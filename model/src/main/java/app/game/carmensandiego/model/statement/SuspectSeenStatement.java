package app.game.carmensandiego.model.statement;

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
