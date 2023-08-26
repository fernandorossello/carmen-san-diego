package app.game.carmensandiego;


import app.game.carmensandiego.fixtures.CityMother;
import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.City;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static app.game.carmensandiego.fixtures.CurrentLocationMother.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GameTest {

    @Mock Output output;

    private final City madrid = CityMother.madrid();

    @Test
    @DisplayName("When asking to for the name of the current location, the game should display it correctly")
    public void currentLocationName() {
        Assignment assignment = new Assignment();
        Game game = new Game(output, assignment);
        assignment.setCurrentLocation(madridFromBuenosAires());

        game.currentLocationName();

        verify(output).println(madrid.name());
    }


    @Test
    @DisplayName("When asking to for the name of the current location, the game should display it correctly")
    public void currentLocationDescription() {
        Assignment assignment = new Assignment();
        Game game = new Game(output, assignment);
        assignment.setCurrentLocation(madridFromBuenosAires());

        game.currentLocationDescription();

        verify(output).println(madrid.description());
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
    @DisplayName("When asking to execute SeeConnections, the game should execute the expected action")
    public void executeSeeConnectionsAction() {
        Assignment assignment = new Assignment();
        assignment.setCurrentLocation(madridFromBuenosAires());
        Game game = new Game(output, assignment);

        game.executeAction(1);

        InOrder inOrder = org.mockito.Mockito.inOrder(output);
        inOrder.verify(output).println("Conexiones: ");
        inOrder.verify(output).println("Buenos Aires");
        inOrder.verify(output).println("Madrid");
        inOrder.verify(output).println("Londres");
    }
}
