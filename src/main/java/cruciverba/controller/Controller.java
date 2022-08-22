package cruciverba.controller;

import cruciverba.element.GameListener;
import cruciverba.model.ModelGame;
import cruciverba.objects.Box;

import java.awt.*;

public class Controller {

    ModelGame modelGame;
    GameListener listener;

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
        generateNeri();
        printGrid();
        generateRowsAndColumns();
    }

    private void generateNeri() {
        for (int i = 0; i < modelGame.getNeri(); i++) {
            int xNeri = (int) (Math.random() * (double) getX());
            int yNeri = (int) (Math.random() * (double) getY());
//            Point randomCoordinateNero = new Point(xNeri, yNeri);
            if (getBox(xNeri, yNeri).isNero()) {
                i--;
            } else {
                getBox(xNeri, yNeri).setNero(true);
            }
        }
        listener.updateGraphic();
    }

    public void generateRowsAndColumns() {
        checkRows();
        checkColumns();
        listener.updateGraphic();
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

    public void checkRows() {
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

    public int checkNextRow(Box box, int length) {
        if (box.isNero())
            return length;
        length++;
        if (box.hasRight()) {
            box.setCheckRight(true);
            return checkNextRow(box.getRight(), length);
        }
        return length;
    }

    public void checkColumns() {
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

    public int checkNextCol(Box box, int length) {
        if (box.isNero())
            return length;
        length++;
        if (box.hasUnder()) {
            box.setCheckUnder(true);
            return checkNextCol(box.getUnder(), length);
        }
        return length;
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

}
