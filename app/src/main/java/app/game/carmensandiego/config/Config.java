package app.game.carmensandiego.config;

import app.game.carmensandiego.ConsoleOutput;
import app.game.carmensandiego.GameManager;
import app.game.carmensandiego.Output;
import app.game.carmensandiego.model.cities.CitiesRepository;
import app.game.carmensandiego.model.RandomProvider;
import app.game.carmensandiego.model.cities.InMemoryCitiesRepository;
import app.game.carmensandiego.model.criminals.CriminalsRepository;
import app.game.carmensandiego.model.criminals.InMemoryCriminalsRepository;
import app.game.carmensandiego.model.investigation.BasicInvestigationFactory;
import app.game.carmensandiego.model.investigation.GameConfiguration;
import app.game.carmensandiego.model.investigation.InvestigationFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public GameConfiguration gameConfiguration() {
        int numberOfCitiesInTrail = 3;
        int dueHours = numberOfCitiesInTrail * (4 + 8) + 8;
        return GameConfiguration.builder()
                .numberOfCitiesInTrail(numberOfCitiesInTrail)
                .dueHours(dueHours)
                .build();
    }

    @Bean
    public Output output() {
        return new ConsoleOutput();
    }

    @Bean
    public CitiesRepository citiesRepository() {
        return new InMemoryCitiesRepository();
    }

    @Bean
    public CriminalsRepository criminalsRepository() {
        return new InMemoryCriminalsRepository();
    }

    @Bean
    public InvestigationFactory investigationFactory(CitiesRepository citiesRepository, CriminalsRepository criminalsRepository) {
        RandomProvider randomProvider = new RandomProvider();

        return new BasicInvestigationFactory(citiesRepository, randomProvider, criminalsRepository);
    }

    @Bean
    public GameManager gameManager(Output output, InvestigationFactory investigationFactory, GameConfiguration gameConfiguration) {
        return new GameManager(output, investigationFactory, gameConfiguration);
    }
}
