package cruciverba.model;

import cruciverba.objects.Box;

public class ModelGame {

    public ModelGame() {
    }

    private int x;
    private int y;
    private int neri;
    private Box[][] boxes;

    public void generateBoxes() {
        boxes = new Box[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                boxes[i][j] = new Box(i, j);
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(i + 1 != x)
                    boxes[i][j].setRight(boxes[i + 1][j]);
                if(j + 1 != y)
                    boxes[i][j].setUnder(boxes[i][j + 1]);
            }
        }
    }

    public Box getBox(int x, int y) {
        return boxes[x][y];
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Box[][] getBoxes() {
        return boxes;
    }

    public void setBoxes(Box[][] boxes) {
        this.boxes = boxes;
    }

    public int getNeri() {
        return neri;
    }

    public void setNeri(int neri) {
        this.neri = neri;
    }

}
