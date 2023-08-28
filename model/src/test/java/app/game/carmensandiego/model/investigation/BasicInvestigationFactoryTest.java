package app.game.carmensandiego.model.investigation;

import app.game.carmensandiego.fixtures.CityMother;
import app.game.carmensandiego.model.cities.CitiesRepository;
import app.game.carmensandiego.model.cities.City;
import app.game.carmensandiego.model.PointOfInterest;
import app.game.carmensandiego.model.RandomProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasicInvestigationFactoryTest {

    @Mock
    CitiesRepository citiesRepository;

    @Mock
    RandomProvider randomProvider;

    @InjectMocks
    BasicInvestigationFactory investigationFactory;

    @BeforeEach
    void setUp() {
        when(randomProvider.getRnd()).thenReturn(new Random(1));
    }

    @Test
    @DisplayName("When creating an investigation it should be created with a list of cities")
    void createInvestigation_withCities() {
        when(citiesRepository.findAll()).thenReturn(Arrays.asList(
                CityMother.buenosAires(),
                CityMother.madrid(),
                CityMother.london(),
                CityMother.paris())
        );

        Investigation investigation = investigationFactory.create();

        assertThat(investigation.getTrail()).hasSize(3);
    }

    @Test
    @DisplayName("When creating an investigation it should be created with a list of misleading cities. They should be the remaining from the trail ones")
    void createInvestigation_withMisleadingCities() {
        when(citiesRepository.findAll()).thenReturn(Arrays.asList(
                CityMother.buenosAires(),
                CityMother.madrid(),
                CityMother.london(),
                CityMother.paris(),
                CityMother.bangkok(),
                CityMother.tokio())
        );

        Investigation investigation = investigationFactory.create();

        List<City> misleadingCities = investigation.getMisleadingCities();
        assertThat(misleadingCities).hasSize(3);
        assertThat(misleadingCities).doesNotContainAnyElementsOf(investigation.getTrail());
        assertThat(investigation.getTrail()).doesNotContainAnyElementsOf(misleadingCities);
    }

    @Test
    @DisplayName("When crating an investigation it should add the proper clues to the cities, based on the next city")
    void createInvestigation_withClues() {
        when(citiesRepository.findAll()).thenReturn(Arrays.asList(
                CityMother.paris(),
                CityMother.madrid(),
                CityMother.london())
        );

        Investigation investigation = investigationFactory.create();

        List<City> trail = investigation.getTrail();
        assertThat(trail.get(0).pointsOfInterest().get(0).getClue()).isEqualTo("Se fue para Londres");
        assertThat(trail.get(1).pointsOfInterest().get(0).getClue()).isEqualTo("Se fue para Paris");
        assertThat(trail.get(2).pointsOfInterest().get(0).getClue()).isEqualTo("Carmen");
    }

    @Test
    @DisplayName("When crating an investigation should add a clue of type SuspectNotSeen to the misleading cities")
    void createInvestigation_withCluesForMisleadingCities() {
        when(citiesRepository.findAll()).thenReturn(Arrays.asList(
                CityMother.paris(),
                CityMother.madrid(),
                CityMother.london(),
                CityMother.bangkok())
        );

        Investigation investigation = investigationFactory.create();

        List<City> cities = investigation.getMisleadingCities();
        List<PointOfInterest> pois = cities.stream().flatMap(c -> c.pointsOfInterest().stream()).toList();

        //assertThat(pois).allMatch(poi -> poi.getClue() == null);
        assertThat(pois).allMatch(poi -> poi.getClue2() instanceof SuspectNotSeenClue);
    }

}
