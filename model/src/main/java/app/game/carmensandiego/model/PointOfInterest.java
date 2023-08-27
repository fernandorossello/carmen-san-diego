package app.game.carmensandiego.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class PointOfInterest {

    private final String name;

    @Setter
    @Getter
    private String clue;


    public PointOfInterest(String name) {
        this.name = name;
    }
}
