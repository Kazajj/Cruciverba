package cruciverba.controller;

import cruciverba.dizionario.Dizionario;
import cruciverba.element.GameListener;
import cruciverba.graphic.ScreenView;
import cruciverba.model.ModelGame;
import cruciverba.objects.Box;

import java.awt.*;
import java.util.Optional;
import java.util.regex.Pattern;

public class Controller {

    ModelGame modelGame;
    GameListener listener;
    Dizionario dizionario = new Dizionario();

    public Controller(ModelGame modelGame) {
        this.modelGame = modelGame;
        this.listener = null;
    }

    public void setListener(GameListener listener) {
        this.listener = listener;
    }

    public void chooseGridSize(int x, int y, int neri) {
        modelGame.setX(x);
        modelGame.setY(y);
        modelGame.setNeri(neri);
        modelGame.generateBoxes();
        listener.generateGamePanel();
        listener.generateDebug();

        generateNeri();
        generateRowsAndColumns();
        resetCheckValues();

        generateWords();
        printGrid();
        listener.updateGraphic();
    }

    private void generateNeri() {
        for (int i = 0; i < modelGame.getNeri(); i++) {
            int xNeri = (int) (Math.random() * (double) getX());
            int yNeri = (int) (Math.random() * (double) getY());
            if (getBox(xNeri, yNeri).isNero()) {
                i--;
            } else {
                getBox(xNeri, yNeri).setNero(true);
            }
        }
        listener.updateGraphic();
    }

    private void generateRowsAndColumns() {
        checkRows();
        checkColumns();
        listener.updateGraphic();
    }

    private void resetCheckValues() {
        for (int i = 0; i < getY(); i++) {
            for (int j = 0; j < getX(); j++) {
                getBox(j, i).setCheckRight(false);
                getBox(j, i).setCheckUnder(false);
            }
        }
    }

    private void printGrid() {
        for (int i = 0; i < getY(); i++) {
            for (int j = 0; j < getX(); j++) {
                if (getBox(j, i).isNero())
                    System.out.print("[X]");
                else
                    System.out.print("[ ]");
            }
            System.out.print("\n");
        }
    }

    private void checkRows() {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                if (!getBox(i, j).isCheckRight()) {
                    int wordLength = checkNextRow(getBox(i, j), 0);
                    if (wordLength > 1)
                        getBox(i, j).setHorizontalVal(String.valueOf(wordLength));
                }
            }
        }
    }

    private int checkNextRow(Box box, int length) {
        if (box.isNero())
            return length;
        length++;
        if (box.hasRight()) {
            box.setCheckRight(true);
            return checkNextRow(box.getRight(), length);
        }
        return length;
    }

    private void checkColumns() {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                if (!getBox(i, j).isCheckUnder()) {
                    int wordLength = checkNextCol(getBox(i, j), 0);
                    if (wordLength > 1)
                        getBox(i, j).setVerticalVal(String.valueOf(wordLength));
                }
            }
        }
    }

    private int checkNextCol(Box box, int length) {
        if (box.isNero())
            return length;
        length++;
        if (box.hasUnder()) {
            box.setCheckUnder(true);
            return checkNextCol(box.getUnder(), length);
        }
        return length;
    }

    private void generateWords() {
//        for (int j = 1; j <= getY(); j++)
//        {
//            for (int i = 1 ; i <= getX(); i++)
//            {
//                System.out.print(j + i + " ");
//            }
//            System.out.println();
//        }

//        generateHorizontalWords();
//        generateVerticalWords();
    }

    private void generateHorizontalWords(Box box) {

    }

    private void generateHorizontalWords() {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                if (getBox(i, j).getHorizontalVal() != null) {
                    int horizontalLength = Integer.parseInt(getBox(i, j).getHorizontalVal());

                    String existingWord = null;
                    String regexPattern = "^";

                    for (int k = 0; k < horizontalLength; k++) {
                        String currentLetter = getBox(i + k, j).getLetter();
                        if (currentLetter != null)
                            regexPattern = regexPattern + "[" + currentLetter.toLowerCase() + "]";
                        else
                            regexPattern = regexPattern + "[\\D]";
                    }
                    regexPattern = regexPattern + "$";

                    Optional<String> wordOptional = dizionario.getRandomWordByRegex(regexPattern);

                    if(wordOptional.isEmpty()) {
                        listener.reset();
                    } else {
                        String word = wordOptional.get();
                        System.out.println("HORIZONTAL: " + word);
                        for (int k = 0; k < horizontalLength; k++) {
                            char letter = word.charAt(k);
                            letter = removeAccentFromChar(letter);
                            getBox(i + k, j).setLetter(String.valueOf(letter).toUpperCase());
                        }
                    }
                }
            }
        }
    }

    private void generateVerticalWords() {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                if (getBox(i, j).getVerticalVal() != null) {
                    int verticalLength = Integer.parseInt(getBox(i, j).getVerticalVal());
                    String existingWord = null;
                    String regexPattern = "^";

                    for (int k = 0; k < verticalLength; k++) {
                        String currentLetter = getBox(i, j + k).getLetter();
                        if (currentLetter != null)
                            regexPattern = regexPattern + "[" + currentLetter.toLowerCase() + "]";
                        else
                            regexPattern = regexPattern + "[\\D]";
                    }
                    regexPattern = regexPattern + "$";

                    Optional<String> wordOptional = dizionario.getRandomWordByRegex(regexPattern);
                    if(wordOptional.isEmpty()) {
                        listener.reset();
                    } else {
                        String word = wordOptional.get();
                        System.out.println("VERTICAL: " + word);
                        for (int k = 0; k < verticalLength; k++) {
                            char letter = word.charAt(k);
                            letter = removeAccentFromChar(letter);
                            getBox(i, j + k).setLetter(String.valueOf(letter).toUpperCase());
                        }
                    }
                }
            }
        }
    }

    private char removeAccentFromChar(char letter) {
        if (letter == 'à')
            return 'a';
        if (letter == 'è' || letter == 'é')
            return 'e';
        if (letter == 'ì')
            return 'i';
        if (letter == 'ò')
            return 'o';
        if (letter == 'ù')
            return 'u';
        else
            return letter;
    }

    private String removeAccentFromString(String letter) {
        char firstChar = letter.charAt(0);
        return String.valueOf(removeAccentFromChar(firstChar));
    }

    public void onLeftClick(int x, int y) {

    }

    public void onRightClick(int x, int y) {

    }

    public Box getBox(int x, int y) {
        return modelGame.getBox(x, y);
    }

    public int getX() {
        return modelGame.getX();
    }

    public int getY() {
        return modelGame.getY();
    }

    public int getNeri() {
        return modelGame.getNeri();
    }

}
