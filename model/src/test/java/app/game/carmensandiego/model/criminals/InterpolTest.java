package app.game.carmensandiego.model.criminals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static app.game.carmensandiego.fixtures.CriminalMother.*;
import static app.game.carmensandiego.model.criminals.characteristics.Hobby.*;
import static app.game.carmensandiego.model.criminals.characteristics.Sex.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class InterpolTest {

    private Interpol interpol;

    @BeforeEach
    void setUp() {
        List<Criminal> knownCriminals = List.of(ladyAgatha(), scarGraynolt());
        interpol = new Interpol(knownCriminals);
    }

    @Test
    @DisplayName("When searching by characteristics, should find all the criminals and filter the ones that match the characteristics")
    void searchByCharacteristics() {
        var criminals = interpol.findByCharacteristics(Optional.of(FEMALE), Optional.of(TENNIS),Optional.empty(),Optional.empty(),Optional.empty());

        assertThat(criminals).containsExactly(ladyAgatha());
    }

    @Test
    @DisplayName("When searching by characteristics, if there is no match, should return an empty list")
    void searchByCharacteristicsWhenThereIsNoMatch() {
        var criminals = interpol.findByCharacteristics(Optional.of(MALE), Optional.of(TENNIS),Optional.empty(),Optional.empty(),Optional.empty());

        assertThat(criminals).isEmpty();
    }

    @Test
    @DisplayName("When searching by characteristics, if any characteristic is null, should not fail and search by the other characteristics")
    void searchByCharacteristicsWhenEmptyCharacteristic() {
        var criminals = interpol.findByCharacteristics(Optional.of(MALE), Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());

        assertThat(criminals).containsExactly(scarGraynolt());
    }

    @Test
    @DisplayName("When searching by characteristics, if all characteristics are empty, should return all the criminals")
    void searchByCharacteristicsWhenAllCharacteristicsAreEmpty() {
        var criminals = interpol.findByCharacteristics(Optional.empty(), Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());

        assertThat(criminals).containsExactlyInAnyOrder(ladyAgatha(), scarGraynolt());
    }

}