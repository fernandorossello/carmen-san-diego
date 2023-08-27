package app.game.carmensandiego.model;

import app.game.carmensandiego.model.cities.City;
import app.game.carmensandiego.model.investigation.Investigation;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class Assignment {

    public static final int AMOUNT_OF_MISLEADING_CITIES = 4;
    private CurrentLocation currentLocation;
    private final Investigation investigation;

    public Assignment(Investigation investigation) {
        this.investigation = investigation;

        this.currentLocation = CurrentLocation.builder()
                .currentCity(investigation.getOriginCity())
                .cityOptions(investigation.getMisleadingCities(AMOUNT_OF_MISLEADING_CITIES))
                .nextInTrail(investigation.getNextCityInTrail(investigation.getOriginCity()))
                .build();
    }

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

    public String investigatePointOfInterest(PointOfInterest pointOfInterest) {
        String clue = pointOfInterest.getClue();

        if (clue == null) {
            return "No he visto al sospechoso por aquí";
        }
        return clue;
    }
}
