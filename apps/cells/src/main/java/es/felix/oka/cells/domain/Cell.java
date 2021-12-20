package es.felix.oka.cells.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cell {

    private String uid;

    private int level;

    private String title;

    private String description;

    private long version;

    public Cell(String uid, int level, String title, String description) {
        this.uid = uid;
        this.level = level;
        this.title = title;
        this.description = description;
        version = 0;
    }
}
