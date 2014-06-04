/**
 *
 */
package emse.softwaredesign.sokoban.view;

import emse.softwaredesign.sokoban.controller.Controller;
import emse.softwaredesign.sokoban.model.Game;
import emse.softwaredesign.sokoban.model.SquareType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Sokoban is a type of transport puzzle, in which the player pushes boxes or
 * crates around in a warehouse, trying to get them to storage locations.
 * @see <a>http://en.wikipedia.org/wiki/Sokoban</a>
 */
public class SokobanGame implements View {

    // Instance Components
    private JFrame frame;

    private JLabel lblStatusBar;

    private JPanel gameBoardPanel;

    private Controller controller;

    private final int BLOC_SIZE = 61;


    /**
     * Initializes the contents of the frame
     */
    private void initialize () {
        // Frame
        frame = new JFrame("Sokoban");
        int nbRows = controller.getRows();
        int nbCols = controller.getColumns();
        frame.setBounds(100, 100, nbCols*BLOC_SIZE, 1+(nbRows+1)*BLOC_SIZE);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Menu Bar
        frame.setJMenuBar(createMenuBar());
        // Status Bar Label
        lblStatusBar = new JLabel(
                "Push boxes around, trying to get them to storage locations.");
        // Vertical, horizontal and diagonal jumps are allowed.
        frame.getContentPane().add(lblStatusBar, BorderLayout.SOUTH);
        final JPanel gameBoardPanel1 = createGameBoardPanel();
        frame.getContentPane().add(gameBoardPanel1, BorderLayout.CENTER);

        registerForArrowsEvents(gameBoardPanel1);

    }// end of initialize()

    /**
     * Attaches the move behaviors to the direction keys
     * @param gameBoardPanel1 the associated visual component
     */
    private void registerForArrowsEvents (JPanel gameBoardPanel1) {
        Action moveDown = new AbstractAction() {
            public void actionPerformed (ActionEvent e) {
                controller.moveDown();
                refreshView();
                checkGameCondition();
            }
        };

        Action moveUp = new AbstractAction() {
            public void actionPerformed (ActionEvent e) {
                controller.moveUp();
                refreshView();
                checkGameCondition();
            }
        };

        Action moveRight = new AbstractAction() {
            public void actionPerformed (ActionEvent e) {
                controller.moveRight();
                refreshView();
                checkGameCondition();
            }
        };

        Action moveLeft = new AbstractAction() {
            public void actionPerformed (ActionEvent e) {
                controller.moveLeft();
                refreshView();
                checkGameCondition();
            }
        };

        gameBoardPanel1.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        gameBoardPanel1.getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveUp");
        gameBoardPanel1.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        gameBoardPanel1.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");

        gameBoardPanel1.getActionMap().put("moveDown", moveDown);
        gameBoardPanel1.getActionMap().put("moveUp", moveUp);
        gameBoardPanel1.getActionMap().put("moveLeft", moveLeft);
        gameBoardPanel1.getActionMap().put("moveRight", moveRight);
    }

    /**
     * Checks if game conditions have been met and if so displays a message
     */
    private void checkGameCondition() {
        if(controller.isGameFinished()) {
            if (controller.hasNextLevel()) {
                JOptionPane.showMessageDialog(gameBoardPanel,"Go to the next level","Congratulations",JOptionPane.INFORMATION_MESSAGE);
                frame.setVisible(false);
                controller.goToNextLevel();
                controller.start();
            } else {
                JOptionPane.showMessageDialog(gameBoardPanel,"Congratulations, you won!","Congratulations",JOptionPane.INFORMATION_MESSAGE);
                this.lblStatusBar.setText("Congratulations, you won!");
                gameBoardPanel.getActionMap().clear();
            }
        }
    }

    /**
     * Creates a new Game
     * @return a new game board
     */
    private JPanel createGameBoardPanel () {
        int nbRows = controller.getRows();
        int nbCols = controller.getColumns();
        gameBoardPanel = new JPanel();
        gameBoardPanel.setBounds(0, 0, nbRows*BLOC_SIZE, nbCols*BLOC_SIZE);
        gameBoardPanel.setMaximumSize(new Dimension(nbRows*BLOC_SIZE, nbCols*BLOC_SIZE));
        createNewGameButtons();
        gameBoardPanel.setLayout(new GridLayout(nbRows, nbCols));
        return gameBoardPanel;
    }

    /**
     * Creates the new game buttons panel
     */
    private void createNewGameButtons () {
        // gameBoardPanel.removeAll(); //Debugged
        int nbRows = controller.getRows();
        int nbCols = controller.getColumns();
        for (int y = 0; y < nbRows ; y++)
            for (int x = 0; x < nbCols ; x++) {
                JButton btn = new JButton(new ImageIcon(SokobanGame.class.getResource("Outside_Wall.png")));
                btn.setName("btn" + x  + "," + y);
                //btn.setToolTipText("btn(" + x + "," + y + ")");
                gameBoardPanel.add(btn);
            }
    }// end of createNewGameButtons()

    /**
     * Creates a new menu bar
     * @return a new menu component
     */
    private JMenuBar createMenuBar () {
        JMenuBar menuBar = new JMenuBar();
        // game menu
        JMenu mnGame = new JMenu("Game");
        menuBar.add(mnGame);
        // add restart option to game menu
        JMenuItem mntmRestart = new JMenuItem("Restart");
        mntmRestart.addActionListener(new ActionListener() {
            // overrides actionPerformed
            public void actionPerformed (ActionEvent e) {
                frame.setVisible(false);
                controller.setGame(new Game());
                controller.start();
            }
        });
        // register listener
        mnGame.add(mntmRestart);
        // add exit option to game menu
        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener() {
            // overrides actionPerformed
            public void actionPerformed (ActionEvent e) {
                System.exit(0);
            }
        });
        // register listener
        mnGame.add(mntmExit);

        // Help menu
        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);
        // add rules option to help menu
        JMenuItem mntmRules = new JMenuItem("Rules");
        mntmRules.addActionListener(new ActionListener() {
            // overrides actionPerformed
            public void actionPerformed (ActionEvent e) {
                Rules ru = new Rules();
                ru.setVisible(true);
            }
        });
        // register listener
        mnHelp.add(mntmRules);
        return menuBar;
    }

    /**
     * Refreshes the view
     */
    private void refreshView () {
        SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                redrawComponents();
            }
        });
    }

    /**
     * Redraws the components
     */
    private void redrawComponents () {
        for (Component c : gameBoardPanel.getComponents())
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                btn.setFocusable(false);
                final String[] split = btn.getName().replace("btn", "").split(",");
                final int column = Integer.parseInt(split[0]); // COLUMN
                final int row = Integer.parseInt(split[1]); // ROW

                // Change images for pieces
                final SquareType color = controller.getBoardView().getType(row, column);
                switch (color) {
                    case FLOOR:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("Floor.png")));
                        btn.setRolloverEnabled(false);
                        break;
                    case WALL:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("Wall.png")));
                        btn.setRolloverEnabled(false);
                        break;
                    case BOX:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("Box.png")));
                        btn.setRolloverEnabled(false);
                        break;
                    case DOG:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("Dog.png")));
                        btn.setRolloverEnabled(false);
                        break;
                    case BOX_ON_THE_SLOT:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("BoxOnSlot.png")));
                        btn.setRolloverEnabled(false);
                        break;
                    case PLAYER:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("PlayerAlt.png")));
                        btn.setRolloverEnabled(false);
                        break;
                    case PLAYER_ON_THE_SLOT:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("PlayerSlotAlt.png")));
                        btn.setRolloverEnabled(false);
                        break;
                    //case OUTSIDE_WALL:
                    //	btn.setIcon(new ImageIcon(SokobanGame.class
                    //			.getResource("Outside_Wall.png")));
                    // btn.setRolloverEnabled(true);
                    case BOX_SLOT:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("BoxSlot.png")));
                        btn.setRolloverEnabled(false);
                        break;
                    case GREEN_POINT:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("GreenPoint.png")));
                        btn.setRolloverEnabled(false);
                        break;
                    default:
                        // btn.removed;//Debugged
                        break;
                }// end of Switch
            }// end of if
    }


    @Override
    public void show () {
        initialize();
        refreshView();
        frame.setVisible(true);
    }

    /**
     * Sets the controller attached to the view
     * @param controller the controller attached to the view
     */
    public void setController (Controller controller) {
        this.controller = controller;
    }

}
