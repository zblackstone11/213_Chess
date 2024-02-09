package chess;
// NEEDS WORK
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
        // Should return a list of possible legal moves for the king at the given position
        // Will need special logic to check for castling
            // 1. If in check, cannot castle, use isCheck method if get casle input first
            // 2. If king has moved, cannot castle, check board's hasMoved method
            // 3. If rook has moved, cannot castle, check board's hasMoved method
            // 4. If there are pieces between the king and rook, cannot castle, check board's getPieceAt method
            // 5. If the king would move through check, cannot castle, use isCheck method and simulate 
            //    the move to see if the king would be in check
            // 6. Cannot move into check, use simulate move to see if self check
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

    // Method to clone the king
    public Piece clonePiece() {
        return new King(this.color);
    }
}