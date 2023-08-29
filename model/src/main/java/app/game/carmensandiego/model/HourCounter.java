package app.game.carmensandiego.model;

import lombok.Getter;

@Getter
public class HourCounter {

    private int hoursLeft;

    public HourCounter(int hoursLeft) {
        this.hoursLeft = hoursLeft;
    }

    public void spendHours(int hours) {
        this.hoursLeft -= hours;
    }

    public boolean isTimeOver() {
        return hoursLeft <= 0;
    }
}
