import app.game.carmensandiego.fixtures.CityMother;
import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.City;
import app.game.carmensandiego.model.CurrentLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static app.game.carmensandiego.fixtures.CityMother.londres;
import static app.game.carmensandiego.fixtures.CityMother.paris;
import static app.game.carmensandiego.fixtures.CurrentLocationMother.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AssignmentTest {

    private final City buenosAires = CityMother.buenosAires();
    private final City madrid = CityMother.madrid();

    private final City londres = CityMother.londres();

    private Assignment assignment;

    @BeforeEach
    void setUp() {
        assignment = new Assignment();
    }

    @Test
    @DisplayName("When asking for the current location name, should receive the current location name from the City")
    void getCurrentLocationName() {
        assignment.setCurrentLocation(madridFromBuenosAires());

        String currentLocationName = assignment.getCurrentLocationName();
        assertThat(currentLocationName).isEqualTo(madrid.name());
    }

    @Test
    @DisplayName("When asking for the current location description, should receive the current location description from the City")
    void getCurrentLocationDescription() {
        assignment.setCurrentLocation(madridFromBuenosAires());

        String currentLocationDescription = assignment.getCurrentLocationDescription();

        assertThat(currentLocationDescription).isEqualTo(madrid.description());
    }

    @Test
    @DisplayName("When asking for the available connections, the first connection should be the previous city")
    void getAvailableConnections() {
        assignment.setCurrentLocation(madridFromBuenosAires());

        List<City> availableConnections = assignment.getAvailableConnections();

        assertThat(availableConnections.get(0)).isEqualTo(buenosAires);
    }

    @Test
    @DisplayName("When asking for the available connections on the first location, should not fail because there is no previous city")
    void getAvailableConnectionsOnFirstLocation() {
        assignment.setCurrentLocation(initialLocationMadrid());

        List<City> availableConnections = assignment.getAvailableConnections();

        assertThat(availableConnections).isEmpty();
    }

    @Test
    @DisplayName("When asking for the available connections, should also display other options")
    void getAvailableConnectionsAlsoDisplayOtherOptions() {
        assignment.setCurrentLocation(madridFromBuenosAiresWithEuropeOptions());

        List<City> availableConnections = assignment.getAvailableConnections();

        assertThat(availableConnections).containsExactlyInAnyOrder(buenosAires, londres(), paris());
    }

    @Test
    @DisplayName("When traveling to a city, should update the current location")
    void travel_updateCurrentLocation() {
        assignment.setCurrentLocation(initialLocationMadrid());

        assignment.travelTo(londres);

        assertThat(assignment.getCurrentLocationName()).isEqualTo(londres.name());
    }

    @Test
    @DisplayName("When traveling to a city, should update the previous location with the current location")
    void travel_updatePreviousLocation() {
        assignment.setCurrentLocation(initialLocationMadrid());

        assignment.travelTo(londres);

        assertThat(assignment.getCurrentLocation().getPreviousCity()).isEqualTo(madrid);
    }

}
