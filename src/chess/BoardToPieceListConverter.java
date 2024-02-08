package chess;

import java.util.ArrayList;

// Class to convert the current board state into an ArrayList of ReturnPiece objects, needed for each move
public class BoardToPieceListConverter {
    
    // Method to convert the current board state into an ArrayList of ReturnPiece objects
    public static ArrayList<ReturnPiece> convertToPieceList(ReturnPiece[][] board) {
        ArrayList<ReturnPiece> piecesOnBoard = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ReturnPiece piece = board[i][j];
                if (piece != null) {
                    ReturnPiece pieceToAdd = CreatePiece.createPiece(piece.pieceType, piece.pieceFile, piece.pieceRank);
                    piecesOnBoard.add(pieceToAdd);
                }
            }
        }
        return piecesOnBoard;
    }
}