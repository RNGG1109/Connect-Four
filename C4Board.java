public class C4Board extends Board implements BoardThings{
	/**
	 * Sets the board
	 * @param r Rows
	 * @param c Columns
	 */
	public C4Board(int r, int c) {
		super(r,c);
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				board[i][j] = "O";
			}		
		}
	}
	
	/**
	 * Sets the spot in the grid to be whatever color comes in
	 * @param row Rows
	 * @param col Columns
	 * @param chip (Red / Yellow)
	 */
	public void setChip(int row, int col, String chip ) {
		board[row][col] = chip;
	}
	
	/**
	 * Returns chips location
	 * @param row Row
	 * @param col Column
	 * @return board[row][col]
	 */
	public String getChip(int row, int col) {
		return board[row][col];
	}
	
	/**
	 * Shows the board. This was used to see if the chips were actually going to the right place
	 */
	public void showBoard() {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				System.out.print(board[i][j] + "  ");
			}
			System.out.println("\n");
		}
	}
	//This code checks to see if the red player won.
	/**
	 * Formula to check if four red chips were connected 
	 */
	public boolean winCheckR() {
		boolean win = false;
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				//Diagonal check
				try {
					if(board[i][j] =="R" && board[i + 1][j + 1] =="R" && board[i + 2][j + 2] == "R" && board[i + 3][j + 3] == "R") {
						win = true;
					}		
				}catch(ArrayIndexOutOfBoundsException e) {
					//System.out.println("Array out of bounds");
				}
				//Diagonal check in opposite direction
				try {
					if(board[i][j] == "R" && board[i + 1][j - 1] == "R" && board[i + 2][j - 2] == "R" && board[i + 3][j - 3] == "R") {
						win = true;
					}		
				}catch(ArrayIndexOutOfBoundsException e) {
					//System.out.println("Array out of bounds");
				}
				//Horizontal check
				try {
					if(board[i][j] =="R" && board[i][j + 1] =="R" && board[i][j + 2] == "R" && board[i][j + 3] == "R") {
						win = true;
					}		
				}catch(ArrayIndexOutOfBoundsException e) {
					//System.out.println("Array out of bounds");
				}
				//Vertical check
				try {
					if(board[i][j] == "R" && board[i + 1][j] == "R" && board[i + 2][j] == "R" && board[i + 3][j] == "R") {
						win = true;
					}		
				}catch(ArrayIndexOutOfBoundsException e) {
					//System.out.println("Array out of bounds");
				}
			}
		}
		//System.out.println(win);
		return win;
	}
	
	//This code checks to see if the yellow player won.
	/**
	 * Formula to check if four yellow chips were connected 
	 */
	public boolean winCheckY() {
		boolean win = false;
		
				for(int i = 0; i < 6; i++) {
					for(int j = 0; j < 7; j++) {
						//diagonal check
						try {
							if(board[i][j] =="Y" && board[i + 1][j + 1] =="Y" && board[i + 2][j + 2] == "Y" && board[i + 3][j + 3] == "Y") {
								win = true;
							}		
						}catch(ArrayIndexOutOfBoundsException e) {
							//System.out.println("Array out of bounds");
						}
						//Diagonal check in opposite direction
						try {
							if(board[i][j] == "Y" && board[i + 1][j - 1] == "Y" && board[i + 2][j - 2] == "Y" && board[i + 3][j - 3] == "Y") {
								win = true;
							}		
						}catch(ArrayIndexOutOfBoundsException e) {
							//System.out.println("Array out of bounds");
						}
						//Horizontal check
						try {
							if(board[i][j] =="Y" && board[i][j + 1] =="Y" && board[i][j + 2] == "Y" && board[i][j + 3] == "Y") {
								win = true;
							}		
						}catch(ArrayIndexOutOfBoundsException e) {
							//System.out.println("Array out of bounds");
						}
						//Vertical check
						try {
							if(board[i][j] == "Y" && board[i + 1][j] == "Y" && board[i + 2][j] == "Y" && board[i + 3][j] == "Y") {
								win = true;
							}		
						}catch(ArrayIndexOutOfBoundsException e) {
							//System.out.println("Array out of bounds");
						}
					}
				}
				//System.out.println(win);
				return win;
	}
}
