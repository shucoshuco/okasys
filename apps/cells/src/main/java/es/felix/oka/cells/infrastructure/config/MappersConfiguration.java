package es.felix.oka.cells.infrastructure.config;

import es.felix.oka.cells.application.mapper.CellApiMapper;
import es.felix.oka.cells.application.mapper.CellApiMapperImpl;
import es.felix.oka.cells.domain.mappers.CellMapper;
import es.felix.oka.cells.domain.mappers.CellMapperImpl;
import es.felix.oka.cells.infrastructure.mapper.CellEntityMapper;
import es.felix.oka.cells.infrastructure.mapper.CellEntityMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfiguration {

    @Bean
    public CellMapper cellMapper() {
        return new CellMapperImpl();
    }

    @Bean
    public CellApiMapper cellApiMapper() {
        return new CellApiMapperImpl();
    }

    @Bean
    public CellEntityMapper cellEntityMapper() {
        return new CellEntityMapperImpl();
    }
}
