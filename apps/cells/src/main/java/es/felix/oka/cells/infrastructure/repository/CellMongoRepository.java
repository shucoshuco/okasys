package es.felix.oka.cells.infrastructure.repository;

import es.felix.oka.cells.infrastructure.entity.CellEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellMongoRepository extends ReactiveMongoRepository<CellEntity, String> {
}
