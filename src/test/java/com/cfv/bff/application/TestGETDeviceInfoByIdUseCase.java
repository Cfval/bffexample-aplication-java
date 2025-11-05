package com.cfv.bff.application;

import com.cfv.bff.application.model.DeviceInfoByIdUseCaseResponse;
import com.cfv.bff.core.model.DevicesInfo;
import com.cfv.bff.core.DevicesInfoMother;
import com.cfv.bff.core.ports.DevicesInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestGETDeviceInfoByIdUseCase {

    @Mock
    private DevicesInfoRepository repository;

    private GETDeviceInfoByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GETDeviceInfoByIdUseCase(repository);
    }

    @Test
    void returns_device_when_found() {
        // Arrange
        DevicesInfo device = DevicesInfoMother.device1();
        when(repository.findById(1L)).thenReturn(Optional.of(device));

        // Act
        DeviceInfoByIdUseCaseResponse response = useCase.invoke(1L);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getDevice(), "El device dentro de la respuesta no debe ser null");
        assertEquals(device, response.getDevice(), "El device devuelto debe ser el mismo que devolvió el repositorio");

        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void throws_runtime_exception_when_not_found() {
        // Arrange
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.invoke(99L));
        assertTrue(ex.getMessage().contains("Device not found"), "El mensaje debe indicar que no se encontró el device");

        verify(repository, times(1)).findById(99L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void throws_illegal_argument_exception_when_id_is_null() {
        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> useCase.invoke(null));
        assertTrue(ex.getMessage().contains("ID cannot be null"));

        // El repositorio no debe ser invocado si el id es null
        verifyNoInteractions(repository);
    }
}


