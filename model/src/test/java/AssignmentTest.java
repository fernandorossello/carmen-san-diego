import app.game.carmensandiego.fixtures.CityMother;
import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.cities.City;
import app.game.carmensandiego.model.PointOfInterest;
import app.game.carmensandiego.model.investigation.Investigation;
import app.game.carmensandiego.model.statement.Statement;
import app.game.carmensandiego.model.statement.SuspectSeenStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static app.game.carmensandiego.fixtures.CityMother.beijing;
import static app.game.carmensandiego.fixtures.CityMother.nomPen;
import static app.game.carmensandiego.fixtures.CurrentLocationMother.locationInEuropeTrail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssignmentTest {

    private final City buenosAires = CityMother.buenosAires();
    private final City madrid = CityMother.madrid();

    private final City london = CityMother.london();

    private final City bangkok = CityMother.bangkok();

    private final City tokio = CityMother.tokio();

    @Mock
    private Investigation investigation;

    private Assignment assignment;

    @BeforeEach
    void setUp() {
        assignment = new Assignment(investigation);
    }

    @Test
    @DisplayName("When creating an assignment, a current location should be set from the investigation")
    void createAssignment() {
        when(investigation.getOriginCity()).thenReturn(buenosAires);
        Assignment assignment = new Assignment(investigation);

        assertThat(assignment.getCurrentLocation().getCurrentCity()).isEqualTo(buenosAires);
    }

    @Test
    @DisplayName("When asking for the current location name and description, should provide the right values")
    void getCurrentLocationName() {
        assignment.setCurrentLocation(locationInEuropeTrail());

        assertThat(assignment.getCurrentLocationDescription()).isEqualTo(madrid.description());
        assertThat(assignment.getCurrentLocationName()).isEqualTo(madrid.name());
    }

    @Test
    @DisplayName("When traveling to a city, should update the current location")
    void travel_updateCurrentLocation() {
        assignment.setCurrentLocation(locationInEuropeTrail());

        assignment.travelTo(london);

        assertThat(assignment.getCurrentLocationName()).isEqualTo(london.name());
    }

    @Test
    @DisplayName("When traveling to a city, should update the previous location with the current location")
    void travel_updatePreviousLocation() {
        assignment.setCurrentLocation(locationInEuropeTrail());

        assignment.travelTo(london);

        assertThat(assignment.getCurrentLocation().getPreviousCity()).isEqualTo(Optional.of(madrid));
    }

    @Test
    @DisplayName("When traveling to a city, should update the city connections options from the investigation")
    void travel_updateCityConnectionsOptions() {
        when(investigation.getMisleadingCities(4)).thenReturn(List.of(bangkok, tokio, beijing(),nomPen()));
        assignment.setCurrentLocation(locationInEuropeTrail());

        assignment.travelTo(london);

        assertThat(assignment.getAvailableConnections()).containsExactlyInAnyOrder(madrid, bangkok, tokio, beijing());
    }

    @Test
    @DisplayName("When traveling to a city that is not in the options, should throw an exception")
    void travel_toCityNotInOptions() {
        assignment.setCurrentLocation(locationInEuropeTrail());

        assertThatThrownBy(() -> assignment.travelTo(buenosAires))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("City is not in the available connections");
    }

    @Test
    @DisplayName("When getting the point of interest to invetigate, should return the poi from the current location")
    void getPointOfInterest() {
        assignment.setCurrentLocation(locationInEuropeTrail());

        assertThat(assignment.getPointOfInterest()).isEqualTo(madrid.pointsOfInterest());
    }

    @Test
    @DisplayName("When investigating a point of interest, should provide a clue")
    void investigatePointOfInterest() {
        assignment.setCurrentLocation(locationInEuropeTrail());
        PointOfInterest poi = new PointOfInterest("Puerta del Sol");
        SuspectSeenStatement expectedStatement = new SuspectSeenStatement("The best clue");
        poi.setStatement(expectedStatement);

        Statement clue = assignment.investigatePointOfInterest(poi);

        assertThat(clue).isEqualTo(expectedStatement);
    }

}
