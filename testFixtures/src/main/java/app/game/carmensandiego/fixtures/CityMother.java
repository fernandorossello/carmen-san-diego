package app.game.carmensandiego.fixtures;

import app.game.carmensandiego.model.City;
import app.game.carmensandiego.model.PointOfInterest;

import java.util.Collections;
import java.util.List;


public class CityMother {
    public static City buenosAires() {
        List<PointOfInterest> pois = List.of(new PointOfInterest("Obelisco"), new PointOfInterest("Casa Rosada"), new PointOfInterest("Cementerio de la Recoleta"));
        List<String> clues = Collections.nCopies(3, "Se fue para Buenos Aires");
        return City.builder().name("Buenos Aires").description("Capital de Argentina").pointsOfInterest(pois).clues(clues).build();
    }

    public static City madrid() {
        List<PointOfInterest> pois = List.of(new PointOfInterest("Puerta del Sol"), new PointOfInterest("Plaza Mayor"), new PointOfInterest("Palacio Real"));
        List<String> clues = Collections.nCopies(3, "Se fue para Madrid");
        return City.builder().name("Madrid").description("Capital de España").pointsOfInterest(pois).clues(clues).build();
    }

    public static City london() {
        List<PointOfInterest> pois = List.of(new PointOfInterest("Big Ben"), new PointOfInterest("London Eye"), new PointOfInterest("Palacio de Buckingham"));
        List<String> clues = Collections.nCopies(3, "Se fue para Londres");
        return City.builder().name("Londres").description("Capital de Inglaterra").pointsOfInterest(pois).clues(clues).build();
    }

    public static City paris() {
        List<PointOfInterest> pois = List.of(new PointOfInterest("Torre Eiffel"), new PointOfInterest("Arco del Triunfo"), new PointOfInterest("Museo del Louvre"));
        List<String> clues = Collections.nCopies(3, "Se fue para Paris");
        return City.builder().name("Paris").description("Capital de Francia").pointsOfInterest(pois).clues(clues).build();
    }

    public static City rome() {
        List<PointOfInterest> pois = List.of(new PointOfInterest("Coliseo"), new PointOfInterest("Fontana di Trevi"), new PointOfInterest("Plaza de España"));
        List<String> clues = Collections.nCopies(3, "Se fue para Roma");
        return City.builder().name("Roma").description("Capital de Italia").pointsOfInterest(pois).clues(clues).build();
    }

    public static City bangkok() {
        List<PointOfInterest> pois = List.of(new PointOfInterest("Templo del Buda de Esmeralda"), new PointOfInterest("Templo del Amanecer"), new PointOfInterest("Gran Palacio"));
        List<String> clues = Collections.nCopies(3, "Se fue para Bangkok");
        return City.builder().name("Bangkok").description("Capital de Tailandia").pointsOfInterest(pois).clues(clues).build();
    }

    public static City tokio() {
        List<PointOfInterest> pois = List.of(new PointOfInterest("Torre de Tokio"), new PointOfInterest("Templo de Sensoji"), new PointOfInterest("Palacio Imperial"));
        List<String> clues = Collections.nCopies(3, "Se fue para Tokio");
        return City.builder().name("Tokio").description("Capital de Japón").pointsOfInterest(pois).clues(clues).build();
    }

    public static City beijing() {
        List<PointOfInterest> pois = List.of(new PointOfInterest("Ciudad Prohibida"), new PointOfInterest("Gran Muralla"), new PointOfInterest("Templo del Cielo"));
        List<String> clues = Collections.nCopies(3, "Se fue para Beijing");
        return City.builder().name("Beijing").description("Capital de China").pointsOfInterest(pois).clues(clues).build();
    }

    public static City nomPen() {
        List<PointOfInterest> pois = List.of(new PointOfInterest("Palacio Real"), new PointOfInterest("Museo Nacional"), new PointOfInterest("Templo de Wat Phnom"));
        List<String> clues = Collections.nCopies(3, "Se fue para Nom Pen");
        return City.builder().name("Nom Pen").description("Capital de Camboya").pointsOfInterest(pois).clues(clues).build();
    }

}
