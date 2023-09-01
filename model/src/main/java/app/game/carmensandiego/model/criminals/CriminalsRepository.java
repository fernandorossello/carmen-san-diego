package app.game.carmensandiego.model.criminals;

import java.util.List;

public interface CriminalsRepository {
    List<Criminal> findAll();
}
