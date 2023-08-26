package app.game.carmensandiego;


import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.City;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GameTest {

    @Mock Output output;

    @Test
    @DisplayName("When asking to for the name of the current location, the game should display it correctly")
    public void currentLocationName() {
        Assignment assignment = new Assignment();
        Game game = new Game(output, assignment);
        String cityName = "Buenos Aires";
        assignment.setCurrentLocation(new City(cityName, "Capital de Argentina"));

        game.currentLocationName();

        verify(output).println(cityName);
    }


    @Test
    @DisplayName("When asking to for the name of the current location, the game should display it correctly")
    public void currentLocationDescription() {
        Assignment assignment = new Assignment();
        Game game = new Game(output, assignment);
        String cityName = "Buenos Aires";
        String cityDescription = "Capital de Argentina";
        assignment.setCurrentLocation(new City(cityName, cityDescription));

        game.currentLocationDescription();

        verify(output).println(cityDescription);
    }

    @Test
    @DisplayName("When asking to displayActions a game should display all the actions available")
    public void displayActions() {
        Assignment assignment = new Assignment();

        Game game = new Game(output, assignment);

        game.displayActions();

        verify(output).println("1. Ver conexiones");
    }

    @Test
    @Disabled
    @DisplayName("When asking to execute SeeConnections, the game should execute the expected action")
    public void executeSeeConnectionsAction() {
        Assignment assignment = new Assignment();
        Game game = new Game(output, assignment);

        game.executeAction(1);

        InOrder inOrder = org.mockito.Mockito.inOrder(output);
        inOrder.verify(output).println("Conexiones: ");
        inOrder.verify(output).println("1. Buenos Aires");
        inOrder.verify(output).println("2. Madrid");
        inOrder.verify(output).println("3. Londres");
    }

}
