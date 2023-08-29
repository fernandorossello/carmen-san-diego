package app.game.carmensandiego.model.investigation;


import app.game.carmensandiego.model.cities.City;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Builder
@Getter
public class Investigation {

    private List<City> trail;
    private List<City> misleadingCities;
    private int dueHours;

    public Optional<City> getNextCityInTrail(City currentCity) {
        int index = trail.indexOf(currentCity);

        if (index == -1) {
            return Optional.empty();
        }

        if (index == trail.size() - 1) {
            return Optional.empty();
        }
        return Optional.of(trail.get(index + 1));
    }

    public City getOriginCity() {
        return trail.get(0);
    }
}
