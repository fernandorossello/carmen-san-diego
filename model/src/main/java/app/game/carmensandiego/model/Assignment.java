package app.game.carmensandiego.model;

import app.game.carmensandiego.model.cities.City;
import app.game.carmensandiego.model.criminals.Criminal;
import app.game.carmensandiego.model.investigation.Investigation;
import app.game.carmensandiego.model.statement.Statement;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class Assignment {

    public static final int HOURS_SPENT_TRAVEL = 8;
    public static final int HOURS_SPENT_INVESTIGATING = 3;
    private CurrentLocation currentLocation;
    private final Investigation investigation;
    private int hoursLeft;
    private HourCounter hourCounter;

    public Assignment(Investigation investigation) {
        this.investigation = investigation;

        this.currentLocation = CurrentLocation.builder()
                .currentCity(investigation.getOriginCity())
                .cityOptions(investigation.getMisleadingCities())
                .nextInTrail(investigation.getNextCityInTrail(investigation.getOriginCity()))
                .build();

        this.hourCounter = new HourCounter(investigation.getDueHours());
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
        hourCounter.spendHours(HOURS_SPENT_TRAVEL);
        this.currentLocation = CurrentLocation.builder()
                .previousCity(Optional.of(currentCity()))
                .currentCity(city)
                .cityOptions(investigation.getMisleadingCities())
                .nextInTrail(investigation.getNextCityInTrail(city))
                .build();
    }

    public List<PointOfInterest> getPointOfInterest() {
        return currentCity().pointsOfInterest();
    }

    private void isValidCity(City city) {
        if(!getAvailableConnections().contains(city)) {
            throw new IllegalArgumentException("City is not in the available connections");
        }
    }

    private City currentCity() {
        return currentLocation.getCurrentCity();
    }

    public Statement investigatePointOfInterest(PointOfInterest pointOfInterest) {
        hourCounter.spendHours(HOURS_SPENT_INVESTIGATING);
        return pointOfInterest.getStatement();
    }

    public boolean isTimeOver() {
        return hourCounter.isTimeOver();
    }

    public Criminal getCriminal() {
        return investigation.getCriminal();
    }
}
