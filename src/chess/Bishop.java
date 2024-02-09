package chess;
// NEEDS WORK
import java.util.List;

public class Bishop implements Piece {
    private final Color color; // The color of the BISHOP

    // Constructor to initialize the bishop with its color
    public Bishop(Color color) {
        this.color = color;
    }

    // Implement the method to return a list of legal moves for this bishop
    public List<Move> getLegalMoves(Board board, Position position) {
        // Need to implement this method
        return null;  // Placeholder, replace with actual logic
    }

    // Method to return the type of this piece (BISHOP)
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    // Method to return the color of this bishop
    public Color getColor() {
        return this.color;
    }

    // Method to clone the bishop
    public Piece clonePiece() {
        return new Bishop(this.color);
    }
}
