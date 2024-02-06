package chess;

import java.util.ArrayList;

class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
		            BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank;  // 1..8
	public String toString() {
		return ""+pieceFile+pieceRank+":"+pieceType;
	}
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece)other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

class ReturnPlay {
	enum Message {ILLEGAL_MOVE, DRAW, 
				  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
				  CHECK, CHECKMATE_BLACK_WINS,	CHECKMATE_WHITE_WINS, 
				  STALEMATE};
	
	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}

public class Chess {

	// Field for the board array at class level, so that it can be accessed from any method in the class
	private static ReturnPiece[][] board = new ReturnPiece[8][8];

	// Field to track the current player's turn
	private static Player currentPlayer = Player.white; // White starts first, update this field after each move

	// Method to create a piece, since the ReturnPiece class has no constructor
	protected static ReturnPiece createPiece(ReturnPiece.PieceType pieceType, ReturnPiece.PieceFile pieceFile, int pieceRank) {
		ReturnPiece piece = new ReturnPiece();
		// Directly assign the values to the fields of the piece object
		piece.pieceType = pieceType;
		piece.pieceFile = pieceFile;
		piece.pieceRank = pieceRank;
		return piece;
	}
	
	enum Player { white, black }
	
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {

		/* FILL IN THIS METHOD */
		
		// 1. Parse the move string into the from and to squares
		// Going to move this to a separate class called MoveParser, more complex than I realized, forgot special moves below
		String trimmedMove = move.trim(); // Remove leading and trailing whitespace
		String[] squares = trimmedMove.split(" "); // Split the string into two squares
		String fromSquare = squares[0]; // The first square is the from square
		String toSquare = squares[1]; // The second square is the to square

		// 2. Check if the move is legal
			// The move is legal if:
			// a. White is moving and it's white's turn, or black is moving and it's black's turn
			// b. Check if the from square contains a piece
			// c. Check if the piece can move to the to square
			// d. Check if the to square contains a piece of the same color
			// e. Check if the move puts the moving player in check (self-check)
			// f. Check if the move puts the opponent in check

		// 3. If the move is legal, execute the move and update the 2D array board

		// 4. Check if the move puts the opponent in check or checkmate

		// 5. Convert the 2D array board into an ArrayList of ReturnPiece objects

		// 6. Create a new returnPlay object, setting the piecesOnBoard field with the ArrayList from step 5, and
		// 	  set the message field with the appropriate message
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		return null;
	}
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */

		// Initialize the 2D array board with nulls to start, resets the board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}
		// Initialize the board with pieces in their initial positions, resets the pieces
		BoardInitializer.initializeBoard(board);
	}
}
