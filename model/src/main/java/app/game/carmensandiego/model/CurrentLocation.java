package app.game.carmensandiego.model;

import lombok.Builder;

@Builder
public record CurrentLocation(City previousCity, City currentCity) {
}
