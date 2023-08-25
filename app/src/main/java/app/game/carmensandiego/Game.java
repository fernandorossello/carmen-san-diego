package app.game.carmensandiego;

import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.action.GameAction;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class Game {
    private final Output output;
    private final Assignment assignment;
    private final Map<Integer, GameAction> actions = new HashMap<>();

    public void displayActions() {
        if(actions.isEmpty()) {
            List<GameAction> availableActions = assignment.getActions();
            actions.put(1, availableActions.get(0));
            /*IntStream.of(1, availableActions.size()+1)
                    .forEach(index -> actions.put(index-1, availableActions.get(index-1)));*/
        }

        actions.forEach((index, action) -> output.println(index + ". " + action.getName()));
    }
}
