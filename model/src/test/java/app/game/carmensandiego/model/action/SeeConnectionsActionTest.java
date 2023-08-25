package app.game.carmensandiego.model.action;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SeeConnectionsActionTest {

    @Test
    @DisplayName("When getting the name, should return the correct description")
    void getDescription() {
        SeeConnectionsAction seeConnectionsAction = new SeeConnectionsAction();
        String name = seeConnectionsAction.getName();
        assertThat(name).isEqualTo("Ver conexiones");
    }
}
