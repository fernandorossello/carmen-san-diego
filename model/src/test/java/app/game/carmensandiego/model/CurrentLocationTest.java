package app.game.carmensandiego.model;

import app.game.carmensandiego.model.cities.City;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static app.game.carmensandiego.fixtures.CityMother.*;
import static app.game.carmensandiego.fixtures.CurrentLocationMother.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CurrentLocationTest {

    @Test
    @DisplayName("When asking for the available connections, connections should include the previous city")
    void getAvailableConnections() {
        List<City> availableConnections = locationInEuropeTrail().getAvailableConnections();

        assertThat(availableConnections).contains(paris());
        assertThat(availableConnections).hasSize(4);
    }

    @Test
    @DisplayName("When asking for the available connections, connections should include the next city in the trail")
    void getAvailableConnectionsIncludesNextInTrail() {
        List<City> availableConnections = locationInEuropeTrail().getAvailableConnections();

        assertThat(availableConnections).contains(london());
        assertThat(availableConnections).hasSize(4);
    }

    @Test
    @DisplayName("When asking for the available connections, should also display the right misleading cities")
    void getAvailableConnectionsAlsoDisplayOtherOptions() {
        List<City> availableConnections = locationInEuropeTrail().getAvailableConnections();

        assertThat(availableConnections).contains(beijing(), bangkok());
        assertThat(availableConnections).doesNotContain(tokio());
        assertThat(availableConnections).hasSize(4);
    }

    @Test
    @DisplayName("When asking for the available connections on the first location, should not fail because there is no previous city")
    void getAvailableConnectionsOnFirstLocation() {
        List<City> availableConnections = initialLocationInEuropeTrail().getAvailableConnections();

        assertThat(availableConnections).hasSize(4);
        assertThat(availableConnections).containsExactlyInAnyOrder(madrid(), beijing(), bangkok(), tokio());
    }

    @Test
    @DisplayName("When asking for the available connections should return always the cities in the same order")
    void getAvailableConnectionsAlwaysSameOrder() {
        CurrentLocation location = locationInEuropeTrail();
        List<City> availableConnections = location.getAvailableConnections();
        List<City> availableConnections2 = location.getAvailableConnections();

        assertThat(availableConnections).isEqualTo(availableConnections2);
    }

    @Test
    @DisplayName("When asking for the available connections at the end of the trail, should return the previous city and other options")
    void getAvailableConnectionsLastCityInTrail() {
        List<City> availableConnections = locationAtTheEndOfEuropeTrail().getAvailableConnections();

        assertThat(availableConnections).hasSize(4);
        assertThat(availableConnections).containsExactlyInAnyOrder(madrid(), beijing(), bangkok(), tokio());
    }

    @Test
    @DisplayName("When asking for the available connections in a city outside of the trail,  should return the previous city and other options")
    void getAvailableConnectionsOutsideOfTrail() {
        List<City> availableConnections = locationOutsideTheEuropeTrail().getAvailableConnections();

        assertThat(availableConnections).hasSize(4);
        assertThat(availableConnections).containsExactlyInAnyOrder(paris(), nomPen(), bangkok(), tokio());
    }

    @Test
    @DisplayName("When having a misleading city as previous city, should not include it in the available connections twice. (BUG)")
    void getAvailableConnectionsWithPreviousMisleadingCity() {
        CurrentLocation currentLocation = CurrentLocation.builder()
                .currentCity(tokio())
                .previousCity(Optional.of(bangkok()))
                .cityOptions(List.of(bangkok(), madrid(), london(), rome()))
                .nextInTrail(Optional.of(beijing()))
                .build();

        List<City> cities = currentLocation.getAvailableConnections();

        assertThat(cities).containsExactlyInAnyOrder(bangkok(), madrid(), london(), beijing());
    }

}