package app.game.carmensandiego.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class CurrentLocation {

    private final City previousCity;
    private final City currentCity;

    private final List<City> cityOptions = new ArrayList<>();
    public void addCityOptions(List<City> cityOptions) {
        this.cityOptions.addAll(cityOptions);
    }
}
