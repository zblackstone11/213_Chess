package chess;

import java.util.List;

public class King implements Piece {
    private final Color color; // The color of the KING

    // Constructor to initialize the pawn with its color
    public King(Color color) {
        this.color = color;
    }

    // Implement the method to return a list of legal moves for this king
    public List<Move> getLegalMoves(Board board, Position position) {
        // Need to implement this method
        return null;  // Placeholder, replace with actual logic
    }

    // Method to return the type of this piece (KING)
    public PieceType getType() {
        return PieceType.KING;
    }

    // Method to return the color of this king
    public Color getColor() {
        return this.color;
    }
}