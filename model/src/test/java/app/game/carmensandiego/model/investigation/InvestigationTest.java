package app.game.carmensandiego.model.investigation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static app.game.carmensandiego.fixtures.CityMother.*;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class InvestigationTest {
    @Test
    @DisplayName("When getting the next city in the trail, should return the next one")
    void getNextCityInTrail() {
        Investigation investigation = anInvestigation();

        assertThat(investigation.getNextCityInTrail(madrid())).isEqualTo(Optional.of(london()));
    }

    @Test
    @DisplayName("When getting the next city in the trail, if its the last city should return null")
    void getNextCityInTrail_lastCity() {
        Investigation investigation = anInvestigation();

        assertThat(investigation.getNextCityInTrail(paris())).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("When getting the next city in the trail, if the city is the first one should return the next city correctly")
    void getNextCityInTrail_firstCity() {
        Investigation investigation = anInvestigation();

        assertThat(investigation.getNextCityInTrail(buenosAires())).isEqualTo(Optional.of(madrid()));
    }

    @Test
    @DisplayName("When getting the next city in the trail, if the city is not in the trail should return null")
    void getNextCityInTrail_notInTrail() {
        Investigation investigation = anInvestigation();

        assertThat(investigation.getNextCityInTrail(tokio())).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("When getting the origin city, should return the first city in the trail")
    void getOriginCity() {
        Investigation investigation = anInvestigation();

        assertThat(investigation.getOriginCity()).isEqualTo(buenosAires());
    }

    private static Investigation anInvestigation() {
        return Investigation.builder()
                .trail(List.of(buenosAires(), madrid(), london(), paris()))
                .misleadingCities(List.of(tokio(), bangkok()))
                .dueHours(10)
                .build();
    }

}