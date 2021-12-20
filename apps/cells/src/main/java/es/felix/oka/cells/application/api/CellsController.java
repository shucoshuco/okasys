package es.felix.oka.cells.application.api;

import es.felix.oka.cells.application.mapper.CellApiMapper;
import es.felix.oka.cells.domain.usecases.CrudUseCase;
import es.felix.oka.schemas.BoardApi;
import es.felix.oka.schemas.CellsApi;
import es.felix.oka.schemas.model.CellNoValidatedApiObj;
import es.felix.oka.schemas.model.InlineResponse200ApiObj;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CellsController implements CellsApi, BoardApi {

    private final CrudUseCase crudUseCase;

    private final CellApiMapper mapper;

    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    @Secured("admin")
    public ResponseEntity<InlineResponse200ApiObj> postCell(CellNoValidatedApiObj cell) {
        log.debug("Post to create new cell");
        return crudUseCase.addCell(mapper.apiToDomain(cell))
                .map(c -> ResponseEntity.ok(new InlineResponse200ApiObj().cell(mapper.domainToApi(c))))
                .block();
    }

    @Override
    @Secured("admin")
    public ResponseEntity<Void> deleteCellById(String cellId) {
        log.debug("Delete cell");
        crudUseCase.deleteCel(cellId);
        return ResponseEntity.ok().build();
    }

    @Override
    @Secured("user")
    public ResponseEntity<InlineResponse200ApiObj> getCellById(String cellId) {
        log.debug("Get cell by id");
        return crudUseCase.getCell(cellId)
                .map(cell -> ResponseEntity.ok(new InlineResponse200ApiObj().cell(mapper.domainToApi(cell))))
                .block();
    }

    @Override
    @Secured("admin")
    public ResponseEntity<InlineResponse200ApiObj> patchCell(String cellId, CellNoValidatedApiObj cell) {
        log.debug("Patch to partially update cell");
        return crudUseCase.updateCell(cellId, mapper.apiToDomainUpdate(cell))
                .map(c -> ResponseEntity.ok(new InlineResponse200ApiObj().cell(mapper.domainToApi(c))))
                .block();
    }
}
