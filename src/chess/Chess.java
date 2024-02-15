// Zack Blackstone and Stevie
package chess;

import java.util.ArrayList;
import java.util.List;

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

	// Field for the Board matrix at class level, so it can be accessed from anywhere in the class
	public static Board board = new Board();
	// Field to track the current player's turn, update after each move
	public static Player currentPlayer = Player.white;
	// We want a field for prior move here later, to check for en passant and self-check
	public static Move priorMove = null;

	// Getter for priorMove, for pawn class to implement en passant in Pawn class
	public static Move getPriorMove() {
		return priorMove;
	}
	
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
		if (parsedMove.moveType == MoveType.RESIGN) {
			returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
		
			if (currentPlayer == Player.white) {
				returnPlay.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
			} else {
				returnPlay.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
			}
			return returnPlay;
		}

		// If the move is an EXPLICIT pawn promotion, return a ReturnPlay object with the appropriate message and the current board state
		else if (parsedMove.moveType == MoveType.PAWN_PROMOTION) {
			// Generate the move object from the parsed move
			Move newmove = Move.convertParsedMoveToMove(parsedMove, board);
			// Get the piece at the start position of the move
			Piece movingPiece = board.getPieceAt(newmove.getStartPosition());
			// Get the list of legal moves for the piece at the start position
			// Check if there is no piece at the start position
			if (movingPiece == null) {
				returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
				return returnPlay; // Immediately return, indicating an illegal move due to selecting an empty square
			}
			List<Move> legalMoves = movingPiece.getLegalMoves(board, newmove.getStartPosition());
			// Check if the newmove is in the list of legal moves
			boolean isTentativelyLegal = legalMoves.contains(newmove);
			if (!isTentativelyLegal) {
				// If the move is not even tentatively legal, return ILLEGAL_MOVE
				returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
				return returnPlay;
			}
			// If the move is tentatively legal, check for self-check
			boolean resultsInSelfCheck = SelfCheckSimulator.simulateMove(board, newmove);
			if (resultsInSelfCheck) { 
				// If the move results in self-check, return ILLEGAL_MOVE
				returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
				return returnPlay;
			}
			// If the move is tentatively legal AND does not result in self-check, execute the move
			board = ExecuteMove.executeMove(newmove, board);
			priorMove = newmove; // Update prior move
			// IF PRIOR MOVE WAS EXPLICIT PAWN PROMOTION, PROMOTE PAWN USING EXPLICIT PAWN PROMOTION HANDLE HERE
			ExplicitPawnPromotion.promotePawn(priorMove, parsedMove, board); // possible solution needs testing
			Piece.Color opponentColor = (currentPlayer == Player.white) ? Piece.Color.BLACK : Piece.Color.WHITE;
			currentPlayer = (currentPlayer == Player.white) ? Player.black : Player.white; // Switch turn
			// Check if the move has put the opponent's king in check
			if (IsCheck.isCheck(board, opponentColor)) {
				// If the opponent's king is in check, check for checkmate
				if (IsCheck.isCheckmate(board, opponentColor)) {
					// If it's checkmate, set the message accordingly
					returnPlay.message = (opponentColor == Piece.Color.WHITE) ? ReturnPlay.Message.CHECKMATE_BLACK_WINS : ReturnPlay.Message.CHECKMATE_WHITE_WINS;
				} else {
					// If it's just check and not checkmate, set the message to CHECK
					returnPlay.message = ReturnPlay.Message.CHECK;
				}
			} else {
				returnPlay.message = null;
			}
			// Continue with setting pieces on board and returning returnPlay
			returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
			return returnPlay;
		}

		// If the move is a draw
		else if (parsedMove.moveType == MoveType.DRAW) {
			// Generate the move object from the parsed move
			Move newmove = Move.convertParsedMoveToMove(parsedMove, board);
			// Get the piece at the start position of the move
			Piece movingPiece = board.getPieceAt(newmove.getStartPosition());
			// Check if there is no piece at the start position
			if (movingPiece == null) {
				returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
				return returnPlay; // Immediately return, indicating an illegal move due to selecting an empty square
			}
			// Get the list of legal moves for the piece at the start position
			List<Move> legalMoves = movingPiece.getLegalMoves(board, newmove.getStartPosition());			
			// Check if the newmove is in the list of legal moves
			boolean isTentativelyLegal = legalMoves.contains(newmove);			
			if (!isTentativelyLegal) {
				// If the move is not even tentatively legal, return ILLEGAL_MOVE
				returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
				return returnPlay;
			}		
			// If the move is tentatively legal, check if it results in self-check
			 // result of the simulation is true if the move is illegal, false if the move is legal
			boolean resultsInSelfCheck = SelfCheckSimulator.simulateMove(board, newmove);
			if (resultsInSelfCheck) { 
				// If the move results in self-check, return ILLEGAL_MOVE
				returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
				return returnPlay;
			}
			// If the move is tentatively legal AND does not result in self-check, execute the move
			board = ExecuteMove.executeMove(newmove, board);
			priorMove = newmove; // Update prior move for potential en passant that occurs later in the game
			// Check if the move is an implicit or explicit pawn promotion
			if (move.contains("R") || move.contains("N") || move.contains("B") || move.contains("Q")) {
				// Set parsedmove.promotionPieceType to the letter that triggered the if statement above (R, N, or B)
				// This is a very speical case like 'e7 e8 R draw?' so we need to use the right character in the forgoing string
				parsedMove.setPromotionPieceType(move.charAt(move.length() - 7));
				ExplicitPawnPromotion.promotePawn(priorMove, parsedMove, board);
			}
			else {ImplicitPawnPromotion.promotePawn(priorMove, board);} // only changes if the move is an implicit pawn promotion
			Piece.Color opponentColor = (currentPlayer == Player.white) ? Piece.Color.BLACK : Piece.Color.WHITE;
			currentPlayer = (currentPlayer == Player.white) ? Player.black : Player.white; // Switch turn now that we saved the prior move info
			// Check if the move has put the opponent's king in check
			if (IsCheck.isCheck(board, opponentColor)) {
				// If the opponent's king is in check, check for checkmate
				if (IsCheck.isCheckmate(board, opponentColor)) {
					// If it's checkmate and opponent color is white, black wins, and vice versa
					returnPlay.message = (opponentColor == Piece.Color.WHITE) ? ReturnPlay.Message.CHECKMATE_BLACK_WINS : ReturnPlay.Message.CHECKMATE_WHITE_WINS;
				} else {
					// If it's just check and not checkmate, set the message to CHECK
					//returnPlay.message = ReturnPlay.Message.CHECK; // have to decide whether just to return draw or check here
					returnPlay.message = ReturnPlay.Message.DRAW; // have to decide whether just to return draw or check here
				}
			} else {
				returnPlay.message = ReturnPlay.Message.DRAW; // else it's a draw
			}
			// Continue with setting pieces on board and returning returnPlay
			returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
			return returnPlay;
		}
		
		// If the move is regular, check if the move is tentatively legal and doesn't result in self-check
		else { /* move type must be REGULAR, only other option assuming all inputs are properly formatted */
			// Generate the move object from the parsed move
			Move newmove = Move.convertParsedMoveToMove(parsedMove, board);
			// Get the piece at the start position of the move
			Piece movingPiece = board.getPieceAt(newmove.getStartPosition());
			// Get the list of legal moves for the piece at the start position
			// Check if there is no piece at the start position
			if (movingPiece == null) {
				returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
				return returnPlay; // Immediately return, indicating an illegal move due to selecting an empty square
			}
			List<Move> legalMoves = movingPiece.getLegalMoves(board, newmove.getStartPosition());
			// Check if the newmove is in the list of legal moves
			boolean isTentativelyLegal = legalMoves.contains(newmove);
			if (!isTentativelyLegal) {
				// If the move is not even tentatively legal, return ILLEGAL_MOVE
				returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
				return returnPlay;
			}
			// If the move is tentatively legal, check for self-check
			boolean resultsInSelfCheck = SelfCheckSimulator.simulateMove(board, newmove);
			if (resultsInSelfCheck) { 
				// If the move results in self-check, return ILLEGAL_MOVE
				returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
				return returnPlay;
			}
			// If the move is tentatively legal AND does not result in self-check, execute the move
			board = ExecuteMove.executeMove(newmove, board);
			priorMove = newmove; // Update prior move
			// IF PRIOR MOVE WAS IMPLICIT PAWN PROMOTION, PROMOTE PAWN TO QUEEN HANGLE HERE
			ImplicitPawnPromotion.promotePawn(priorMove, board);
			Piece.Color opponentColor = (currentPlayer == Player.white) ? Piece.Color.BLACK : Piece.Color.WHITE;
			currentPlayer = (currentPlayer == Player.white) ? Player.black : Player.white; // Switch turn
			// Check if the move has put the opponent's king in check
			if (IsCheck.isCheck(board, opponentColor)) {
				// If the opponent's king is in check, check for checkmate
				if (IsCheck.isCheckmate(board, opponentColor)) {
					// If it's checkmate, set the message accordingly
					returnPlay.message = (opponentColor == Piece.Color.WHITE) ? ReturnPlay.Message.CHECKMATE_BLACK_WINS : ReturnPlay.Message.CHECKMATE_WHITE_WINS;
				} else {
					// If it's just check and not checkmate, set the message to CHECK
					returnPlay.message = ReturnPlay.Message.CHECK;
				}
			} else {
				returnPlay.message = null;
			}
			// Continue with setting pieces on board and returning returnPlay
			returnPlay.piecesOnBoard = ConvertBoardToReturnPieceList.convertToPieceList(board);
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
	}
}
