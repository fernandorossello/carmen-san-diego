package app.game.carmensandiego.model.investigation;

import app.game.carmensandiego.fixtures.CityMother;
import app.game.carmensandiego.fixtures.CriminalMother;
import app.game.carmensandiego.model.cities.CitiesRepository;
import app.game.carmensandiego.model.cities.City;
import app.game.carmensandiego.model.PointOfInterest;
import app.game.carmensandiego.model.RandomProvider;
import app.game.carmensandiego.model.criminals.CriminalsRepository;
import app.game.carmensandiego.model.statement.*;
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
    private RandomProvider randomProvider;
    @Mock
    private CriminalsRepository criminalsRepository;

    @InjectMocks
    BasicInvestigationFactory investigationFactory;

    GameConfiguration gameConfiguration = GameConfiguration.builder().numberOfCitiesInTrail(3).dueHours(10).build();

    @BeforeEach
    void setUp() {
        when(randomProvider.getRnd()).thenReturn(new Random(1));
        when(criminalsRepository.findAll()).thenReturn(List.of(CriminalMother.carmenSanDiego()));
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

        Investigation investigation = investigationFactory.create(gameConfiguration);

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

        Investigation investigation = investigationFactory.create(gameConfiguration);

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

        Investigation investigation = investigationFactory.create(gameConfiguration);

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

        Investigation investigation = investigationFactory.create(gameConfiguration);

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

        Investigation investigation = investigationFactory.create(gameConfiguration);

        List<Statement> statements = investigation.getTrail().get(2).pointsOfInterest().stream().map(PointOfInterest::getStatement).toList();

        assertThat(statements.get(0)).isInstanceOf(SuspectFoundStatement.class);
        assertThat(statements.get(1)).isInstanceOf(SuspectNearbyStatement.class);
        assertThat(statements.get(2)).isInstanceOf(SuspectNearbyStatement.class);
    }

    @Test
    @DisplayName("When creating an investigation, should create it with the amount of due hours from the config")
    void createInvestigation_withHoursToSpend() {
        when(citiesRepository.findAll()).thenReturn(Arrays.asList(
                CityMother.paris(),
                CityMother.madrid(),
                CityMother.london())
        );

        Investigation investigation = investigationFactory.create(gameConfiguration);

        assertThat(investigation.getDueHours()).isEqualTo(10);
    }

    @Test
    @DisplayName("When creating an investigation, should create it with a criminal assigned to it")
    void createInvestigation_withCriminal() {
        when(citiesRepository.findAll()).thenReturn(Arrays.asList(
                CityMother.paris(),
                CityMother.madrid(),
                CityMother.london())
        );

        Investigation investigation = investigationFactory.create(gameConfiguration);

        assertThat(investigation.getCriminal()).isEqualTo(CriminalMother.carmenSanDiego());
    }

}
