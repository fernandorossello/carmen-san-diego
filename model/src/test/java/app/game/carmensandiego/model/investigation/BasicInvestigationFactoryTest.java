package app.game.carmensandiego.model.investigation;

import app.game.carmensandiego.fixtures.CityMother;
import app.game.carmensandiego.model.CitiesRepository;
import app.game.carmensandiego.model.City;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasicInvestigationFactoryTest {

    @Mock
    CitiesRepository citiesRepository;

    @InjectMocks
    BasicInvestigationFactory investigationFactory;

    @Test
    @DisplayName("When creating an investigation it should be created with a list of cities")
    void createInvestigation_withCities() {
        when(citiesRepository.findAll()).thenReturn(Arrays.asList(
                CityMother.buenosAires(),
                CityMother.madrid(),
                CityMother.londres(),
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
                CityMother.londres(),
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
}
