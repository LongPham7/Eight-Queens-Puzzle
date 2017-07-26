import java.util.Map;

import javax.swing.JOptionPane;

import org.jpl7.Query;
import org.jpl7.Term;

/**
 * This class is a solver of the N queens problem, where N is at most 8.
 */
public class PuzzleSolver {

	// JPanel where a chess board is displayed
	private ChessBoardPanel board;

	// Current Prolog query
	private Query query;

	public PuzzleSolver(ChessBoardPanel board) {
		this.board = board;
	}

	// Solves the N queens problem with a given board size and obtains the first
	// solution. The Prolog query issused in this method is stored in the instance
	// variable "query" for later use.
	public void solve(int boardSize) {
		String t1 = "consult('queens.pl')";
		System.out.println(t1 + " " + (Query.hasSolution(t1) ? "succeeded" : "failed"));

		String t2 = String.format("queens(%s, X)", boardSize);
		Query q2 = new Query(t2);
		query = q2;
		nextSolution();
	}

	// Obtains the next solution if it exists. 
	public void nextSolution() {
		if (query == null) {
			JOptionPane.showMessageDialog(null, "Click 'Solve' first.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (query.hasMoreSolutions()) {
			Map<String, Term> ans = query.nextSolution();
			displayResult(ans.get("X"));
		} else {
			JOptionPane.showMessageDialog(null, "No more solution", "Warning", JOptionPane.PLAIN_MESSAGE);
		}
	}

	// Displays a solution on the chess board.
	private void displayResult(Term answer) {
		Term[] resultArray = answer.toTermArray();
		int len = resultArray.length;
		Coordinate[] result = new Coordinate[len];
		for (int i = 0; i != len; i++) {
			result[i] = new Coordinate(i, resultArray[i].intValue() - 1);
		}
		refresh(result);
	}

	// Refreshes the chess board. 
	private void refresh(Coordinate[] result) {
		board.setBoardSize(result.length);
		board.setCoordinate(result);
		board.repaint();
	}
}
