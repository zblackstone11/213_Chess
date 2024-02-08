package chess;

import java.util.ArrayList;

public class ConvertBoardToReturnPieceList {
    public static ArrayList<ReturnPiece> convertToPieceList(Board board) { // Method to convert a Board of Pieces to an array list of ReturnPiece objects
        ArrayList<ReturnPiece> pieceList = new ArrayList<>(); // Create an empty list to store ReturnPiece objects

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece currentPiece = board.getPieceAt(new Position(row, col));
                if (currentPiece != null) { // don't add null pieces to the list
                    // Convert the Piece object to a ReturnPiece object
                    ReturnPiece returnPiece = convertPieceToReturnPiece(currentPiece, row, col);
                    pieceList.add(returnPiece);
                }
            }
        }
        return pieceList;
    }

    private static ReturnPiece convertPieceToReturnPiece(Piece piece, int row, int col) { // Method to convert a Piece to a ReturnPiece, might want to make public later
        // Logic to convert a Piece to a ReturnPiece based on its type and color
        // This will involve mapping PieceType and Color from Piece to ReturnPiece.PieceType, can use first letters of each?
        // And converting row and col to a rank and file in that order
        // Get the first letter of tHe Piece color and first letter of piece type, concatenate them, and cast to ReturnPiece.PieceType
        // Wont work as is need mapping function since the enum type etc. is different
        ReturnPiece.PieceType pieceType = ReturnPiece.PieceType.(piece.getColor().toString().charAt(0) + piece.getType().toString().charAt(0));
        ReturnPiece.PieceFile pieceFile = ReturnPiece.PieceFile.values()[col]; // need to test this, not sure if values() works from 0 or 1 based
        int pieceRank = row + 1; // Chess ranks are 1-based so add 1 to the row we are at in the loop

        return CreateReturnPiece.createReturnPiece(pieceType, pieceFile, pieceRank);
    }
}
