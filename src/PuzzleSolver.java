import java.util.Map;

import javax.swing.JOptionPane;

import org.jpl7.Query;
import org.jpl7.Term;

public class PuzzleSolver {

	private ChessBoardPanel board;

	private Query query;

	public PuzzleSolver(ChessBoardPanel board) {
		this.board = board;
	}

	public void solve(int boardSize) {
		String t1 = "consult('queens.pl')";
		System.out.println(t1 + " " + (Query.hasSolution(t1) ? "succeeded" : "failed"));

		String t2 = String.format("queens(%s, X)", boardSize);
		Query q2 = new Query(t2);
		query = q2;
		nextSolution();
	}

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

	private void displayResult(Term answer) {
		Term[] resultArray = answer.toTermArray();
		int len = resultArray.length;
		Coordinate[] result = new Coordinate[len];
		for (int i = 0; i != len; i++) {
			result[i] = new Coordinate(i, resultArray[i].intValue() - 1);
		}
		refresh(result);
	}

	private void refresh(Coordinate[] result) {
		board.setBoardSize(result.length);
		board.setCoordinate(result);
		board.repaint();
	}
}
