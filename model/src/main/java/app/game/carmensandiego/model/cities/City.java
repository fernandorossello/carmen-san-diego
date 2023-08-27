package app.game.carmensandiego.model.cities;

import app.game.carmensandiego.model.PointOfInterest;
import lombok.Builder;

import java.util.List;

@Builder
public record City(String name, String description, List<PointOfInterest> pointsOfInterest, List<String> clues) {

}

