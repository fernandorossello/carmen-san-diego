package app.game.carmensandiego.model;

import app.game.carmensandiego.model.investigation.Investigation;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Assignment {

    public static final int AMOUNT_OF_MISLEADING_CITIES = 4;
    private CurrentLocation currentLocation;
    private final Investigation investigation;
    private List<City> availableConnections;

    public String getCurrentLocationName() {
        return currentLocation.getCurrentCity().name();
    }

    public String getCurrentLocationDescription() {
        return currentLocation.getCurrentCity().description();
    }

    public List<City> getAvailableConnections() {
        List<City> availableConnections = new LinkedList<>();

        return availableConnections;
    }

    public void travelTo(City city) {
        isValidCity(city);
        this.currentLocation = CurrentLocation.builder()
                .previousCity(currentLocation.getCurrentCity())
                .currentCity(city)
                .cityOptions(investigation.getMisleadingCities(AMOUNT_OF_MISLEADING_CITIES))
                .nextInTrail(investigation.getNextCityInTrail(city))
                .build();
    }

    private void isValidCity(City city) {
        if(!getAvailableConnections().contains(city)) {
            throw new IllegalArgumentException("City is not in the available connections");
        }
    }
    private boolean currentCityIsInTheTrail() {
        return investigation.isInTrail(currentLocation.getCurrentCity());
    }

    private boolean isNotStartingCity() {
        return currentLocation.getPreviousCity() != null;
    }

}
