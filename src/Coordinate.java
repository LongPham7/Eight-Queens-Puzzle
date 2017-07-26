/**
 * This class represents a coordinate of the form (x, y), where both x and y are
 * integers. An object of this class is intended to denote a coordinate on a
 * chess board.
 */
public class Coordinate {

	private int x;
	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
