package app.game.carmensandiego.fixtures;

import app.game.carmensandiego.model.CurrentLocation;

import java.util.List;

import static app.game.carmensandiego.fixtures.CityMother.*;

public class CurrentLocationMother {
    public static CurrentLocation initialLocationInEuropeTrail() {
        return CurrentLocation.builder()
                .currentCity(paris())
                .nextInTrail(madrid())
                .cityOptions(List.of(beijing(), bangkok(), tokio(), nomPen()))
                .build();
    }

    public static CurrentLocation locationInEuropeTrail() {
        return CurrentLocation.builder()
                .previousCity(paris())
                .currentCity(madrid())
                .nextInTrail(london())
                .cityOptions(List.of(beijing(), bangkok(), tokio(), nomPen()))
                .build();
    }

    public static CurrentLocation locationAtTheEndOfEuropeTrail() {
        return CurrentLocation.builder()
                .previousCity(madrid())
                .currentCity(london())
                .cityOptions(List.of(beijing(), bangkok(), tokio(), nomPen()))
                .build();
    }

    public static CurrentLocation locationOutsideTheEuropeTrail() {
        return CurrentLocation.builder()
                .previousCity(paris())
                .currentCity(beijing())
                .cityOptions(List.of(beijing(), bangkok(), tokio(), nomPen()))
                .build();
    }
}
