package com.cfv.bff.infrastructure.mappers;

import com.cfv.bff.application.dto.DevicesInfoDto;
import com.cfv.bff.core.model.DevicesInfo;
import com.cfv.bff.core.DevicesInfoMother;
import com.cfv.bff.infrastructure.repository.entity.DevicesInfoEntity;
import com.cfv.bff.infrastructure.repository.entity.DevicesInfoEntityMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TestDevicesInfoMapper {

    @Test
    void toDomain_fromDto_mapsAllFields_whenIdIsNull() {
        // Arrange: DTO sin id (nuevo recurso)
        DevicesInfoDto dto = new DevicesInfoDto(null, "MarcaX", "ModelY", "123456789");

        // Act
        DevicesInfo domain = DevicesInfoMapper.toDomain(dto);

        // Assert
        assertNotNull(domain, "El domain no debe ser null");
        assertNull(domain.id(), "El id debe ser null al mapear un DTO sin id");
        assertEquals("MarcaX", domain.brand(), "La brand debe mapearse correctamente");
        assertEquals("ModelY", domain.model(), "El model debe mapearse correctamente");
        assertEquals("123456789", domain.imei(), "El imei debe mapearse correctamente");
    }

    @Test
    void toDomain_fromDto_mapsAllFields_whenIdPresent() {
        // Arrange: DTO con id
        DevicesInfoDto dto = new DevicesInfoDto(42L, "MarcaA", "ModeloA", "111111111");

        // Act
        DevicesInfo domain = DevicesInfoMapper.toDomain(dto);

        // Assert
        assertNotNull(domain);
        assertEquals(42L, domain.id(), "El id debe mapearse correctamente");
        assertEquals("MarcaA", domain.brand());
        assertEquals("ModeloA", domain.model());
        assertEquals("111111111", domain.imei());
    }

    @Test
    void toDomain_fromEntity_mapsAllFields() {
        // Arrange: Entity persistida de ejemplo
        DevicesInfoEntity entity = DevicesInfoEntityMother.entity1();

        // Act
        DevicesInfo domain = DevicesInfoMapper.toDomain(entity);

        // Assert
        assertNotNull(domain);
        assertEquals(entity.getId(), domain.id(), "El id debe mapearse desde la entity");
        assertEquals(entity.getBrand(), domain.brand(), "La brand debe mapearse desde la entity");
        assertEquals(entity.getModel(), domain.model(), "El model debe mapearse desde la entity");
        assertEquals(entity.getImei(), domain.imei(), "El imei debe mapearse desde la entity");
    }

    @Test
    void toEntity_fromDomain_mapsAllFields() {
        // Arrange: Domain persistido con id usando la mother
        DevicesInfo domain = DevicesInfoMother.persistedDevice(99L, "MarcaD", "ModeloD", "333333333");

        // Act
        DevicesInfoEntity entity = DevicesInfoMapper.toEntity(domain);

        // Assert
        assertNotNull(entity, "La entity no debe ser null");
        assertEquals(99L, entity.getId(), "El id debe mapearse desde el domain");
        assertEquals("MarcaD", entity.getBrand(), "La brand debe mapearse desde el domain");
        assertEquals("ModeloD", entity.getModel(), "El model debe mapearse desde el domain");
        assertEquals("333333333", entity.getImei(), "El imei debe mapearse desde el domain");
    }

    @Test
    void toDomain_fromDto_returnsNullWhenInputIsNull() {
        assertNull(DevicesInfoMapper.toDomain((DevicesInfoDto) null), "Debe devolver null si el DTO es null");
    }

    @Test
    void toDomain_fromEntity_returnsNullWhenInputIsNull() {
        assertNull(DevicesInfoMapper.toDomain((DevicesInfoEntity) null), "Debe devolver null si la Entity es null");
    }

    @Test
    void toEntity_fromDomain_returnsNullWhenInputIsNull() {
        assertNull(DevicesInfoMapper.toEntity((DevicesInfo) null), "Debe devolver null si el domain es null");
    }
}

