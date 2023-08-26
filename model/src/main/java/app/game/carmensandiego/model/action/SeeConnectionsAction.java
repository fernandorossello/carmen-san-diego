package app.game.carmensandiego.model.action;

import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.City;

import java.util.List;

public class SeeConnectionsAction implements GameAction {
    @Override
    public String getName() {
        return "Ver conexiones";
    }

    public List<City> execute(Assignment assignment) {
        return assignment.getAvailableConnections();
    }
}
