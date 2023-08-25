import app.game.carmensandiego.model.action.GameAction;
import app.game.carmensandiego.model.action.SeeConnectionsAction;
import lombok.Data;

import java.util.List;

@Data
public class Game {

    public Game() {
        actions = List.of(new SeeConnectionsAction());
    }

    private final List<GameAction> actions;
}
