package app.game.carmensandiego;

import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.PointOfInterest;
import app.game.carmensandiego.model.cities.City;
import app.game.carmensandiego.model.statement.Statement;
import app.game.carmensandiego.model.statement.SuspectFoundStatement;

import java.util.List;

import static java.lang.Thread.sleep;


public class Game {
    private final Output output;
    private final Assignment assignment;
    public Game(Output output, Assignment assignment) {
        this.output = output;
        this.assignment = assignment;
    }

    public void displayActions() {
        output.println("#############################################");
        output.println("ACCIONES: ");
        output.println("#############################################");
        output.println("1. Ver conexiones");
        output.println("2. Viajar");
        output.println("3. Investigar");
        output.println("0. Salir");
    }

    public void executeAction(int index) throws InterruptedException {
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
                Statement statement = investigatePointOfInterest(pointOfInterest);
                if(statement instanceof SuspectFoundStatement) {
                    output.println("");
                    output.println("");
                    output.println("¡FELICIDADES! HAS ENCONTRADO A CARMEN SANDIEGO");
                    output.println("¡HAS GANADO!");
                    sleep(5000);
                    output.println("");
                    output.println("");
                    output.println("");
                    output.println("");
                    output.println("");
                    output.println("");
                    System.exit(0);
                } else {
                    output.println(statement.toString());
                }

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

    public Statement investigatePointOfInterest(PointOfInterest pointOfInterest) {
        return assignment.investigatePointOfInterest(pointOfInterest);
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
