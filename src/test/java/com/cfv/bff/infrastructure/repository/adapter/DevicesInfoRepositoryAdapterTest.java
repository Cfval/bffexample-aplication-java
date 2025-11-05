package com.cfv.bff.infrastructure.repository.adapter;

import com.cfv.bff.core.model.DevicesInfo;
import com.cfv.bff.core.DevicesInfoMother;
import com.cfv.bff.infrastructure.repository.JpaDevicesInfoRepository;
import com.cfv.bff.infrastructure.repository.entity.DevicesInfoEntity;
import com.cfv.bff.infrastructure.repository.entity.DevicesInfoEntityMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DevicesInfoRepositoryAdapterTest {

    @Mock
    private JpaDevicesInfoRepository jpaRepository;
    private DevicesInfoRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new DevicesInfoRepositoryAdapter(jpaRepository);
    }

    @Test
    void findAll_returns_mapped_domain_list() {
        // Arrange
        DevicesInfoEntity e1 = DevicesInfoEntityMother.entity1();
        DevicesInfoEntity e2 = DevicesInfoEntityMother.entity2();
        when(jpaRepository.findAll()).thenReturn(List.of(e1, e2));

        // Act
        List<DevicesInfo> result = adapter.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        // Comprobar campos mapeados del primer elemento
        DevicesInfo d1 = result.get(0);
        assertEquals(e1.getId(), d1.id());
        assertEquals(e1.getBrand(), d1.brand());
        assertEquals(e1.getModel(), d1.model());
        assertEquals(e1.getImei(), d1.imei());

        // Comprobar campos mapeados del segundo elemento
        DevicesInfo d2 = result.get(1);
        assertEquals(e2.getId(), d2.id());
        assertEquals(e2.getBrand(), d2.brand());
        assertEquals(e2.getModel(), d2.model());
        assertEquals(e2.getImei(), d2.imei());

        verify(jpaRepository, times(1)).findAll();
        verifyNoMoreInteractions(jpaRepository);
    }

    @Test
    void findById_returns_mapped_domain_when_found() {
        // Arrange
        DevicesInfoEntity e = DevicesInfoEntityMother.entity1();
        when(jpaRepository.findById(1L)).thenReturn(Optional.of(e));

        // Act
        Optional<DevicesInfo> opt = adapter.findById(1L);

        // Assert
        assertTrue(opt.isPresent());
        DevicesInfo domain = opt.get();
        assertEquals(e.getId(), domain.id());
        assertEquals(e.getBrand(), domain.brand());
        assertEquals(e.getModel(), domain.model());
        assertEquals(e.getImei(), domain.imei());

        verify(jpaRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(jpaRepository);
    }

    @Test
    void findById_returns_empty_when_not_found() {
        // Arrange
        when(jpaRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<DevicesInfo> opt = adapter.findById(99L);

        // Assert
        assertTrue(opt.isEmpty());

        verify(jpaRepository, times(1)).findById(99L);
        verifyNoMoreInteractions(jpaRepository);
    }

    @Test
    void save_converts_domain_to_entity_and_returns_mapped_domain() {
        // Arrange
        // Domain to save (new device without id)
        DevicesInfo domainToSave = DevicesInfoMother.newDeviceExample(); // id null
        // Entity returned by JPA (simula persistencia con id asignado)
        DevicesInfoEntity persisted = DevicesInfoEntityMother.persistedEntity(10L, domainToSave.brand(), domainToSave.model(), domainToSave.imei());
        when(jpaRepository.save(any(DevicesInfoEntity.class))).thenReturn(persisted);

        // Act
        DevicesInfo result = adapter.save(domainToSave);

        // Assert: resultado mapeado desde la entidad persistida
        assertNotNull(result);
        assertEquals(persisted.getId(), result.id());
        assertEquals(persisted.getBrand(), result.brand());
        assertEquals(persisted.getModel(), result.model());
        assertEquals(persisted.getImei(), result.imei());

        // Verificar que se llamó a jpa.save con una entidad construida desde el domain
        ArgumentCaptor<DevicesInfoEntity> captor = ArgumentCaptor.forClass(DevicesInfoEntity.class);
        verify(jpaRepository, times(1)).save(captor.capture());
        DevicesInfoEntity passedEntity = captor.getValue();

        // El entity pasado al save debería reflejar los valores del domainToSave
        // Si domainToSave.id() es null, passedEntity.getId() probablemente será null (antes de persistir)
        assertNull(passedEntity.getId(), "Se esperaba que la entidad pasada a save no tenga id (nuevo recurso)");
        assertEquals(domainToSave.brand(), passedEntity.getBrand());
        assertEquals(domainToSave.model(), passedEntity.getModel());
        assertEquals(domainToSave.imei(), passedEntity.getImei());

        verifyNoMoreInteractions(jpaRepository);
    }
}

