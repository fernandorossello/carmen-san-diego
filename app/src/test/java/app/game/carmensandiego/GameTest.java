package app.game.carmensandiego;


import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.PointOfInterest;
import app.game.carmensandiego.model.statement.Statement;
import app.game.carmensandiego.model.statement.SuspectSeenStatement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static app.game.carmensandiego.fixtures.CityMother.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameTest {

    @Mock private Output output;

    @Mock private Assignment assignment;

    public GameTest() {
    }


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

        InOrder inOrder = inOrder(output);
        inOrder.verify(output).println("#############################################");
        inOrder.verify(output).println("ACCIONES: ");
        inOrder.verify(output).println("#############################################");
        inOrder.verify(output).println("1. Ver conexiones");
        inOrder.verify(output).println("2. Viajar");
        inOrder.verify(output).println("3. Investigar");
        inOrder.verify(output).println("0. Salir");
    }

    @Test
    @DisplayName("When asking to execute SeeConnections, the game should execute the expected action")
    public void executeSeeConnectionsAction() throws IOException {
        when(assignment.getAvailableConnections()).thenReturn(List.of(madrid(), paris(), london(), rome()));
        Game game = new Game(output, assignment);

        game.executeAction(1);

        InOrder inOrder = inOrder(output);
        inOrder.verify(output).println("CONEXIONES: ");
        inOrder.verify(output).println("Madrid");
        inOrder.verify(output).println("Paris");
        inOrder.verify(output).println("Londres");
        inOrder.verify(output).println("Roma");
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

    @Test
    @DisplayName("When asking to get points of interest, the game should print the correct names")
    public void getPointOfInterest() {
        when(assignment.getPointOfInterest()).thenReturn(madrid().pointsOfInterest());
        Game game = new Game(output, assignment);

        game.seePointOfInterest();

        InOrder inOrder = inOrder(output);
        inOrder.verify(output).println("PUNTOS DE INTERÉS: ");
        inOrder.verify(output).println("Puerta del Sol");
        inOrder.verify(output).println("Plaza Mayor");
        inOrder.verify(output).println("Palacio Real");
    }

    @Test
    @DisplayName("When investigating a point of interest, should display the proper clue")
    public void investigatePointOfInterest() {
        Game game = new Game(output, assignment);
        PointOfInterest pointOfInterest = new PointOfInterest("Puerta del Sol");
        Statement statement = new SuspectSeenStatement("The best clue");
        when(assignment.investigatePointOfInterest(pointOfInterest)).thenReturn(statement);

        game.investigatePointOfInterest(pointOfInterest);

        verify(output).println("The best clue");
    }
}
