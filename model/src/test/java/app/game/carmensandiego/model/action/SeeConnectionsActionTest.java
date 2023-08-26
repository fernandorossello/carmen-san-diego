package app.game.carmensandiego.model.action;

import app.game.carmensandiego.fixtures.CurrentLocationMother;
import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.City;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static app.game.carmensandiego.fixtures.CurrentLocationMother.madridFromBuenosAiresWithEuropeOptions;
import static org.assertj.core.api.Assertions.assertThat;

public class SeeConnectionsActionTest {

    @Test
    @DisplayName("When getting the name, should return the correct description")
    void getDescription() {
        SeeConnectionsAction seeConnectionsAction = new SeeConnectionsAction();
        String name = seeConnectionsAction.getName();
        assertThat(name).isEqualTo("Ver conexiones");
    }

    @Test
    @DisplayName("When executing the action, should get the available connections from the assignment")
    void execute() {
        SeeConnectionsAction seeConnectionsAction = new SeeConnectionsAction();
        Assignment assignment = new Assignment();
        assignment.setCurrentLocation(madridFromBuenosAiresWithEuropeOptions());

        List<City> connections = seeConnectionsAction.execute(assignment);

        assertThat(connections).isEqualTo(assignment.getAvailableConnections());
    }

}
