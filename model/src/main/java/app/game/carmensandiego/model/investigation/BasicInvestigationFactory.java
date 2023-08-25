package app.game.carmensandiego.model.investigation;

import app.game.carmensandiego.model.CitiesRepository;
import app.game.carmensandiego.model.City;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class BasicInvestigationFactory implements InvestigationFactory {
    private static final int AMOUNT_OF_COUNTRIES = 3;
    private final CitiesRepository citiesRepository;
    @Override
    public Investigation create() {
        List<City> cities1 = getCities(AMOUNT_OF_COUNTRIES);
        return new Investigation(cities1);
    }

    private List<City> getCities(int amountOfCountries) {
        List<City> cities = citiesRepository.findAll();
        Collections.shuffle(cities, new Random(System.nanoTime()));
        return cities.subList(0, amountOfCountries);
    }
}
