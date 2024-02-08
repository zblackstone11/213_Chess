package chess;

import java.util.List;

public class Pawn implements Piece {
    private final Color color; // The color of the pawn

    // Constructor to initialize the pawn with its color
    public Pawn(Color color) {
        this.color = color;
    }

    // Implement the method to return a list of legal moves for this pawn
    public List<Move> getLegalMoves(Board board, Position position) {
        // Need to implement this method
        return null;  // Placeholder, replace with actual logic
    }

    // Method to return the type of this piece (PAWN)
    public PieceType getType() {
        return PieceType.PAWN;
    }

    // Method to return the color of this pawn
    public Color getColor() {
        return this.color;
    }
}
