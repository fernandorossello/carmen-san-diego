package app.game.carmensandiego.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Builder
@Getter
public class CurrentLocation {

    private final City previousCity;
    private final City currentCity;
    private final List<City> cityOptions;
    private final City nextInTrail;
    private final boolean isInTheTrail;
    private final List<City> availableConnections = new LinkedList<>();

    private static int CITIES_TO_SHOW = 4;
    public List<City> getAvailableConnections() {
        if(!availableConnections.isEmpty()) {
            return availableConnections;
        }

        if (isNotStartingCity()) {
            availableConnections.add(previousCity);
        }

        if (hasNextInTrail()) {
            availableConnections.add(nextInTrail);
        }

        availableConnections.addAll(cityOptions.subList(0, CITIES_TO_SHOW - availableConnections.size()));
        Collections.shuffle(availableConnections, new Random(System.nanoTime())); // To prevent giving hints with the position of the cities

        return availableConnections;
    }

    private boolean hasNextInTrail() {
        return nextInTrail != null;
    }

    private boolean isNotStartingCity() {
        return previousCity != null;
    }
}
