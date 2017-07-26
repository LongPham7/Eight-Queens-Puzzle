import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ChessBoardPanel extends JPanel {

	// Default serial version UID
	private static final long serialVersionUID = 1L;

	private Coordinate[] coordinates;
	private int boardSize = 8;

	public void setCoordinate(Coordinate[] coordinate) {
		this.coordinates = coordinate;
	}

	public void setBoardSize(int boardSize) {
		assert 0 < boardSize && boardSize <= 8;
		this.boardSize = boardSize;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		drawBoard(g2);
		placeQueens(g2);
	}

	private void drawBoard(Graphics2D g) {
		setBackground(Color.WHITE);
		g.draw(new Line2D.Double(10, 10, 410, 10));
		g.draw(new Line2D.Double(10, 10, 10, 410));
		g.draw(new Line2D.Double(410, 10, 410, 410));
		g.draw(new Line2D.Double(10, 410, 410, 410));
		for (int i = 0; i != boardSize; i++) {
			for (int j = (i + 1) % 2; j < boardSize; j = j + 2) {
				shadeCell(g, j, i);
			}
		}
	}

	private void shadeCell(Graphics2D g, int x, int y) {
		double width = 400.0 / boardSize;
		double topX = 10 + width * x;
		double topY = 10 + width * y;
		g.setColor(Color.gray);
		g.fill(new Rectangle2D.Double(topX, topY, width, width));
	}

	private void placeQueens(Graphics2D g) {
		if (coordinates != null) {
			for (int i = 0; i != coordinates.length; i++) {
				Coordinate xy = coordinates[i];
				placeQueen(g, xy);
			}
		}
	}

	private void placeQueen(Graphics2D g, Coordinate co) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("Queen.png"));
		} catch (IOException e) {
			throw new Error("Error in loading an image.");
		}
		float width = ((float) 400.0) / boardSize;
		int integralWidth = 400 / boardSize;
		float topX = 10 + width * co.getX();
		float topY = 10 + width * co.getY();
		g.drawImage(img, Math.round(topX), Math.round(topY), integralWidth, integralWidth, null);
	}
}
