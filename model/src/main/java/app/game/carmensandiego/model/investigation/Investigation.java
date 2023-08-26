package app.game.carmensandiego.model.investigation;


import app.game.carmensandiego.model.City;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Investigation {

    private List<City> trail;
    private List<City> misleadingCities;

    public List<City> getMisleadingCities(int amount) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public City getNextCityInTrail(City currentCity) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public boolean isInTrail(City currentCity) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
