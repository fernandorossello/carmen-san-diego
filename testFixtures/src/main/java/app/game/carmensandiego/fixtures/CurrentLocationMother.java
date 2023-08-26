package app.game.carmensandiego.fixtures;

import app.game.carmensandiego.model.CurrentLocation;

import java.util.List;

import static app.game.carmensandiego.fixtures.CityMother.*;

public class CurrentLocationMother {

    public static CurrentLocation madridFromBuenosAires() {
        return CurrentLocation.builder()
                .previousCity(buenosAires())
                .currentCity(madrid())
                .build();
    }

    public static CurrentLocation initialLocationMadrid() {
        return CurrentLocation.builder()
                .currentCity(madrid())
                .build();
    }

    public static CurrentLocation madridFromBuenosAiresWithEuropeOptions() {
        CurrentLocation location = CurrentLocation.builder()
                .previousCity(buenosAires())
                .currentCity(madrid())
                .build();

        location.addCityOptions(List.of(londres(), paris()));

        return location;
    }
}
