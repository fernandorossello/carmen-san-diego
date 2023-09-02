package app.game.carmensandiego.fixtures;

import app.game.carmensandiego.model.criminals.Criminal;

import static app.game.carmensandiego.model.criminals.characteristics.Auto.*;
import static app.game.carmensandiego.model.criminals.characteristics.Feature.*;
import static app.game.carmensandiego.model.criminals.characteristics.Hair.*;
import static app.game.carmensandiego.model.criminals.characteristics.Hobby.*;
import static app.game.carmensandiego.model.criminals.characteristics.Sex.*;

public class CriminalMother {
    public static Criminal ladyAgatha() {
        return new Criminal("Lady Agatha Wayland", FEMALE, TENNIS, RACE_CAR, RED, JEWELERY);
    }

    public static Criminal scarGraynolt() {
        return new Criminal("Scar Graynolt",MALE, CROQUET,LIMOUSINE, RED, RING);
    }
}
