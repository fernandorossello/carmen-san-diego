package app.game.carmensandiego.model;

import app.game.carmensandiego.model.investigation.Clue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class PointOfInterest {

    @Getter
    private final String name;

    @Setter
    @Getter
    private String clue;

    @Setter
    @Getter
    private Clue clue2;


    public PointOfInterest(String name) {
        this.name = name;
    }
}
