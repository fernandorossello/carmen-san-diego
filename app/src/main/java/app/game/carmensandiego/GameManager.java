package app.game.carmensandiego;

import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.investigation.GameConfiguration;
import app.game.carmensandiego.model.investigation.Investigation;
import app.game.carmensandiego.model.investigation.InvestigationFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameManager {
    private final Output output;
    private final InvestigationFactory investigationFactory;
    private final GameConfiguration config;

    public void play() throws InterruptedException {
        Game game = initGame(config);

        displayGreetings();

        game.displayActions();
        int action = readOption();
        while (action != 0) {
            game.executeAction(action);

            if(game.isTimeOver()) {
                displayGameOver();
                break;
            }

            game.displayActions();
            action = readOption();
        }
    }

    private void displayGameOver() {
        output.println("#############################################");
        output.println("#############################################");
        output.println("Ha pasado demasiado tiempo y la persona que venías siguiendo logró escapar");
        output.println("Mejor suerte la próxima vez");
        output.println("#############################################");
        output.println("#############################################");
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
