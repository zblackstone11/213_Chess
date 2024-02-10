package chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {
    private final Color color; // The color of the pawn

    // Constructor to initialize the pawn with its color
    public Pawn(Color color) {
        this.color = color;
    }

    // Implement the method to return a list of legal moves for this pawn
    public List<Move> getLegalMoves(Board board, Position position) {
        // For en passant, need to check if the last move was a double move by an opponent's pawn
        // use board's Chess class's priorMove field to check if the last move was a double move by an opponent's pawn
        // Also need to check for promotion to queen, so if white and on row 7, or black and on row 1?
        // might just wanna do that part elsewhere though
        List<Move> legalMoves = new ArrayList<>();
        int direction = (this.color == Color.WHITE) ? 1 : -1; // Corrected direction: White pawns move up (increase row), black pawns move down (decrease row)

        // Single square forward move
        Position oneStepForward = new Position(position.getRow() + direction, position.getColumn());
        if (isWithinBoard(oneStepForward) && board.getPieceAt(oneStepForward) == null) {
            legalMoves.add(new Move(position, oneStepForward, this));

            // Two squares forward move from starting position
            if ((this.color == Color.WHITE && position.getRow() == 1) || (this.color == Color.BLACK && position.getRow() == 6)) {
                Position twoStepsForward = new Position(position.getRow() + 2 * direction, position.getColumn());
                if (isWithinBoard(twoStepsForward) && board.getPieceAt(twoStepsForward) == null) {
                    legalMoves.add(new Move(position, twoStepsForward, this));
                }
            }
        }

        // Diagonal captures
        int[] captureOffsets = {1, -1}; // Capture to the right and left diagonals
        for (int offset : captureOffsets) {
            Position capturePos = new Position(position.getRow() + direction, position.getColumn() + offset);
            if (isWithinBoard(capturePos)) {
                Piece targetPiece = board.getPieceAt(capturePos);
                if (targetPiece != null && targetPiece.getColor() != this.color) {
                    legalMoves.add(new Move(position, capturePos, this));
                }
            }
        }

        return legalMoves;
    }

    // Helper method to check if a position is within the board
    private boolean isWithinBoard(Position position) {
        return position.getRow() >= 0 && position.getRow() < 8 && position.getColumn() >= 0 && position.getColumn() < 8;
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
