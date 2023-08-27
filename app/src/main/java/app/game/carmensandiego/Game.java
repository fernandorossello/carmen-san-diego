package app.game.carmensandiego;

import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.City;
import app.game.carmensandiego.model.PointOfInterest;
import app.game.carmensandiego.model.action.GameAction;
import app.game.carmensandiego.model.action.SeeConnectionsAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Game {
    private final Output output;
    private final Assignment assignment;

    private final Map<Integer, GameAction> actions = new HashMap<>();
    private final SeeConnectionsAction seeConnectionsAction = new SeeConnectionsAction();

    public Game(Output output, Assignment assignment) {
        this.output = output;
        this.assignment = assignment;
        initActions();
    }

    private void initActions() {
        actions.put(1, seeConnectionsAction);
    }

    public void displayActions() {
        actions.forEach((index, action) -> output.println(index + ". " + action.getName()));
    }

    public void executeAction(Actions index) {
        switch (index) {
            case SEE_CONNECTIONS:
                List<City> connections = seeConnectionsAction.execute(assignment);
                output.println("Conexiones: ");
                connections.forEach(city -> output.println(city.name()));
                break;
            case TRAVEL:
                break;
            default:
                throw new UnsupportedOperationException("Invalid action");
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
        output.println("Puntos de interés: ");
        pointOfInterest.forEach(p -> output.println(p.getName()));
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
