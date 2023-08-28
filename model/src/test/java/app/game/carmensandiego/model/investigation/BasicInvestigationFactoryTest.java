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
    @DisplayName("When crating an investigation it should add the proper statements to the cities, based on the next city")
    void createInvestigation_withStatements() {
        when(citiesRepository.findAll()).thenReturn(Arrays.asList(
                CityMother.paris(),
                CityMother.madrid(),
                CityMother.london())
        );

        Investigation investigation = investigationFactory.create();

        List<City> trail = investigation.getTrail();
        Statement statement1 = trail.get(0).pointsOfInterest().get(0).getStatement();
        assertThat(statement1.toString()).isEqualTo("Se fue para Londres");
        assertThat(statement1).isInstanceOf(SuspectSeenStatement.class);

        Statement statement2 = trail.get(1).pointsOfInterest().get(0).getStatement();
        assertThat(statement2.toString()).isEqualTo("Se fue para Paris");
        assertThat(statement2).isInstanceOf(SuspectSeenStatement.class);

        Statement statement3 = trail.get(2).pointsOfInterest().get(0).getStatement();
        assertThat(statement3.toString()).isEqualTo("Carmen");
        assertThat(statement3).isInstanceOf(SuspectFoundStatement.class);
    }

    @Test
    @DisplayName("When crating an investigation should add a statement of type SuspectNotSeen to the misleading cities")
    void createInvestigation_withStatementsForMisleadingCities() {
        when(citiesRepository.findAll()).thenReturn(Arrays.asList(
                CityMother.paris(),
                CityMother.madrid(),
                CityMother.london(),
                CityMother.bangkok())
        );

        Investigation investigation = investigationFactory.create();

        List<City> cities = investigation.getMisleadingCities();
        List<PointOfInterest> pois = cities.stream().flatMap(c -> c.pointsOfInterest().stream()).toList();

        assertThat(pois).allMatch(poi -> poi.getStatement() instanceof SuspectNotSeenStatement);
    }

    @Test
    @DisplayName("When creating an investigation should add a statement of type SuspectFound to one of the pois in the last city in the trail")
    void createInvestigation_withStatementForLastCity() {
        when(citiesRepository.findAll()).thenReturn(Arrays.asList(
                CityMother.paris(),
                CityMother.madrid(),
                CityMother.london())
        );

        Investigation investigation = investigationFactory.create();

        List<Statement> statements = investigation.getTrail().get(2).pointsOfInterest().stream().map(PointOfInterest::getStatement).toList();

        assertThat(statements.get(0)).isInstanceOf(SuspectFoundStatement.class);
        assertThat(statements.get(1)).isInstanceOf(SuspectNearbyStatement.class);
        assertThat(statements.get(2)).isInstanceOf(SuspectNearbyStatement.class);
    }

}
