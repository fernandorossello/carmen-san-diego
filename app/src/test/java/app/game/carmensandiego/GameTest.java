package app.game.carmensandiego;


import app.game.carmensandiego.model.Assignment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GameTest {

    @Mock Output output;


    @Test
    @DisplayName("When asking to displayActions a game should display all the actions available")
    public void displayActions() {
        Assignment assignment = new Assignment();

        Game game = new Game(output, assignment);

        game.displayActions();

        verify(output).println("1. Ver conexiones");
    }
}
