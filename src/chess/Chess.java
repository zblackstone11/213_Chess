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
	public static ReturnPiece[][] board = new ReturnPiece[8][8];

	// Field to track the current player's turn
	public static Player currentPlayer = Player.white; // White starts first, update this field after each move

	// Field to track the movement status of important pieces for castling
	public static PieceMovementTracker pieceMovementTracker = new PieceMovementTracker();

	// Method to create a piece, since the ReturnPiece class has no constructor
	public static ReturnPiece createPiece(ReturnPiece.PieceType pieceType, ReturnPiece.PieceFile pieceFile, int pieceRank) {
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

		// If the move is a draw, return a ReturnPlay object with the appropriate message and the current board state
		else if (parsedMove.moveType == MoveType.DRAW) {
			// Check if the move is legal
			// Make move first as per the rules
			// Update the board with the move
			// Maybe reset the game
			returnPlay.piecesOnBoard = BoardToPieceListConverter.convertToPieceList(board);
			returnPlay.message = ReturnPlay.Message.DRAW;
			return returnPlay;
		}
		
		// If the move is an explicit pawn promotion, return a ReturnPlay object with the appropriate message and the current board state
		else if (parsedMove.moveType == MoveType.PAWN_PROMOTION) {
			// Check if the move is legal
			// Check if the move results in check or checkmate
			// Make move first as per the rules
			// Update the board with the move
			returnPlay.piecesOnBoard = BoardToPieceListConverter.convertToPieceList(board);
			returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return returnPlay;
		}
		
		// If the move is regular, check if the move is legal
		else if (parsedMove.moveType == MoveType.REGULAR) {
			// Check if the move is legal
			// Check if the move results in check or checkmate
			// Check if it is an implicit promotion to Queen, a castle, an en passant etc
			// If castle, check the piece movement tracker to see if the king or rook has moved before
			// Make move first as per the rules
			// Update the board with the move
			returnPlay.piecesOnBoard = BoardToPieceListConverter.convertToPieceList(board);
			returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return returnPlay;
		}

		// Otherwise an illegal move
		// ReturnPlay instance with pieces on board same as previous state of the board and message as ILLEGAL_MOVE
		else {
			returnPlay.piecesOnBoard = BoardToPieceListConverter.convertToPieceList(board);
			returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return returnPlay;
		}
	}
	
	// This method should reset the game, and start from scratch.
	public static void start() {
		// Initialize the 2D array board with nulls to start, resets the board
		BoardInitializer.resetBoard(board);
		// Initialize the board with pieces in their initial positions, resets the pieces
		BoardInitializer.initializeBoard(board);
		// In case the game was already in progress, reset the current player to white
		currentPlayer = Player.white;
		// Reset the movement status of each piece
		pieceMovementTracker.reset();
	}
}
