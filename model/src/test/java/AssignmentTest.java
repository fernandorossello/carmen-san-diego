import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.City;
import app.game.carmensandiego.model.action.GameAction;
import app.game.carmensandiego.model.action.SeeConnectionsAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AssignmentTest {

    @Test
    @DisplayName("When asking for the actions, should receive the See connections action")
    void getActions_seeConnections() {
        Assignment assignment = new Assignment();

        List<GameAction> actions = assignment.getActions();
        assertThat(actions).hasSize(1);
        assertThat(actions.get(0)).isInstanceOf(SeeConnectionsAction.class);
    }

    @Test
    @DisplayName("When asking for the current location name, should receive the current location name from the City")
    void getCurrentLocationName() {
        Assignment assignment = new Assignment();
        String cityName = "Buenos Aires";
        assignment.setCurrentLocation(new City(cityName, "description"));

        String currentLocationName = assignment.getCurrentLocationName();
        assertThat(currentLocationName).isEqualTo(cityName);
    }

    @Test
    @DisplayName("When asking for the current location description, should receive the current location description from the City")
    void getCurrentLocationDescription() {
        Assignment assignment = new Assignment();
        String cityDescription = "Capital de Argentina";
        assignment.setCurrentLocation(new City("Buenos Aires", cityDescription));

        String currentLocationDescription = assignment.getCurrentLocationDescription();
        assertThat(currentLocationDescription).isEqualTo(cityDescription);
    }


}
