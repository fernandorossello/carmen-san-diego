package app.game.carmensandiego.model.investigation;

public class SuspectSeenStatement implements Statement {

    private final String clue;
    public SuspectSeenStatement(String clue) {
        this.clue = clue;
    }

    @Override
    public String toString() {
        return clue;
    }
}
