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

	// Method to create a piece since the ReturnPiece class has no constructor
	private static ReturnPiece createPiece(ReturnPiece.PieceType pieceType, ReturnPiece.PieceFile pieceFile, int pieceRank) {
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
		
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		return null;
	}
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */

		// Initialize the 2D array board with nulls to start, just to be safe
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}
		// Might make this a separate class later to keep the code clean
		// Initialize the white rooks
		board[0][0] = createPiece(ReturnPiece.PieceType.WR, ReturnPiece.PieceFile.a, 1);
		board[0][7] = createPiece(ReturnPiece.PieceType.WR, ReturnPiece.PieceFile.h, 1);

		// Initialize the white knights
		board[0][1] = createPiece(ReturnPiece.PieceType.WN, ReturnPiece.PieceFile.b, 1);
		board[0][6] = createPiece(ReturnPiece.PieceType.WN, ReturnPiece.PieceFile.g, 1);

		// Initialize the white bishops
		board[0][2] = createPiece(ReturnPiece.PieceType.WB, ReturnPiece.PieceFile.c, 1);
		board[0][5] = createPiece(ReturnPiece.PieceType.WB, ReturnPiece.PieceFile.f, 1);

		// Initialize the white queen
		board[0][3] = createPiece(ReturnPiece.PieceType.WQ, ReturnPiece.PieceFile.d, 1);

		// Initialize the white king
		board[0][4] = createPiece(ReturnPiece.PieceType.WK, ReturnPiece.PieceFile.e, 1);

		// Initialize the white pawns using the enum values() method, which returns an array of the enum values
		// This way, we can iterate through the array and initialize the pawns in a loop
		for (int i = 0; i < 8; i++) {
			board[1][i] = createPiece(ReturnPiece.PieceType.WP, ReturnPiece.PieceFile.values()[i], 2);
		}

		// Initialize the black rooks
		board[7][0] = createPiece(ReturnPiece.PieceType.BR, ReturnPiece.PieceFile.a, 8);
		board[7][7] = createPiece(ReturnPiece.PieceType.BR, ReturnPiece.PieceFile.h, 8);

		// Initialize the black knights
		board[7][1] = createPiece(ReturnPiece.PieceType.BN, ReturnPiece.PieceFile.b, 8);
		board[7][6] = createPiece(ReturnPiece.PieceType.BN, ReturnPiece.PieceFile.g, 8);

		// Initialize the black bishops
		board[7][2] = createPiece(ReturnPiece.PieceType.BB, ReturnPiece.PieceFile.c, 8);
		board[7][5] = createPiece(ReturnPiece.PieceType.BB, ReturnPiece.PieceFile.f, 8);

		// Initialize the black queen
		board[7][3] = createPiece(ReturnPiece.PieceType.BQ, ReturnPiece.PieceFile.d, 8);

		// Initialize the black king
		board[7][4] = createPiece(ReturnPiece.PieceType.BK, ReturnPiece.PieceFile.e, 8);

		// Initialize the black pawns
		for (int i = 0; i < 8; i++) {
			board[6][i] = createPiece(ReturnPiece.PieceType.BP, ReturnPiece.PieceFile.values()[i], 7);
		}
	}
}
