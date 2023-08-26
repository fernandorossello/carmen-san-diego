import app.game.carmensandiego.fixtures.CityMother;
import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.City;
import app.game.carmensandiego.model.action.GameAction;
import app.game.carmensandiego.model.action.SeeConnectionsAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static app.game.carmensandiego.fixtures.CurrentLocationMother.initialLocationMadrid;
import static app.game.carmensandiego.fixtures.CurrentLocationMother.madridFromBuenosAires;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AssignmentTest {

    private final City buenosAires = CityMother.buenosAires();
    private final City madrid = CityMother.madrid();

    @Test
    @DisplayName("When asking for the current location name, should receive the current location name from the City")
    void getCurrentLocationName() {
        Assignment assignment = new Assignment();
        assignment.setCurrentLocation(madridFromBuenosAires());

        String currentLocationName = assignment.getCurrentLocationName();
        assertThat(currentLocationName).isEqualTo(madrid.name());
    }

    @Test
    @DisplayName("When asking for the current location description, should receive the current location description from the City")
    void getCurrentLocationDescription() {
        Assignment assignment = new Assignment();
        assignment.setCurrentLocation(madridFromBuenosAires());

        String currentLocationDescription = assignment.getCurrentLocationDescription();

        assertThat(currentLocationDescription).isEqualTo(madrid.description());
    }

    @Test
    @DisplayName("When asking for the available connections, the first connection should be the previous city")
    void getAvailableConnections() {
        Assignment assignment = new Assignment();
        assignment.setCurrentLocation(madridFromBuenosAires());

        List<City> availableConnections = assignment.getAvailableConnections();

        assertThat(availableConnections.get(0)).isEqualTo(buenosAires);
    }

    @Test
    @DisplayName("When asking for the available connections on the first location, should not fail because there is no previous city")
    void getAvailableConnectionsOnFirstLocation() {
        Assignment assignment = new Assignment();
        assignment.setCurrentLocation(initialLocationMadrid());

        List<City> availableConnections = assignment.getAvailableConnections();

        assertThat(availableConnections).isEmpty();
    }

}
