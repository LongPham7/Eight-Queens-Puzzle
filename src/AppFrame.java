import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppFrame {

	private JFrame frame;

	private JPanel panel1;
	private JPanel panel2;
	private ChessBoardPanel board = new ChessBoardPanel();;

	private PuzzleSolver solver = new PuzzleSolver(board);

	private JLabel label1;

	private JButton button1;
	private JButton button2;

	private JComboBox<Integer> boardSizeSelector;

	public void activate() {
		frame = new JFrame("Eight Queens Problem");

		panel1 = new JPanel();
		panel2 = new JPanel();

		frame.getContentPane().add(BorderLayout.NORTH, panel1);
		frame.getContentPane().add(BorderLayout.CENTER, panel2);

		label1 = new JLabel("Select the board size: ");

		button1 = new JButton("Solve");
		button2 = new JButton("Next solution");

		Integer[] boardSize = { 1, 2, 3, 4, 5, 6, 7, 8 };
		boardSizeSelector = new JComboBox<Integer>(boardSize);
		boardSizeSelector.setSelectedIndex(7);

		panel1.setLayout(new GridBagLayout());
		addComponent(label1, panel1, 0, 0, 1);
		addComponent(boardSizeSelector, panel1, 1, 0, 1);
		addComponent(button1, panel1, 0, 1, 2);
		addComponent(button2, panel1, 0, 2, 2);
		panel1.setBorder(BorderFactory.createTitledBorder("Control Panel"));

		panel2.setLayout(new GridBagLayout());
		addComponent(board, panel2, 0, 0, 1);
		panel2.setBorder(BorderFactory.createTitledBorder("Chess Board"));

		board.setPreferredSize(new Dimension(420, 420));

		button1.addActionListener(new Button1Listener());
		button2.addActionListener(new Button2Listener());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 600);
		frame.setVisible(true);
	}

	// Adds an input component to a specified JPanel at a specified coordinate.
	private void addComponent(Component component, JPanel panel, int x, int y, int width) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		panel.add(component, c);
	}

	// Action listener for a button
	class Button1Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			solver.solve((int) boardSizeSelector.getSelectedItem());
		}
	}

	// Action listener for a button
	class Button2Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			solver.nextSolution();
		}
	}
}
