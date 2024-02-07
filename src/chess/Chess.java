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
	public static Player currentPlayer = Player.white; // White starts first, update this field after each move

	// Method to create a piece, since the ReturnPiece class has no constructor
	protected static ReturnPiece createPiece(ReturnPiece.PieceType pieceType, ReturnPiece.PieceFile pieceFile, int pieceRank) {
		ReturnPiece piece = new ReturnPiece();
		piece.pieceType = pieceType;
		piece.pieceFile = pieceFile;
		piece.pieceRank = pieceRank;
		return piece;
	}
	
	enum Player { white, black }
	
	/**
	 * @param move String for next move, e.g. "a2 a3"
	 * @return A ReturnPlay instance that contains the result of the move.
	 */
	public static ReturnPlay play(String move) {
		
		// 1. Parse the move string into the from and to squares and the move type
		ParsedMove parsedMove = MoveParser.parseMove(move);
		ReturnPlay returnPlay = new ReturnPlay();

		// If the move is a resignation, return a ReturnPlay object with the appropriate message and the current board state
		if (parsedMove.moveType == MoveType.RESIGN) {
			returnPlay.piecesOnBoard = BoardToPieceListConverter.convertToPieceList(board);
		
			if (currentPlayer == Player.white) {
				returnPlay.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
			} else {
				returnPlay.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
			}
			return returnPlay;
		}		

		// 2. Check if the move is legal for other parseMove types DRAW, REGULAR, PAWN_PROMOTION
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
		
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		return null; // comment this out when done with everything else
	}
	
	// This method should reset the game, and start from scratch.
	public static void start() {
		// Initialize the 2D array board with nulls to start, resets the board
		BoardInitializer.resetBoard(board);
		// Initialize the board with pieces in their initial positions, resets the pieces
		BoardInitializer.initializeBoard(board);
		// In case the game was already in progress, reset the current player to white
		currentPlayer = Player.white;
	}
}
