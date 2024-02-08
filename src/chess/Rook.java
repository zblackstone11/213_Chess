package chess;

import java.util.List;

public class Rook implements Piece {
    private final Color color; // The color of the ROOK

    // Constructor to initialize the rook with its color
    public Rook(Color color) {
        this.color = color;
    }

    // Implement the method to return a list of legal moves for this rook
    public List<Move> getLegalMoves(Board board, Position position) {
        // Need to implement this method
        return null;  // Placeholder, replace with actual logic
    }

    // Method to return the type of this piece (ROOK)
    public PieceType getType() {
        return PieceType.ROOK;
    }

    // Method to return the color of this rook
    public Color getColor() {
        return this.color;
    }
}
