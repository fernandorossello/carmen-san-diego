package app.game.carmensandiego.model.criminals;

import app.game.carmensandiego.model.criminals.characteristics.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class Interpol {

    private final List<Criminal> criminals;
    List<Criminal> findByCharacteristics(Optional<Sex> sex, Optional<Hobby> hobby, Optional<Hair> hair, Optional<Feature> feature, Optional<Auto> auto) {
        return criminals.stream().filter(c ->
                (sex.isEmpty() || c.sex().equals(sex.get())) &&
                (hobby.isEmpty() || c.hobby().equals(hobby.get()) &&
                (hair.isEmpty() || c.hair().equals(hair.get())) &&
                (feature.isEmpty() || c.feature().equals(feature.get())) &&
                (auto.isEmpty() || c.auto().equals(auto.get()))
                )
        ).toList();
    }
}
