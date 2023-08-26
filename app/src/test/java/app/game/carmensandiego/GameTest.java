package app.game.carmensandiego;


import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.CurrentLocation;
import app.game.carmensandiego.model.investigation.Investigation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static app.game.carmensandiego.Game.Actions.SEE_CONNECTIONS;
import static app.game.carmensandiego.fixtures.CityMother.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameTest {

    @Mock private Output output;
    @Mock private Investigation investigation;

    @Mock private CurrentLocation currentLocation;

    @Mock private Assignment assignment;


    @Test
    @DisplayName("When asking to for the name of the current location, the game should display it correctly")
    public void currentLocationName() {
        when(assignment.getCurrentLocationName()).thenReturn(paris().name());
        Game game = new Game(output, assignment);

        game.currentLocationName();

        verify(output).println(paris().name());
    }


    @Test
    @DisplayName("When asking to for the name of the current location, the game should display it correctly")
    public void currentLocationDescription() {
        Game game = new Game(output, assignment);
        when(assignment.getCurrentLocationDescription()).thenReturn(madrid().description());

        game.currentLocationDescription();

        verify(output).println(madrid().description());
    }

    @Test
    @DisplayName("When asking to displayActions a game should display all the actions available")
    public void displayActions() {
        Game game = new Game(output, assignment);

        game.displayActions();

        verify(output).println("1. Ver conexiones");
    }

    @Test
    @DisplayName("When asking to execute SeeConnections, the game should execute the expected action")
    public void executeSeeConnectionsAction() {
        when(assignment.getAvailableConnections()).thenReturn(List.of(madrid(), paris(), london(), rome()));
        Game game = new Game(output, assignment);

        game.executeAction(SEE_CONNECTIONS);

        verify(output).println("Conexiones: ");
        verify(output).println("Madrid");
        verify(output).println("Paris");
        verify(output).println("Londres");
        verify(output).println("Roma");
    }

    @Test
    @DisplayName("When asking to execute TravelAction, the game should execute the expected action")
    public void executeTravelAction() {
        when(assignment.getCurrentLocationName()).thenReturn(london().name());
        when(assignment.getCurrentLocationDescription()).thenReturn(london().description());
        Game game = new Game(output, assignment);

        game.travelTo(london());

        verify(assignment).travelTo(london());
        verify(output).println("Bienvenido a Londres. Capital de Inglaterra");
    }
}
