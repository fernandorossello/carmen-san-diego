package app.game.carmensandiego.model;

import app.game.carmensandiego.model.statement.Statement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class PointOfInterest {

    @Getter
    private final String name;

    @Setter
    @Getter
    private Statement statement;


    public PointOfInterest(String name) {
        this.name = name;
    }
}
