package com.cfv.bff.application;

import com.cfv.bff.application.model.DevicesInfoUseCaseResponse;
import com.cfv.bff.core.model.DevicesInfo;
import com.cfv.bff.core.DevicesInfoMother;
import com.cfv.bff.core.ports.DevicesInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestGETAllDevicesInfoUseCase {

    @Mock
    private DevicesInfoRepository repository;
    private GETAllDevicesInfoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GETAllDevicesInfoUseCase(repository);
    }

    @Test
    void returns_empty_response_when_repository_empty() {
        // Arrange
        when(repository.findAll()).thenReturn(List.of());

        // Act
        DevicesInfoUseCaseResponse result = useCase.invoke();

        // Assert
        assertNotNull(result, "La respuesta no debe ser null");
        assertTrue(result.getDevicesInfoList().isEmpty(), "La lista debe estar vacía");
        assertEquals(0, result.getTotalLines());
        assertEquals(0, result.getTotalLinesFiltered());

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void returns_all_devices_and_correct_totals() {
        // Arrange
        List<DevicesInfo> mockList = List.of(
                DevicesInfoMother.device1(),
                DevicesInfoMother.device2()
        );
        when(repository.findAll()).thenReturn(mockList);

        // Act
        DevicesInfoUseCaseResponse result = useCase.invoke();

        // Assert
        assertNotNull(result, "La respuesta no debe ser null");
        assertEquals(2, result.getDevicesInfoList().size(), "La lista debe contener 2 elementos");
        assertEquals(2, result.getTotalLines());
        assertEquals(2, result.getTotalLinesFiltered());

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void propagates_exception_from_repository() {
        // Arrange
        when(repository.findAll()).thenThrow(new RuntimeException("DB unreachable"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.invoke());
        assertTrue(ex.getMessage().contains("DB unreachable"));

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }
}

