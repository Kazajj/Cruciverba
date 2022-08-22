package cruciverba.graphic;

import cruciverba.controller.Controller;
import cruciverba.element.GameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScreenView implements MouseListener, GameListener {

    private JPanel panel;
    private JTextField status;
    private JFrame frame;
    private GameButton[][] buttons;
    private Controller controller;
    private int DEBUG_PANEL = 100;
    private int CASELLA_SIZE = 60;

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
        panel.setPreferredSize(new Dimension(CASELLA_SIZE * controller.getX() + DEBUG_PANEL, CASELLA_SIZE * controller.getY()));
        panel.setVisible(true);

        generateBoxes();
        updateGraphic();

        frame.add(panel);
        frame.pack();

        frame.repaint();
    }

    public void generateDebug() {
        status = new JTextField();
        status.setSize(DEBUG_PANEL, controller.getY() * CASELLA_SIZE);
        status.setLocation(CASELLA_SIZE * controller.getX(), 0);
        status.setVisible(true);
        status.setEnabled(false);
        panel.add(status);

        JButton debug = new JButton();
        debug.setSize(DEBUG_PANEL, CASELLA_SIZE);
        debug.setLocation(0, 0);
        debug.setVisible(true);
        debug.setText("DEBUG");
        debug.setEnabled(false);
        status.add(debug);

        JButton debug1 = new JButton();
        debug1.setSize(DEBUG_PANEL, CASELLA_SIZE);
        debug1.setLocation(0, CASELLA_SIZE);
        debug1.setText("DEFAULT");
        debug1.setVisible(true);
        debug1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateGraphic();
            }
        });
        status.add(debug1);

        JButton debug2 = new JButton();
        debug2.setSize(DEBUG_PANEL, CASELLA_SIZE);
        debug2.setLocation(0, CASELLA_SIZE * 2);
        debug2.setText("hasRight()");
        debug2.setVisible(true);
        debug2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                debugHasRight();
            }
        });
        status.add(debug2);

        JButton debug3 = new JButton();
        debug3.setSize(DEBUG_PANEL, CASELLA_SIZE);
        debug3.setLocation(0, CASELLA_SIZE * 3);
        debug3.setText("hasUnder()");
        debug3.setVisible(true);
        debug3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                debugHasUnder();
            }
        });
        status.add(debug3);

        JButton debug4 = new JButton();
        debug4.setSize(DEBUG_PANEL, CASELLA_SIZE);
        debug4.setLocation(0, CASELLA_SIZE * 4);
        debug4.setText("Horizontal()");
        debug4.setVisible(true);
        debug4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                debugHorizontalVal();
            }
        });
        status.add(debug4);

        JButton debug5 = new JButton();
        debug5.setSize(DEBUG_PANEL, CASELLA_SIZE);
        debug5.setLocation(0, CASELLA_SIZE * 5);
        debug5.setText("Vertical()");
        debug5.setVisible(true);
        debug5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                debugVerticalVal();
            }
        });
        status.add(debug5);

        JButton reset = new JButton();
        reset.setSize(DEBUG_PANEL, CASELLA_SIZE);
        reset.setLocation(0, CASELLA_SIZE * 10);
        reset.setText("RESET");
        reset.setVisible(true);
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        status.add(reset);
    }

    public void generateBoxes() {
        buttons = new GameButton[controller.getX()][controller.getY()];
        for (int i = 0; i < controller.getX(); i++) {
            for (int j = 0; j < controller.getY(); j++) {
                buttons[i][j] = new GameButton();
                buttons[i][j].setCoordinateXandY(i, j);
                buttons[i][j].setLocation(CASELLA_SIZE * i, CASELLA_SIZE * j);
                buttons[i][j].setSize(CASELLA_SIZE, CASELLA_SIZE);
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
                buttons[i][j].setText("");
                if (controller.getBox(i, j).isNero()) {
                    buttons[i][j].setBackground(Color.BLACK);
                } else {
                    buttons[i][j].setBackground(Color.WHITE);
                }
                if(controller.getBox(i, j).getLetter() != null)
                    buttons[i][j].setText(controller.getBox(i, j).getLetter());
            }
        }
        frame.repaint();
    }

    public void reset() {
        frame.setVisible(false);
        frame.dispose();
        controller.chooseGridSize(controller.getX(), controller.getY(), controller.getNeri());
    }

    private void debugHasRight() {
        for (int i = 0; i < controller.getX(); i++) {
            for (int j = 0; j < controller.getY(); j++) {
                if (controller.getBox(i, j).hasRight()) {
                    buttons[i][j].setBackground(Color.GREEN);
                    buttons[i][j].setText(controller.getBox(i, j).getRight().getX() + "," + controller.getBox(i, j).getRight().getY());
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
                    buttons[i][j].setText(controller.getBox(i, j).getUnder().getX() + "," + controller.getBox(i, j).getUnder().getY());
                } else {
                    buttons[i][j].setBackground(Color.RED);
                }
            }
        }
    }

    private void debugHorizontalVal() {
        updateGraphic();
        for (int i = 0; i < controller.getX(); i++) {
            for (int j = 0; j < controller.getY(); j++) {
                buttons[i][j].setText(controller.getBox(i, j).getHorizontalVal());
            }
        }
    }

    private void debugVerticalVal() {
        updateGraphic();
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
