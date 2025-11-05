package com.cfv.bff.application;

import com.cfv.bff.application.dto.DevicesInfoDto;
import com.cfv.bff.core.model.DevicesInfo;
import com.cfv.bff.core.DevicesInfoMother;
import com.cfv.bff.core.ports.DevicesInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestPOSTDeviceUseCase {

    @Mock
    private DevicesInfoRepository repository;
    private POSTDeviceUseCase useCase;

    @Captor
    private ArgumentCaptor<DevicesInfo> devicesInfoCaptor;

    @BeforeEach
    void setUp() {
        useCase = new POSTDeviceUseCase(repository);
    }

    @Test
    void saves_device_and_returns_created_dto() {
        // Arrange
        DevicesInfoDto inputDto = new DevicesInfoDto(null, "MarcaX", "ModelY", "123456789");

        // Domain que simula el repositorio al devolver el guardado (con id asignado)
        DevicesInfo savedDomain = DevicesInfoMother.persistedDevice(10L, "MarcaX", "ModelY", "123456789");
        when(repository.save(any(DevicesInfo.class))).thenReturn(savedDomain);

        // Act
        DevicesInfoDto result = useCase.invoke(inputDto);

        // Assert
        assertNotNull(result);
        assertEquals(10L, result.getId(), "El id devuelto debe ser el asignado por el repositorio");
        assertEquals("MarcaX", result.getBrand());
        assertEquals("ModelY", result.getModel());
        assertEquals("123456789", result.getImei());

        // Assert - argumento pasado al repository.save()
        verify(repository, times(1)).save(devicesInfoCaptor.capture());
        DevicesInfo passedToSave = devicesInfoCaptor.getValue();
        assertNotNull(passedToSave);

        // Se espera que el objeto a guardar tenga id null (nuevo) y campos iguales a los del DTO
        assertNull(passedToSave.id());
        assertEquals("MarcaX", passedToSave.brand());
        assertEquals("ModelY", passedToSave.model());
        assertEquals("123456789", passedToSave.imei());
    }

    @Test
    void propagates_exception_when_repository_save_fails() {
        // Arrange
        DevicesInfoDto inputDto = new DevicesInfoDto(null, "MarcaX", "ModelY", "123212321");
        when(repository.save(any())).thenThrow(new RuntimeException("DB down"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.invoke(inputDto));
        assertTrue(ex.getMessage().contains("DB down"));

        verify(repository, times(1)).save(any(DevicesInfo.class));
    }
}

