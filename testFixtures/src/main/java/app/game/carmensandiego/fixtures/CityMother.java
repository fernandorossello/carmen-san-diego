package app.game.carmensandiego.fixtures;

import app.game.carmensandiego.model.City;

public class CityMother {
    public static City buenosAires() {
        return new City("Buenos Aires", "Capital de Argentina");
    }

    public static City madrid() {
        return new City("Madrid", "Capital de España");
    }

    public static City london() {
            return new City("Londres", "Capital de Inglaterra");
    }

    public static City paris() {
        return new City("Paris", "Capital de Francia");
    }

    public static City rome() {
        return new City("Roma", "Capital de Italia");
    }

    public static City bangkok() {
        return new City("Bangkok", "Capital de Tailandia");
    }

    public static City tokio() {
        return new City("Tokio", "Capital de Japón");
    }

    public static City beijing() { return new City("Beijing", "Capital de China"); }

    public static City nomPen() { return new City("Nom Pen", "Capital de Camboya"); }

}
