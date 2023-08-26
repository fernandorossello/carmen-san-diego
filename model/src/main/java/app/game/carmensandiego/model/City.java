package app.game.carmensandiego.model;

import lombok.Builder;

import java.util.List;

@Builder
public record City(String name, String description, List<PointOfInterest> pointsOfInterest, List<String> clues) {

}

