package app.game.carmensandiego.model.investigation;

import app.game.carmensandiego.model.CitiesRepository;
import app.game.carmensandiego.model.City;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

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
                new City("Buenos Aires", "descripcion Buenos Aires"),
                new City("Madrid", "descripcion Madrid"),
                new City("Londres", "descripcion Londres"),
                new City("Paris", "descripcion Paris"))
        );

        Investigation investigation = investigationFactory.create();

        assertThat(investigation.cities()).hasSize(3);
    }
}
