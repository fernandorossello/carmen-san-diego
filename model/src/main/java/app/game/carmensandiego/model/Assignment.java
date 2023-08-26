package app.game.carmensandiego.model;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Assignment {

    private CurrentLocation currentLocation;

    public String getCurrentLocationName() {
        return currentLocation.currentCity().name();
    }

    public String getCurrentLocationDescription() {
        return currentLocation.currentCity().description();
    }

    public List<City> getAvailableConnections() {
        List<City> availableConnections = new LinkedList<>();

        if(currentLocation.previousCity() != null) {
            availableConnections.add(currentLocation.previousCity());
        }

        return availableConnections;
    }
}
