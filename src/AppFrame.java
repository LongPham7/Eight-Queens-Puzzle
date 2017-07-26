import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppFrame {

	private JFrame frame;

	private JPanel panel1;
	private JPanel panel2;
	private JPanel grid;

	private JLabel label1;

	private JButton button1;
	private JButton button2;

	private JComboBox<String> gridSizeSelector;

	public void activate() {
		frame = new JFrame("Eight Queens Problem");

		panel1 = new JPanel();
		panel2 = new JPanel();
		grid = new ChessBoardPanel();

		frame.getContentPane().add(BorderLayout.NORTH, panel1);
		frame.getContentPane().add(BorderLayout.CENTER, panel2);

		label1 = new JLabel("Select the grid size: ");

		button1 = new JButton("Solve");
		button2 = new JButton("Next solution");

		String[] gridSize = { "1", "2", "3", "4", "5", "6", "7", "8" };
		gridSizeSelector = new JComboBox<String>(gridSize);
		gridSizeSelector.setSelectedIndex(7);

		panel1.setLayout(new GridBagLayout());
		addComponent(label1, panel1, 0, 0, 1);
		addComponent(gridSizeSelector, panel1, 1, 0, 1);
		addComponent(button1, panel1, 0, 1, 2);
		addComponent(button2, panel1, 0, 2, 2);
		panel1.setBorder(BorderFactory.createTitledBorder("Control Panel"));

		panel2.setLayout(new GridBagLayout());
		addComponent(grid, panel2, 0, 0, 1);
		panel2.setBorder(BorderFactory.createTitledBorder("Chess Board"));

		button1.addActionListener(new ButtonListener());
		button2.addActionListener(new ButtonListener());

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
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

		}
	}

}
