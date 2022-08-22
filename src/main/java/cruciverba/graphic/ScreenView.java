package cruciverba.graphic;

import cruciverba.controller.Controller;
import cruciverba.element.GameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScreenView implements MouseListener, GameListener {

    private JPanel panel;
    private JFrame frame;
    private GameButton[][] buttons;
    private Controller controller;

    public ScreenView(Controller controller) {
        this.controller = controller;
    }

    public void generateGamePanel() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setLocation(0, 0);
        panel.setPreferredSize(new Dimension(50 * controller.getX(), 50 * controller.getY()));
        panel.setVisible(true);

        generateBoxes();
        updateGraphic();

        frame.add(panel);
        frame.pack();

        frame.repaint();
    }

    public void generateBoxes() {
        buttons = new GameButton[controller.getX()][controller.getY()];
        for (int i = 0; i < controller.getX(); i++) {
            for (int j = 0; j < controller.getY(); j++) {
                buttons[i][j] = new GameButton();
                buttons[i][j].setCoordinateXandY(i, j);
                buttons[i][j].setLocation(50 * i, 50 * j);
                buttons[i][j].setVisible(true);
                buttons[i][j].addMouseListener(this);
                buttons[i][j].setEnabled(false);
                panel.add(buttons[i][j]);
            }
        }
    }

    public void updateGraphic() {
        for (int i = 0; i < controller.getX(); i++) {
            for (int j = 0; j < controller.getY(); j++) {
                if (controller.getBox(i, j).isNero()) {
                    buttons[i][j].setBackground(Color.BLACK);
                } else {
                    buttons[i][j].setBackground(Color.WHITE);
                }
            }
        }
//        debugHasRight();
//        debugHasUnder();
//        debugHorizontalVal();
//        debugVerticalVal();
        frame.repaint();
    }

    private void debugHasRight() {
        for (int i = 0; i < controller.getX(); i++) {
            for (int j = 0; j < controller.getY(); j++) {
                if (controller.getBox(i, j).hasRight()) {
                    buttons[i][j].setBackground(Color.GREEN);
                    buttons[i][j].setText(controller.getBox(i, j).getRight().getX() + "" + controller.getBox(i, j).getRight().getY());
                } else {
                    buttons[i][j].setBackground(Color.RED);
                }
            }
        }
    }

    private void debugHasUnder() {
        for (int i = 0; i < controller.getX(); i++) {
            for (int j = 0; j < controller.getY(); j++) {
                if (controller.getBox(i, j).hasUnder()) {
                    buttons[i][j].setBackground(Color.GREEN);
                    buttons[i][j].setText(controller.getBox(i, j).getUnder().getX() + "" + controller.getBox(i, j).getUnder().getY());
                } else {
                    buttons[i][j].setBackground(Color.RED);
                }
            }
        }
    }

    private void debugHorizontalVal() {
        for (int i = 0; i < controller.getX(); i++) {
            for (int j = 0; j < controller.getY(); j++) {
                buttons[i][j].setText(controller.getBox(i, j).getHorizontalVal());
            }
        }
    }

    private void debugVerticalVal() {
        for (int i = 0; i < controller.getX(); i++) {
            for (int j = 0; j < controller.getY(); j++) {
                buttons[i][j].setText(controller.getBox(i, j).getVerticalVal());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameButton gameButton = (GameButton) e.getSource();
        int x = gameButton.getCoordinateX();
        int y = gameButton.getCoordinateY();

        if (SwingUtilities.isLeftMouseButton(e)) {
            controller.onLeftClick(x, y);
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            controller.onRightClick(x, y);
        }
        updateGraphic();
    }

    @Override
    public void onTimeExpired() {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
