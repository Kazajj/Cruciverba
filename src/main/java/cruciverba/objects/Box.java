package cruciverba.objects;


public class Box {

    private boolean nero;
    private String horizontalVal, verticalVal;
    private int x, y;
    private Box right, under;
    private boolean checkRight, checkUnder;

    public Box(int x, int y) {
        this.horizontalVal = null;
        this.verticalVal = null;
        this.x = x;
        this.y = y;
        this.right = null;
        this.under = null;
        this.checkRight = false;
        this.checkUnder = false;
    }

    public boolean isNero() {
        return nero;
    }

    public void setNero(boolean nero) {
        this.nero = nero;
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

    public Box getRight() {
        return right;
    }

    public boolean hasRight() {
        if (getRight() == null)
            return false;
        else
            return true;
    }

    public void setRight(Box right) {
        this.right = right;
    }

    public Box getUnder() {
        return under;
    }

    public void setUnder(Box under) {
        this.under = under;
    }

    public boolean hasUnder() {
        if (getUnder() == null)
            return false;
        else
            return true;
    }

    public boolean isCheckRight() {
        return checkRight;
    }

    public void setCheckRight(boolean checkRight) {
        this.checkRight = checkRight;
    }

    public boolean isCheckUnder() {
        return checkUnder;
    }

    public void setCheckUnder(boolean checkUnder) {
        this.checkUnder = checkUnder;
    }

    public String getHorizontalVal() {
        return horizontalVal;
    }

    public void setHorizontalVal(String horizontalVal) {
        this.horizontalVal = horizontalVal;
    }

    public String getVerticalVal() {
        return verticalVal;
    }

    public void setVerticalVal(String verticalVal) {
        this.verticalVal = verticalVal;
    }

}
