package app.game.carmensandiego.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static app.game.carmensandiego.fixtures.CityMother.*;
import static app.game.carmensandiego.fixtures.CurrentLocationMother.initialLocationMadrid;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CurrentLocationTest {

    CurrentLocation location = CurrentLocation.builder()
            .previousCity(buenosAires())
            .currentCity(madrid())
            .nextInTrail(london())
            .cityOptions(List.of(paris(), bangkok(), tokio()))
            .build();

    CurrentLocation initialLocation = CurrentLocation.builder()
            .currentCity(madrid())
            .nextInTrail(london())
            .cityOptions(List.of(paris(), bangkok(), tokio()))
            .build();

    @Test
    @DisplayName("When asking for the available connections, connections should include the previous city")
    void getAvailableConnections() {
        List<City> availableConnections = location.getAvailableConnections();

        assertThat(availableConnections).contains(buenosAires());
        assertThat(availableConnections).hasSize(4);
    }

    @Test
    @DisplayName("When asking for the available connections, connections should include the next city in the trail")
    void getAvailableConnectionsIncludesNextInTrail() {
        List<City> availableConnections = location.getAvailableConnections();

        assertThat(availableConnections).contains(london());
        assertThat(availableConnections).hasSize(4);
    }

    @Test
    @DisplayName("When asking for the available connections, should also display the right misleading cities")
    void getAvailableConnectionsAlsoDisplayOtherOptions() {
        List<City> availableConnections = location.getAvailableConnections();

        assertThat(availableConnections).contains(paris(), bangkok());
        assertThat(availableConnections).doesNotContain(tokio());
        assertThat(availableConnections).hasSize(4);
    }

    @Test
    @DisplayName("When asking for the available connections on the first location, should not fail because there is no previous city")
    void getAvailableConnectionsOnFirstLocation() {
        List<City> availableConnections = initialLocation.getAvailableConnections();

        assertThat(availableConnections).hasSize(4);
        assertThat(availableConnections).containsExactlyInAnyOrder(london(), paris(), bangkok(), tokio());
    }

    @Test
    @DisplayName("When asking for the available connections should return always the cities in the same order")
    void getAvailableConnectionsAlwaysSameOrder() {
        List<City> availableConnections = location.getAvailableConnections();
        List<City> availableConnections2 = location.getAvailableConnections();

        assertThat(availableConnections).isEqualTo(availableConnections2);
    }

    //TODO: Add test for last city in trail
    //TODO: Add test for city not in trail (same amount of options)


}