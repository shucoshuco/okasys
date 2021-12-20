package es.felix.oka.cells.infrastructure.config;

import es.felix.oka.cells.domain.mappers.CellMapper;
import es.felix.oka.cells.domain.repository.CellRepository;
import es.felix.oka.cells.domain.usecases.BuildGameUseCase;
import es.felix.oka.cells.domain.usecases.BuildGameUseCaseImpl;
import es.felix.oka.cells.domain.usecases.CrudUseCase;
import es.felix.oka.cells.domain.usecases.CrudUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public CrudUseCase crudUseCase(CellRepository repository, CellMapper mapper) {
        return new CrudUseCaseImpl(repository, mapper);
    }

    @Bean
    public BuildGameUseCase buildGameUseCase(CellRepository repository) {
        return new BuildGameUseCaseImpl(repository);
    }
}
