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
    private static final int AMOUNT_OF_CITIES = 3;
    private final CitiesRepository citiesRepository;
    private final RandomProvider randomProvider;
    @Override
    public Investigation create() {
        List<City> allCities = citiesRepository.findAll();
        Collections.shuffle(allCities, randomProvider.getRnd());
        List<City> trail = allCities.subList(0, AMOUNT_OF_CITIES);
        List<City> otherCities = allCities.subList(AMOUNT_OF_CITIES, allCities.size());

        setCluesForTrail(trail);
        setCluesForMisleadingCities(otherCities);

        return new Investigation.InvestigationBuilder()
                .trail(trail)
                .misleadingCities(otherCities)
                .build();
    }

    private void setCluesForMisleadingCities(List<City> otherCities) {
        SuspectNotSeenStatement suspectNotSeenClue = new SuspectNotSeenStatement();
        otherCities.forEach(c -> c.pointsOfInterest()
                .forEach(pointOfInterest -> pointOfInterest.setStatement(suspectNotSeenClue)));
    }

    private void setCluesForTrail(List<City> trail) {
        City current = trail.get(trail.size() - 1);
        current.pointsOfInterest().forEach(poi -> poi.setClue("Carmen"));
        setCluesForLastCityOnTrail(current);
        for (int i = trail.size() - 2; i >= 0; i--) {
            City previous = current;
            current = trail.get(i);
            List<String> clues = previous.clues();
            for(int j = 0; j < current.pointsOfInterest().size(); j++) {
                PointOfInterest poi = current.pointsOfInterest().get(j);
                poi.setClue(clues.get(j));
                poi.setStatement(new SuspectSeenStatement(clues.get(j)));
            }
        }
    }

    private static void setCluesForLastCityOnTrail(City current) {
        current.pointsOfInterest().get(0).setStatement(new SuspectFoundStatement());
        SuspectNearbyStatement suspectNearbyClue = new SuspectNearbyStatement();
        for(int i = 1; i < current.pointsOfInterest().size(); i++) {
            PointOfInterest poi = current.pointsOfInterest().get(i);
            poi.setStatement(suspectNearbyClue);
        }
    }

}
