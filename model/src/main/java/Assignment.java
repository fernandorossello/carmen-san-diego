import app.game.carmensandiego.model.action.GameAction;
import app.game.carmensandiego.model.action.SeeConnectionsAction;
import lombok.Data;

import java.util.List;

@Data
public class Assignment {

    public Assignment() {
        actions = List.of(new SeeConnectionsAction());
    }

    private final List<GameAction> actions;
}
