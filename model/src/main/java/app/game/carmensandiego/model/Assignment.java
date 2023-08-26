package app.game.carmensandiego.model;

import app.game.carmensandiego.model.investigation.Investigation;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Assignment {

    public static final int AMOUNT_OF_MISLEADING_CITIES = 4;
    private CurrentLocation currentLocation;
    private final Investigation investigation;

    public String getCurrentLocationName() {
        return currentCity().name();
    }

    public String getCurrentLocationDescription() {
        return currentCity().description();
    }

    public List<City> getAvailableConnections() {
        return currentLocation.getAvailableConnections();
    }

    public void travelTo(City city) {
        isValidCity(city);
        this.currentLocation = CurrentLocation.builder()
                .previousCity(currentCity())
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

    public List<PointOfInterest> getPointOfInterest() {
        return currentCity().pointsOfInterest();
    }

    private City currentCity() {
        return currentLocation.getCurrentCity();
    }
}
