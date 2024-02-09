package chess;
// NEEDS WORK
import java.util.List;

public class Queen implements Piece {
    private final Color color; // The color of the QUEEN

    // Constructor to initialize the queen with its color
    public Queen(Color color) {
        this.color = color;
    }

    // Implement the method to return a list of legal moves for this queen
    public List<Move> getLegalMoves(Board board, Position position) {
        // Need to implement this method
        return null;  // Placeholder, replace with actual logic
    }

    // Method to return the type of this piece (QUEEN)
    public PieceType getType() {
        return PieceType.QUEEN;
    }

    // Method to return the color of this queen
    public Color getColor() {
        return this.color;
    }

    // Method to clone the queen
    public Piece clonePiece() {
        return new Queen(this.color);
    }
}
