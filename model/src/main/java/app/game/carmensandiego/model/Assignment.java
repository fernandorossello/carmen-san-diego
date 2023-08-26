package app.game.carmensandiego.model;

import app.game.carmensandiego.model.investigation.Investigation;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Assignment {

    public static final int AMOUNT_OF_MISLEADING_CITIES = 3;
    private CurrentLocation currentLocation;
    private final Investigation investigation;

    public String getCurrentLocationName() {
        return currentLocation.getCurrentCity().name();
    }

    public String getCurrentLocationDescription() {
        return currentLocation.getCurrentCity().description();
    }

    public List<City> getAvailableConnections() {
        List<City> availableConnections = new LinkedList<>();

        if(currentLocation.getPreviousCity() != null) {
            availableConnections.add(currentLocation.getPreviousCity());
        }

        availableConnections.addAll(currentLocation.getCityOptions());

        return availableConnections;
    }

    public void travelTo(City city) {
        CurrentLocation newLocation = CurrentLocation.builder()
                .previousCity(currentLocation.getCurrentCity())
                .currentCity(city)
                .build();

        newLocation.addCityOptions(investigation.getMisleadingCities(AMOUNT_OF_MISLEADING_CITIES));

        this.currentLocation = newLocation;
    }
}
