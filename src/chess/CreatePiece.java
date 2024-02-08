package chess;

public class CreatePiece {

    // Method to create a ReturnPiece with the given type, file, and rank
    public static ReturnPiece createPiece(ReturnPiece.PieceType pieceType, ReturnPiece.PieceFile pieceFile, int pieceRank) {
        ReturnPiece piece = new ReturnPiece();
        piece.pieceType = pieceType;
        piece.pieceFile = pieceFile;
        piece.pieceRank = pieceRank;
        return piece;
    }
}