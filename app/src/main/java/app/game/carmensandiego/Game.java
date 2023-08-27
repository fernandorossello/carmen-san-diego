package app.game.carmensandiego;

import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.PointOfInterest;
import app.game.carmensandiego.model.cities.City;

import java.io.IOException;
import java.util.List;


public class Game {
    private final Output output;
    private final Assignment assignment;
    public Game(Output output, Assignment assignment) {
        this.output = output;
        this.assignment = assignment;
    }

    public void displayActions() {
        output.println("ACCIONES: ");
        output.println("1. Ver conexiones");
        output.println("2. Viajar");
        output.println("3. Investigar");
        output.println("0. Salir");
    }

    public void executeAction(int index) {
        switch (index) {
            case 1 -> {
                output.println("CONEXIONES: ");
                List<City> connections = assignment.getAvailableConnections();
                connections.forEach(city -> output.println(city.name()));
            }
            case 2 -> {
                List<City> availableConnections = assignment.getAvailableConnections();
                availableConnections.forEach(city -> output.println(city.name()));
                String option = output.readInput();
                City city = availableConnections.get(Integer.parseInt(option) - 1);
                travelTo(city);
            }
            case 3 -> {
                seePointOfInterest();
                String optionPointOfInterest = output.readInput();
                PointOfInterest pointOfInterest = assignment.getPointOfInterest().get(Integer.parseInt(optionPointOfInterest) - 1);
                investigatePointOfInterest(pointOfInterest);
            }
            default -> throw new UnsupportedOperationException("Invalid action");
        }
    }

    public void travelTo(City city) {
        assignment.travelTo(city);
        output.println("Bienvenido a " + assignment.getCurrentLocationName() + ". " + assignment.getCurrentLocationDescription());
    }

    public void currentLocationName() {
        String cityName = assignment.getCurrentLocationName();
        output.println(cityName);
    }

    public void currentLocationDescription() {
        String cityDescription = assignment.getCurrentLocationDescription();
        output.println(cityDescription);
    }

    public void seePointOfInterest() {
        List<PointOfInterest> pointOfInterest = assignment.getPointOfInterest();
        output.println("PUNTOS DE INTERÉS: ");
        pointOfInterest.forEach(p -> output.println(p.getName()));
    }

    public void investigatePointOfInterest(PointOfInterest pointOfInterest) {
        String clue = assignment.investigatePointOfInterest(pointOfInterest);

        output.println(clue);
    }

    public enum Actions {

        SEE_CONNECTIONS(1),
        TRAVEL(2);

        private int value;

        Actions(int value) {
            this.value = value;
        }
    }
}
