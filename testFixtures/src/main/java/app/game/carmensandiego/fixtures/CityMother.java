package app.game.carmensandiego.fixtures;

import app.game.carmensandiego.model.City;

public class CityMother {

    public static City buenosAires() {
        return new City("Buenos Aires", "Capital de Argentina");
    }

    public static City madrid() {
        return new City("Madrid", "Capital de España");
    }

    public static City londres() {
            return new City("Londres", "Capital de Inglaterra");
    }

    public static City paris() {
        return new City("Paris", "Capital de Francia");
    }
}
