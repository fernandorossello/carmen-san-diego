package app.game.carmensandiego;

import java.util.Scanner;

public class ConsoleOutput implements Output {
    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
