package app.game.carmensandiego.fixtures;

import app.game.carmensandiego.model.CurrentLocation;

import java.util.List;
import java.util.Optional;

import static app.game.carmensandiego.fixtures.CityMother.*;

public class CurrentLocationMother {
    public static CurrentLocation initialLocationInEuropeTrail() {
        return CurrentLocation.builder()
                .currentCity(paris())
                .nextInTrail(Optional.of(madrid()))
                .cityOptions(List.of(beijing(), bangkok(), tokio(), nomPen()))
                .build();
    }

    public static CurrentLocation locationInEuropeTrail() {
        return CurrentLocation.builder()
                .previousCity(Optional.of(paris()))
                .currentCity(madrid())
                .nextInTrail(Optional.of(london()))
                .cityOptions(List.of(beijing(), bangkok(), tokio(), nomPen()))
                .build();
    }

    public static CurrentLocation locationAtTheEndOfEuropeTrail() {
        return CurrentLocation.builder()
                .previousCity(Optional.of(madrid()))
                .currentCity(london())
                .cityOptions(List.of(beijing(), bangkok(), tokio(), nomPen()))
                .build();
    }

    public static CurrentLocation locationOutsideTheEuropeTrail() {
        return CurrentLocation.builder()
                .previousCity(Optional.of(paris()))
                .currentCity(beijing())
                .cityOptions(List.of(beijing(), bangkok(), tokio(), nomPen()))
                .build();
    }
}
