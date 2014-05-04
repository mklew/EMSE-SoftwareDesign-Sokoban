/**
 * 
 */
package emse.softwaredesign.sokoban.view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/** 
 * @author Alberth Montero <alberthm@gmail.com>
 */
@SuppressWarnings("serial")
public class Rules extends JDialog {
	// Instance Variables
	private final JPanel contentPanel = new JPanel();

	/**
	 * Default constructor of Rules class
	 */
	public Rules() {
		setBounds(100, 100, 535, 650);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

		// Fill the text are with the set of rules
		JTextArea textArea = new JTextArea();
		textArea.setText(" Sokoban (warehouse keeper)\n \n Aim of the game\n \n is a type of transport"  
				+ "puzzle, in which the player pushes boxes or crates around\n in a warehouse, "
				+ "trying to get them to storage locations.\n \nPlaying the game\n \n The game is "
				+ "played on a board of squares, where each square is a floor or a wall.\n Some floor"
				+ "squares contain boxes, and some floor squares are marked as storage \n locations.\n\n"
				+ "The player is confined to the board, and may move horizontally or vertically onto\n"
				+ "empty squares (never through walls or boxes). The player can also move into a\n box,"
				+ "which pushes it into the square beyond. Boxes may not be pushed into other\n boxes or"
				+ "walls, and they cannot be pulled. The puzzle is solved when all boxes \n are at storage" 
				+ " locations.\n \n Go to: http://en.wikipedia.org/wiki/Sokoban");
		textArea.setVisible(true);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		contentPanel.add(scrollPane);

		// Create button with action listener to close rules window
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
	}// end of default constructor
}// end of Rules Class
