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
        List<City> allCities = citiesRepository.findAll();
        Collections.shuffle(allCities, new Random(System.nanoTime()));
        List<City> trail = allCities.subList(0, AMOUNT_OF_COUNTRIES);
        List<City> otherCities = allCities.subList(AMOUNT_OF_COUNTRIES, allCities.size());

        return new Investigation.InvestigationBuilder()
                .trail(trail)
                .misleadingCities(otherCities)
                .build();
    }

}
