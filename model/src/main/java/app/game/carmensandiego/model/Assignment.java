package app.game.carmensandiego.model;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Assignment {

    private CurrentLocation currentLocation;

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
}
