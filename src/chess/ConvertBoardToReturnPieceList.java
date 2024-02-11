package chess;

import java.util.ArrayList;

public class ConvertBoardToReturnPieceList {
    public static ArrayList<ReturnPiece> convertToPieceList(Board board) { // Method to convert a Board of Pieces to an array list of ReturnPiece objects
        ArrayList<ReturnPiece> pieceList = new ArrayList<>(); // Create an empty list to store ReturnPiece objects, return this

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece currentPiece = board.getPieceAt(new Position(row, col));
                if (currentPiece != null) {
                    // Convert the Piece object to a ReturnPiece object
                    ReturnPiece returnPiece = convertPieceToReturnPiece(currentPiece, row, col);
                    pieceList.add(returnPiece);
                }
            }
        }
        return pieceList;
    }

    public static ReturnPiece convertPieceToReturnPiece(Piece piece, int row, int col) {
        // Use a mapping function to get the corresponding ReturnPiece.PieceType
        ReturnPiece.PieceType pieceType = mapPieceToReturnPieceType(piece);
        ReturnPiece.PieceFile pieceFile = ReturnPiece.PieceFile.values()[col]; // .values() function returns an array of the enum values from index 0, col corresponds to the column index of letters
        int pieceRank = row + 1; // Convert 0-based row to 1-based rank
    
        return CreateReturnPiece.createReturnPiece(pieceType, pieceFile, pieceRank);
    }
    
    public static ReturnPiece.PieceType mapPieceToReturnPieceType(Piece piece) {
        switch (piece.getType()) {
            case PAWN: // ternary operator to check the color of the piece and return the corresponding ReturnPiece.PieceType
                return piece.getColor() == Piece.Color.WHITE ? ReturnPiece.PieceType.WP : ReturnPiece.PieceType.BP;
            case ROOK: // logic is condition ? value_if_true : value_if_false
                return piece.getColor() == Piece.Color.WHITE ? ReturnPiece.PieceType.WR : ReturnPiece.PieceType.BR;
            case KNIGHT:
                return piece.getColor() == Piece.Color.WHITE ? ReturnPiece.PieceType.WN : ReturnPiece.PieceType.BN;
            case BISHOP:
                return piece.getColor() == Piece.Color.WHITE ? ReturnPiece.PieceType.WB : ReturnPiece.PieceType.BB;
            case QUEEN:
                return piece.getColor() == Piece.Color.WHITE ? ReturnPiece.PieceType.WQ : ReturnPiece.PieceType.BQ;
            case KING:
                return piece.getColor() == Piece.Color.WHITE ? ReturnPiece.PieceType.WK : ReturnPiece.PieceType.BK;
            default:
                throw new IllegalArgumentException("Unknown piece type or color");
        }
    }
}
