package app.game.carmensandiego.model.action;

import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.cities.City;
import app.game.carmensandiego.model.investigation.Investigation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static app.game.carmensandiego.fixtures.CurrentLocationMother.locationInEuropeTrail;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class SeeConnectionsActionTest {
    //TODO: BORRAR ESTA CLASE

    @Mock
    Investigation investigation;

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
        Assignment assignment = new Assignment(investigation);
        assignment.setCurrentLocation(locationInEuropeTrail());

        List<City> connections = seeConnectionsAction.execute(assignment);

        assertThat(connections).isEqualTo(assignment.getAvailableConnections());
    }

}
