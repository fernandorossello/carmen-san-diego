package app.game.carmensandiego.model.criminals;

import app.game.carmensandiego.model.criminals.characteristics.Auto;
import app.game.carmensandiego.model.criminals.characteristics.Feature;
import app.game.carmensandiego.model.criminals.characteristics.Hair;

import java.util.List;

import static app.game.carmensandiego.model.criminals.characteristics.Hobby.*;
import static app.game.carmensandiego.model.criminals.characteristics.Sex.*;

public class InMemoryCriminalsRepository implements CriminalsRepository {
    @Override
    public List<Criminal> findAll() {
        return List.of(new Criminal("Lady Agatha Wayland", FEMALE, TENNIS, Auto.RACE_CAR, Hair.RED, Feature.JEWELERY));
    }
}
