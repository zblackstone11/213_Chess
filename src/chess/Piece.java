package chess;

import java.util.List;

public interface Piece {

    // Enum to represent the types of the pieces
    enum PieceType {
        PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING
    }

    // Enum to represent the color of the piece
    enum Color {
        WHITE, BLACK
    }

    // Returns a list of legal moves for this piece from its current position
    List<Move> getLegalMoves(Board board, Position position);

    // Returns the type of this piece
    PieceType getType();

    // Returns the color of this piece
    Color getColor();

    // Returns a clone of this piece
    public Piece clonePiece();
}
