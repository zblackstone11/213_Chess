package chess;
// NEEDS WORK
import java.util.List;

public class Knight implements Piece {
    private final Color color; // The color of the knight

    // Constructor to initialize the pawn with its color
    public Knight(Color color) {
        this.color = color;
    }

    // Implement the method to return a list of legal moves for this knight
    public List<Move> getLegalMoves(Board board, Position position) {
        // Need to implement this method
        return null;  // Placeholder, replace with actual logic
    }

    // Method to return the type of this piece (KNIGHT)
    public PieceType getType() {
        return PieceType.KNIGHT;
    }

    // Method to return the color of this knight
    public Color getColor() {
        return this.color;
    }

    // Method to clone the knight
    public Piece clonePiece() {
        return new Knight(this.color);
    }
    
}
