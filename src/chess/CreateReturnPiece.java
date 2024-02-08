package chess;

public class CreateReturnPiece {

    // Method to create a ReturnPiece with the given type, file, and rank
    public static ReturnPiece createReturnPiece(ReturnPiece.PieceType pieceType, ReturnPiece.PieceFile pieceFile, int pieceRank) {
        ReturnPiece piece = new ReturnPiece();
        piece.pieceType = pieceType;
        piece.pieceFile = pieceFile;
        piece.pieceRank = pieceRank;
        return piece;
    }
}