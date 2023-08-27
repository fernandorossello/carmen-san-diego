package app.game.carmensandiego;

import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.investigation.Investigation;
import app.game.carmensandiego.model.investigation.InvestigationFactory;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class GameManager {
    private final Output output;
    private final InvestigationFactory investigationFactory;

    public void play() throws IOException {
        Game game = initGame();

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

    private Game initGame() {
        Investigation investigation = investigationFactory.create();
        Assignment assignment = new Assignment(investigation);
        return new Game(output, assignment);
    }

    private void displayGreetings() {
        output.println("Bienvenido a Carmen Sandiego!");
        output.println("Tu misión es capturar a Carmen Sandiego viajando por el mundo.");
        output.println("Empecemos...");
    }
}
