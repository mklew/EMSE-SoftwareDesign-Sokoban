/**
 *
 */
package emse.softwaredesign.sokoban.view;

import emse.softwaredesign.sokoban.controller.Controller;

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

    /**
     * Default Constructor which initialize the game.
     */
    public SokobanGame () {
        initialize();
    }// end default constructor

    /**
     * Method to Initialize the contents of the frame.
     */
    private void initialize () {
        // Frame
        frame = new JFrame("Sokoban");
        frame.setBounds(100, 100, 488, 550);
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

    private void registerForArrowsEvents (JPanel gameBoardPanel1) {
        Action moveDown = new AbstractAction() {
            public void actionPerformed (ActionEvent e) {
                controller.moveDown();
                refreshAndCheckGameCondition();
            }
        };

        Action moveUp = new AbstractAction() {
            public void actionPerformed (ActionEvent e) {
                controller.moveUp();
                refreshView();
                refreshAndCheckGameCondition();
            }
        };

        Action moveRight = new AbstractAction() {
            public void actionPerformed (ActionEvent e) {
                controller.moveRight();
                refreshView();
                refreshAndCheckGameCondition();
            }
        };

        Action moveLeft = new AbstractAction() {
            public void actionPerformed (ActionEvent e) {
                controller.moveLeft();
                refreshView();
                refreshAndCheckGameCondition();
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

    private void refreshAndCheckGameCondition () {
        refreshView();
        if(controller.isGameFinished()) {
            System.out.print("GAME FINISHED"); // TODO display some label
            //gameBoardPanel.
            this.lblStatusBar.setText("GAME OVER!!!");
            gameBoardPanel.getActionMap().clear();
        }
    }

    /**
     * Method to create a new Game
     *
     * @return a new game board.
     */
    private JPanel createGameBoardPanel () {
        int nbRows = 8; //controller.getRows(); // Controller must be set at construction...
        int nbCols = 8; //controller.getColumns();
        gameBoardPanel = new JPanel();
        gameBoardPanel.setBounds(0, 0, nbRows*61, nbCols*61);
        gameBoardPanel.setMaximumSize(new Dimension(nbRows*61, nbCols*61));
        createNewGameButtons();
        gameBoardPanel.setLayout(new GridLayout(nbRows, nbCols));
        return gameBoardPanel;
    }

    /**
     * Method to create the new game buttons panel.
     */
    private void createNewGameButtons () {
        // gameBoardPanel.removeAll(); //Debugged
        int nbRows = 8; //controller.getRows();
        int nbCols = 8; //controller.getColumns();
        for (int y = 0; y < nbRows ; y++)
            for (int x = 0; x < nbCols ; x++) {
                JButton btn = new JButton(new ImageIcon(SokobanGame.class.getResource("Outside_Wall.png")));
                btn.setName("btn" + x + y);
                btn.setToolTipText("btn(" + x + "," + y + ")");
                gameBoardPanel.add(btn);
            }
    }// end of createNewGameButtons()

    /**
     * Method that create a new menu bar
     *
     * @return a new menu component
     */
    private JMenuBar createMenuBar () {
        JMenuBar menuBar = new JMenuBar();
        // game menu
        JMenu mnGame = new JMenu("Game");
        menuBar.add(mnGame);
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

    private void refreshView () {
        SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                redrawComponents();
            }
        });
    }

    private void redrawComponents () {
        for (Component c : gameBoardPanel.getComponents())
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                btn.setFocusable(false);
                final int column = Integer.parseInt(btn.getName().substring(3, 4)); // COLUMN
                final int row = Integer.parseInt(btn.getName().substring(4, 5)); // ROW

                // Change images for pieces
                final SquareTypes color = controller.getBoardView().getType(row, column);
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
                    case BOX_ON_THE_SLOT:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("BoxOnSlot.png")));
                        btn.setRolloverEnabled(false);
                        break;
                    case PLAYER:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("Player.png")));
                        btn.setRolloverEnabled(false);
                        break;
                    case PLAYER_ON_THE_SLOT:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("PlayerSlot.png")));
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

    public void setController (Controller controller) {
        this.controller = controller;
    }

}
