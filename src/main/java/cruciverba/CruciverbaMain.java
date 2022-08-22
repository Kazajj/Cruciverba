package cruciverba;

import cruciverba.controller.Controller;
import cruciverba.dizionario.Dizionario;
import cruciverba.element.GameListener;
import cruciverba.graphic.ScreenView;
import cruciverba.model.ModelGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CruciverbaMain {

    public static void main(String[] args) throws IOException {
        int x = 18, y = 11, neri = 27;

        ModelGame modelGame = new ModelGame();
        Controller controller = new Controller(modelGame);
        GameListener listener = new ScreenView(controller);
        controller.setListener(listener);

        controller.chooseGridSize(x, y, neri);
        Dizionario dizionario = new Dizionario();
    }

}
