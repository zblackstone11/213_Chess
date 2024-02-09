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
        // For en passant, need to check if the last move was a double move by an opponent's pawn
        // use board's Chess class's priorMove field to check if the last move was a double move by an opponent's pawn
        // Also need to check for implicit promotion to queen, so if white and on row 7, or black and on row 1
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

    // Method to clone the pawn
    public Piece clonePiece() {
        return new Pawn(this.color);
    }
    
}
