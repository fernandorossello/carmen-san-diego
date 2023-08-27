package app.game.carmensandiego.model.investigation;


import app.game.carmensandiego.model.cities.City;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Investigation {

    private List<City> trail;
    private List<City> misleadingCities;

    public List<City> getMisleadingCities(int amount) {
        return misleadingCities.subList(0, Math.min(amount, misleadingCities.size()));
    }

    public City getNextCityInTrail(City currentCity) {
        int index = trail.indexOf(currentCity);
        if (index == trail.size() - 1) {
            return null;
        }
        return trail.get(index + 1);
    }

    public City getOriginCity() {
        return trail.get(0);
    }
}
