/**
 *
 */
package emse.softwaredesign.sokoban.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Sokoban is a type of transport puzzle, in which the player pushes boxes or
 * crates around in a warehouse, trying to get them to storage locations.
 *
 * @author Alberth Montero <alberthm@gmail.com>
 * @see http://en.wikipedia.org/wiki/Sokoban
 */
public class SokobanGame implements Observer {
    // Instance Variables
    private static Board board;
    // Instance Components
    private JFrame frame;
    private JLabel lblStatusBar;
    private JPanel gameBoardPanel;

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
        // Game Board Panel
        final JPanel gameBoardPanel1 = createGameBoardPanel();
        frame.getContentPane().add(gameBoardPanel1, BorderLayout.CENTER);

        Action moveDown = new AbstractAction() {
            public void actionPerformed (ActionEvent e) {
                System.out.println("down");
            }
        };


        Action moveUp = new AbstractAction() {
            public void actionPerformed (ActionEvent e) {
                System.out.println("up");
            }
        };

        Action moveRight = new AbstractAction() {
            public void actionPerformed (ActionEvent e) {
                System.out.println("right");
            }
        };

        Action moveLeft = new AbstractAction() {
            public void actionPerformed (ActionEvent e) {
                System.out.println("left");
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

    }// end of initialize()

    /**
     * Method to create a new Game Board
     *
     * @return a new game board.
     */
    private JPanel createGameBoardPanel () {
        gameBoardPanel = new JPanel();
        gameBoardPanel.setBounds(0, 0, 488, 488);
        gameBoardPanel.setMaximumSize(new Dimension(488, 488));
        createNewGameButtons();
        gameBoardPanel.setLayout(new GridLayout(8, 8));
        return gameBoardPanel;
    }// end of createGameBoardPanel()

    /**
     * Method to create the new game buttons panel.
     */
    private void createNewGameButtons () {
        // gameBoardPanel.removeAll(); //Debugged
        int[] eight = {0, 1, 2, 3, 4, 5, 6, 7};

        for (int y : eight)
            for (int x : eight) {
                JButton btn = new JButton(new ImageIcon(SokobanGame.class.getResource("Outside_Wall.png")));
                // Board scheme name
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
        // add start option to game menu
        JMenuItem mntmStart = new JMenuItem("Start");
        mntmStart.addActionListener(new ActionListener() {
            // overrides actionPerformed
            public void actionPerformed (ActionEvent e) {
                startGame();
            }
        });
        // register listener
        mnGame.add(mntmStart);
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
        /*
		 * JMenuItem mntmAbout = new JMenuItem("About");
		 * mntmAbout.setEnabled(false); mnHelp.add(mntmAbout);
		 */// for future implementation

        return menuBar;
    }// end of createMenuBar()

    /**
     * Method that start the Game.
     */
    protected void startGame () {
        board.startGame();
    }// end startGame()

    /**
     * Method that implements Observer update()
     */
    public void update (Object o) {
        // createNewGameButtons();//Debugged
        Board b = (Board) o;
        // Display info
        System.out.println("SokobanGame.update()");
        System.out.println(b);
        // Get the components
        for (Component c : gameBoardPanel.getComponents())
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                btn.setFocusable(false);
                //String btnName = btn.getName();//Debugged
                final int x = Integer.parseInt(btn.getName().substring(3, 4));
                final int y = Integer.parseInt(btn.getName().substring(4, 5));
                //removeButtonListner(btn);

                // Change images for pieces
                switch (b.get(x, y).color) {
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
                    case PLAYER:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("Player.png")));
                        btn.setRolloverEnabled(false);
                        break;
                    //case OUTSIDE_WALL:
                    //	btn.setIcon(new ImageIcon(SokobanGame.class
                    //			.getResource("Outside_Wall.png")));
                    // btn.setRolloverEnabled(true);
                    case BOX_ON_LOCATION:
                        btn.setIcon(new ImageIcon(SokobanGame.class
                                .getResource("BoxSlot.png")));
                        btn.setRolloverEnabled(false);
                    default:
                        // btn.removed;//Debugged
                        break;
                }// end of Switch
            }// end of if
    }// end of update(o)

    /**
     * The main program.
     *
     * @param None
     */
    public static void main (String[] args) {
        try {
            board = new Board();
            SokobanGame window = new SokobanGame();
            board.registerObserver(window);
            window.frame.setVisible(true);
            window.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// end of the main()
}// end of ReversiGame class
