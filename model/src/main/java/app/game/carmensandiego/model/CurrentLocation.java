package app.game.carmensandiego.model;

import app.game.carmensandiego.model.cities.City;
import lombok.Builder;
import lombok.Getter;

import java.util.*;

@Builder
@Getter
public class CurrentLocation {
//TODO: Implement own builder to hide optionals setting complexity
    @Builder.Default
    private final Optional<City> previousCity = Optional.empty();
    private final City currentCity;
    private final List<City> cityOptions;
    @Builder.Default
    private final Optional<City> nextInTrail = Optional.empty();
    private final boolean isInTheTrail;
    private final List<City> availableConnections = new LinkedList<>();

    private static int CITIES_TO_SHOW = 4;
    public List<City> getAvailableConnections() {
        if(!availableConnections.isEmpty()) {
            return availableConnections;
        }

        previousCity.ifPresent(availableConnections::add);

        nextInTrail.ifPresent(availableConnections::add);

        availableConnections.addAll(getMisleadingCities());

        Collections.shuffle(availableConnections, new Random(System.nanoTime())); // To prevent giving hints with the position of the cities

        return availableConnections;
    }

    private boolean isDifferentFromPreviousCity(City city) {
        return previousCity.isEmpty() || !previousCity.get().equals(city);
    }

    private List<City> getMisleadingCities() {
        return cityOptions.stream()
                .filter(c -> !c.equals(currentCity) && isDifferentFromPreviousCity(c))
                .toList()
                .subList(0, CITIES_TO_SHOW - availableConnections.size());
    }

}
