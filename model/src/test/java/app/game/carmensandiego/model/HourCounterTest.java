package app.game.carmensandiego.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HourCounterTest {

    @Test
    @DisplayName("When creating an hour counter, should set the initial hour to the given value")
    void createHourCounter() {
        HourCounter hourCounter = new HourCounter(10);

        assertEquals(10, hourCounter.getHoursLeft());
    }

    @Test
    @DisplayName("When spending hours, should decrease the hours left by the given value")
    void spendHours() {
        HourCounter hourCounter = new HourCounter(4);

        hourCounter.spendHours(3);

        assertThat(hourCounter.getHoursLeft()).isEqualTo(1);
    }

    @Test
    @DisplayName("When no more hours left, should say time up is true")
    void timeUp() {
        HourCounter hourCounter = new HourCounter(0);

        assertTrue(hourCounter.isTimeUp());
    }

    @Test
    @DisplayName("When there are hours left, should say time up is false")
    void timeUp_false() {
        HourCounter hourCounter = new HourCounter(1);

        assertFalse(hourCounter.isTimeUp());
    }
}