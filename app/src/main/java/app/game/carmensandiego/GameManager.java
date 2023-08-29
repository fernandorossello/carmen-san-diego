package app.game.carmensandiego;

import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.investigation.GameConfiguration;
import app.game.carmensandiego.model.investigation.Investigation;
import app.game.carmensandiego.model.investigation.InvestigationFactory;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class GameManager {
    private final Output output;
    private final InvestigationFactory investigationFactory;
    private final GameConfiguration config;

    public void play() throws IOException, InterruptedException {
        Game game = initGame(config);

        displayGreetings();

        game.displayActions();
        int action = readOption();
        while (action != 0) {
            game.executeAction(action);
            game.displayActions();
            action = readOption();
        }
    }

    private int readOption() {
        return Integer.parseInt(output.readInput());
    }

    private Game initGame(GameConfiguration config) {
        Investigation investigation = investigationFactory.create(config);
        Assignment assignment = new Assignment(investigation);
        return new Game(output, assignment);
    }

    private void displayGreetings() {
        output.println("Bienvenido a Carmen Sandiego!");
        output.println("Tu misión es capturar a Carmen Sandiego viajando por el mundo.");
        output.println("Empecemos...");
    }
}
