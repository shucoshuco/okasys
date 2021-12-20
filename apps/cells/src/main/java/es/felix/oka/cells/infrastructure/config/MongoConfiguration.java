package es.felix.oka.cells.infrastructure.config;

import es.felix.oka.cells.infrastructure.repository.CellMongoRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = CellMongoRepository.class)
public class MongoConfiguration {
}
