public abstract class Board {
	protected String[][] board;
	protected int numRows, numCols;
	/**
	 * Sets the boards rows and columns and makes new string board with those dimensions 
	 * @param r Rows
	 * @param c Columns
	 */
	public Board (int r, int c) {
		numRows = r;
		numCols = c;
		board = new String[r][c];
	}
}
