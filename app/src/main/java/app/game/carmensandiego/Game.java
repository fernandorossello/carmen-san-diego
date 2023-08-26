package app.game.carmensandiego;

import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.action.GameAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Game {
    private final Output output;
    private final Assignment assignment;
    private final Map<Integer, GameAction> actions = new HashMap<>();

    public Game(Output output, Assignment assignment) {
        this.output = output;
        this.assignment = assignment;
        initActions();
    }

    private void initActions() {
        List<GameAction> availableActions = assignment.getActions();
        actions.put(1, availableActions.get(0));
            /*IntStream.of(1, availableActions.size()+1)
                    .forEach(index -> actions.put(index-1, availableActions.get(index-1)));*/
    }

    public void displayActions() {
        actions.forEach((index, action) -> output.println(index + ". " + action.getName()));
    }

    public void executeAction(int index) {
        String out = actions.get(index).execute();
        output.println(out);
    }

    public void currentLocationName() {
        String cityName = assignment.getCurrentLocationName();
        output.println(cityName);
    }
}
