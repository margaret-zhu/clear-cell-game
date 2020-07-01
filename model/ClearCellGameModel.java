package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear 
 * cell game, specifically.
 * 
 */

public class ClearCellGameModel extends GameModel {
	
	/* Include whatever instance variables you think are useful. */
	
	private Random random; //random variable
	private int score; // represents the player's score
	
	/**
	 * Defines a board with empty cells.  It relies on the
	 * super class constructor to define the board.
	 * 
	 * @param rows number of rows in board
	 * @param cols number of columns in board
	 * @param random random number generator to be used during game when
	 * rows are randomly created
	 */
	public ClearCellGameModel(int rows, int cols, Random random) {

		super(rows, cols);
		this.random = random;
		this.score = 0;
		
	}

	/**
	 * The game is over when the last row (the one with index equal
	 * to board.length-1) contains at least one cell that is not empty.
	 */		
	
	//loops through all board cells in last row and checks for empty cells
	//upon finding one, returns true, game is over
	public boolean isGameOver() {

		for(int i = 0; i<board[0].length; i++) {
			if(board[board.length-1][i] != BoardCell.EMPTY) {
				return true;
			}
		}
			
		return false;
	}

	/**
	 * Returns the player's score.  The player should be awarded 
	 * one point for each cell that is cleared.
	 * 
	 * @return player's score
	 */
	public int getScore() {
		
		return score;
		
	}

	
	/**
	 * This method must do nothing in the case where the game is over.
	 * 
	 * As long as the game is not over yet, this method will do 
	 * the following:
	 * 
	 * 1. Shift the existing rows down by one position.
	 * 2. Insert a row of random BoardCell objects at the top
	 * of the board. The row will be filled from left to right with 
	 * cells obtained by calling BoardCell.getNonEmptyRandomBoardCell()  
	 * (The Random number generator passed to the constructor of this 
	 * class should be passed as the argument to this method call.)
	 */
	public void nextAnimationStep() {
		
		//exits method if last row contains a cell that is not empty
		if(this.isGameOver()) { 
			return;
		}
		
		//shifts all existing rows down one
		for (int i = board.length - 1; i > 0; i--) {
			for (int col = 0; col < board[0].length; col++) {
				board[i][col] = board[i - 1][col];
			}

		}

		//fills JUST THE top row randomly
		for(int j = 0; j < board[0].length; j++) {

			board[0][j] = BoardCell.getNonEmptyRandomBoardCell(random);

		}

	}

	/**
	 * This method is called when the user clicks a cell on the board. If the
	 * selected cell is not empty, it will be set to BoardCell.EMPTY, along with any
	 * adjacent cells that are the same color as this one. (This includes the cells
	 * above, below, to the left, to the right, and all in all four diagonal
	 * directions.)
	 * 
	 * If any rows on the board become empty as a result of the removal of cells
	 * then those rows will "collapse", meaning that all non-empty rows beneath the
	 * collapsing row will shift upward.
	 * 
	 * @throws IllegalArgumentException with message "Invalid row index" for invalid
	 *                                  row or "Invalid column index" for invalid
	 *                                  column. We check for row validity first.
	 */
	public void processCell(int rowIndex, int colIndex) {
		

		int r = rowIndex;
		int c = colIndex;

		//checks all adjacent cells for same color
		if (board[r][c] != BoardCell.EMPTY) {

			BoardCell b = board[r][c];

			if ((r - 1) >= 0) {
				if (b.getColor() == board[r - 1][c].getColor()) {
					board[r - 1][c] = BoardCell.EMPTY;
					score++;
				}

				if ((c - 1) >= 0) {
					if (b.getColor() == board[r - 1]
							[c - 1].getColor()) {
						board[r - 1][c - 1] = BoardCell.EMPTY;
						score++;
					}
				}

				if ((c + 1) <= board[r].length - 1) {
					if (b.getColor() == board[r - 1]
							[c + 1].getColor()) {
						board[r - 1][c + 1] = BoardCell.EMPTY;
						score++;
					}
				}

			}

			if ((r + 1) <= board.length - 1) {
				if (b.getColor() == board[r + 1][c].getColor()) {
					board[r + 1][c] = BoardCell.EMPTY;
					score++;
				}
				if ((c + 1) <= board[r].length - 1) {
					if (b.getColor() == board[r + 1]
							[c + 1].getColor()) {
						board[r + 1][c + 1] = BoardCell.EMPTY;
						score++;
					}
				}

				if ((c - 1) >= 0) {
					if (b.getColor() == board[r + 1]
							[c - 1].getColor()) {
						board[r + 1][c - 1] = BoardCell.EMPTY;
						score++;
					}
				}

			}

			if ((c - 1) >= 0) {
				if (b.getColor() == board[r][c - 1].getColor()) {
					board[r][c - 1] = BoardCell.EMPTY;
					score++;
				}
			}

			if ((c + 1) <= board[r].length - 1) {
				if (b.getColor() == board[r][c + 1].getColor()) {
					board[r][c + 1] = BoardCell.EMPTY;
					score++;
				}
			}

			board[r][c] = BoardCell.EMPTY;
			score++;

			// checks if any rows become empty and if so, collapses rows
			
			for(int i = 0; i<board.length; i++) {
				for(int j = 0; j<board[i].length; j++) {
					if(board[i][j]!=BoardCell.EMPTY) {
						break;
					}else {
						if(j==board[i].length-1) {
							
							for(int k = i; k<board.length-1; k++) {
								for(int col = 0; col<board[k].length; 
										col++) {
									board[k][col] = board[k+1][col];
								}
								
							}
							
							for(int m = 0; m<board[board.length-1].
									length;m++) {
								board[board.length-1][m] = 
										BoardCell.EMPTY;
							}
						}
					}
				}
			}
			
			
			
		}
		
	
	
	
	
	
	
	}

}