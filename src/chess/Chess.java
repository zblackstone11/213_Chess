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

	// Field for the Board matrix at class level, so that it can be accessed from any method in the class/package
	public static Board board = new Board();

	// Field to track the current player's turn, update after each move
	public static Player currentPlayer = Player.white;

	// We want a field for prior move here later, to check for en passant and self-check
	public static String priorMove = null;
	
	enum Player { white, black }
	
	/**
	 * @param move String for next move, e.g. "a2 a3"
	 * @return A ReturnPlay instance that contains the result of the move.
	 */

	public static ReturnPlay play(String move) {

		// 1. Parse the move string into the from and to squares and the move type if it's explcit from the string
		ParsedMove parsedMove = MoveParser.parseMove(move);
		ReturnPlay returnPlay = new ReturnPlay(); // holder for the return value

		// If the move is a resignation, return a ReturnPlay object with the appropriate message and the current board state
		// Fully implemented as is
		if (parsedMove.moveType == MoveType.RESIGN) {
			returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
		
			if (currentPlayer == Player.white) {
				returnPlay.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
			} else {
				returnPlay.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
			}
			return returnPlay;
		}

		// If the move is a draw, return a ReturnPlay object with the appropriate message and the current board state
		else if (parsedMove.moveType == MoveType.DRAW) {
			// Can be a regular move, a castle, an implicit pawn promotion to queen, or an en passant
			// Check if the move is legal, only continue if it is, else return an illegal move message and the current board state
			// Make move first as per the rules
			// Update the board with the move
			// Maybe reset the game explicitly here
			// returnPlay.piecesOnBoard = BoardToPieceListConverter.convertToPieceList(board);
			returnPlay.message = ReturnPlay.Message.DRAW;
			return returnPlay;
		}
		
		// If the move is an EXPLICIT pawn promotion, return a ReturnPlay object with the appropriate message and the current board state
		else if (parsedMove.moveType == MoveType.PAWN_PROMOTION) {
			// Check if the move is legal, only continue if it is
			// Make move
			// Check if the move results in check OR checkmate
			// Update the board with the move
			// Update the prior move field
			// returnPlay.piecesOnBoard = BoardToPieceListConverter.convertToPieceList(board);
			returnPlay.message = null; // Could be CHECK, CHECKMATE_BLACK_WINS, CHECKMATE_WHITE_WINS, ILLEGAL_MOVE
			return returnPlay;
		}
		
		// If the move is regular, check if the move is legal, will be hardest to implement
		else /* move type must be REGULAR, only other option assuming all inputs are properly formatted */ {
			// Can be a regular move, a castle, an implicit pawn promotion to queen, or an en passant
			// returnPlay.piecesOnBoard = BoardToPieceListConverter.convertToPieceList(board);
			returnPlay.message = null; // Could be CHECK, CHECKMATE_BLACK_WINS, CHECKMATE_WHITE_WINS, ILLEGAL_MOVE
			return returnPlay;
		}
	}
	
	// This method should reset the game, and start from scratch.
	public static void start() {
		// Initialize the 2D array board with nulls to start, resets the board
		board.resetBoard();
		// Initialize the board with pieces in their initial positions, resets the pieces
		board.initializeBoard();
		// In case the game was already in progress, reset the current player to white
		currentPlayer = Player.white;
		// Reset the movement status of each piece to false
		board.resetHasMoved();;
		// Reset the prior move to null
		priorMove = null;
		// Do we need to return a returnPlay object here?
	}
}
