package cruciverba.graphic;

import java.awt.Color;
import javax.swing.JButton;

public class GameButton extends JButton {

    private static final long	serialVersionUID	= 1L;

    private int					coordinataX, coordinataY;

    public GameButton() {
        this.setBackground(Color.LIGHT_GRAY);
    }

    public int getCoordinateX() {
        return coordinataX;
    }

    public int getCoordinateY() {
        return coordinataY;
    }

    public void setCoordinateXandY(int coordinataX, int coordinataY) {
        this.coordinataX = coordinataX;
        this.coordinataY = coordinataY;
    }

}
