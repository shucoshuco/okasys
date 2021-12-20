package es.felix.oka.cells.infrastructure.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cells")
@Data
public class CellEntity {

    @Id
    private String uid;

    private int level;

    private String title;

    private String description;

    @Version
    private long version;
}