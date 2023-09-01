package app.game.carmensandiego.model.criminals;

import java.util.List;

public class InMemoryCriminalsRepository implements CriminalsRepository {
    @Override
    public List<Criminal> findAll() {
        return List.of(new Criminal("Carmen Sandiego"));
    }
}
