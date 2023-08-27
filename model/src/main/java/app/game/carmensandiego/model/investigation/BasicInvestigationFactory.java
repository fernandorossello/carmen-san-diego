package app.game.carmensandiego.model.investigation;

import app.game.carmensandiego.model.cities.CitiesRepository;
import app.game.carmensandiego.model.cities.City;
import app.game.carmensandiego.model.PointOfInterest;
import app.game.carmensandiego.model.RandomProvider;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class BasicInvestigationFactory implements InvestigationFactory {
    private static final int AMOUNT_OF_COUNTRIES = 3;
    private final CitiesRepository citiesRepository;
    private final RandomProvider randomProvider;
    @Override
    public Investigation create() {
        List<City> allCities = citiesRepository.findAll();
        Collections.shuffle(allCities, randomProvider.getRnd());
        List<City> trail = allCities.subList(0, AMOUNT_OF_COUNTRIES);
        List<City> otherCities = allCities.subList(AMOUNT_OF_COUNTRIES, allCities.size());

        setCluesForTrail(trail);

        return new Investigation.InvestigationBuilder()
                .trail(trail)
                .misleadingCities(otherCities)
                .build();
    }

    private static void setCluesForTrail(List<City> trail) {
        City current = trail.get(trail.size() - 1);
        current.pointsOfInterest().forEach(poi -> poi.setClue("Carmen"));
        for (int i = trail.size() - 2; i >= 0; i--) {
            City previous = current;
            current = trail.get(i);
            List<String> clues = previous.clues();
            for(int j = 0; j < current.pointsOfInterest().size(); j++) {
                PointOfInterest poi = current.pointsOfInterest().get(j);
                poi.setClue(clues.get(j));
            }
        }
    }

}
