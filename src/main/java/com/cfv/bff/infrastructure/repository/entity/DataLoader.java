package com.cfv.bff.infrastructure.repository.entity;

import com.cfv.bff.core.model.DevicesInfo;
import com.cfv.bff.core.ports.DevicesInfoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean()
    CommandLineRunner initDatabase(DevicesInfoRepository repository) {
        return args -> {
            // Load initial data into the database
            repository.save(DevicesInfo.fromPrimitives("Samsung", "Galaxy S11", "123456789"));
            repository.save(DevicesInfo.fromPrimitives("iPhone", "13 Pro","666666666"));
            repository.save(DevicesInfo.fromPrimitives("Nokia", "Indestructible","986532659"));
            repository.save(DevicesInfo.fromPrimitives("Nothing", "3a","987654321"));
            repository.save(DevicesInfo.fromPrimitives("Sony Ericsson", "T200","145637891"));
            repository.save(DevicesInfo.fromPrimitives("Xiaomi", "Redmi 14","234512756"));
            repository.save(DevicesInfo.fromPrimitives("Google Pixel", "9a","123453213"));
            repository.save(DevicesInfo.fromPrimitives("Hawai", "Paraiso","778889999"));
            repository.save(DevicesInfo.fromPrimitives("Energy System", "A5","112233445"));
        };
    }
}
