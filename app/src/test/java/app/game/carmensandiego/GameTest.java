package app.game.carmensandiego;


import app.game.carmensandiego.fixtures.CityMother;
import app.game.carmensandiego.model.Assignment;
import app.game.carmensandiego.model.CitiesRepository;
import app.game.carmensandiego.model.City;
import app.game.carmensandiego.model.investigation.BasicInvestigationFactory;
import app.game.carmensandiego.model.investigation.Investigation;
import app.game.carmensandiego.model.investigation.InvestigationFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static app.game.carmensandiego.Game.Actions.SEE_CONNECTIONS;
import static app.game.carmensandiego.fixtures.CityMother.londres;
import static app.game.carmensandiego.fixtures.CurrentLocationMother.madridFromBuenosAires;
import static app.game.carmensandiego.fixtures.CurrentLocationMother.madridFromBuenosAiresWithEuropeOptions;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GameTest {
    private final City madrid = CityMother.madrid();

    @Mock Output output;
    @Mock Investigation investigation;

    private Assignment assignment;


    @BeforeEach
    void setUp() {
        assignment = new Assignment(investigation);
    }

    @Test
    @DisplayName("When asking to for the name of the current location, the game should display it correctly")
    public void currentLocationName() {
        Game game = new Game(output, assignment);
        assignment.setCurrentLocation(madridFromBuenosAires());

        game.currentLocationName();

        verify(output).println(madrid.name());
    }


    @Test
    @DisplayName("When asking to for the name of the current location, the game should display it correctly")
    public void currentLocationDescription() {
        Game game = new Game(output, assignment);
        assignment.setCurrentLocation(madridFromBuenosAires());

        game.currentLocationDescription();

        verify(output).println(madrid.description());
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
        assignment.setCurrentLocation(madridFromBuenosAiresWithEuropeOptions());
        Game game = new Game(output, assignment);

        game.executeAction(SEE_CONNECTIONS);

        InOrder inOrder = org.mockito.Mockito.inOrder(output);
        inOrder.verify(output).println("Conexiones: ");
        inOrder.verify(output).println("Buenos Aires");
        inOrder.verify(output).println("Londres");
        inOrder.verify(output).println("Paris");
    }

    @Test
    @DisplayName("When asking to execute TravelAction, the game should execute the expected action")
    public void executeTravelAction() {
        assignment.setCurrentLocation(madridFromBuenosAiresWithEuropeOptions());
        Game game = new Game(output, assignment);

        game.travelTo(londres());

        verify(output).println("Bienvenido a Londres. Capital de Inglaterra");
    }
}
