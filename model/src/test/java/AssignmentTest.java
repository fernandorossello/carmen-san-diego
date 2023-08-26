import app.game.carmensandiego.fixtures.CityMother;
import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.City;
import app.game.carmensandiego.model.investigation.Investigation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static app.game.carmensandiego.fixtures.CityMother.*;
import static app.game.carmensandiego.fixtures.CurrentLocationMother.*;
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
    @DisplayName("When asking for the current location name and description, should provide the right values")
    void getCurrentLocationName() {
        assignment.setCurrentLocation(madridFromBuenosAires());

        assertThat(assignment.getCurrentLocationDescription()).isEqualTo(madrid.description());
        assertThat(assignment.getCurrentLocationName()).isEqualTo(madrid.name());
    }

    @Test
    @DisplayName("When traveling to a city, should update the current location")
    void travel_updateCurrentLocation() {
        assignment.setCurrentLocation(madridFromBuenosAiresWithEuropeOptions());

        assignment.travelTo(london);

        assertThat(assignment.getCurrentLocationName()).isEqualTo(london.name());
    }

    @Test
    @DisplayName("When traveling to a city, should update the previous location with the current location")
    void travel_updatePreviousLocation() {
        assignment.setCurrentLocation(madridFromBuenosAiresWithEuropeOptions());

        assignment.travelTo(london);

        assertThat(assignment.getCurrentLocation().getPreviousCity()).isEqualTo(madrid);
    }

    @Test
    @DisplayName("When traveling to a city, should update the city connections options from the investigation")
    void travel_updateCityConnectionsOptions() {
        assignment.setCurrentLocation(madridFromBuenosAiresWithEuropeOptions());
        when(investigation.getMisleadingCities(4)).thenReturn(List.of(bangkok, tokio,rome(),paris()));

        assignment.travelTo(london);

        assertThat(assignment.getAvailableConnections()).containsExactlyInAnyOrder(madrid, bangkok, tokio);
    }

    @Test
    @DisplayName("When traveling to a city that is not in the options, should throw an exception")
    void travel_toCityNotInOptions() {
        assignment.setCurrentLocation(initialLocationMadrid());

        assertThat(assignment.getAvailableConnections()).doesNotContain(buenosAires);
        assertThatThrownBy(() -> assignment.travelTo(buenosAires))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("City is not in the available connections");
    }

}
