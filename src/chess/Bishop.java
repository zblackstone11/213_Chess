package chess;
import java.util.ArrayList;

import java.util.List;

public class Bishop implements Piece {
    private final Color color; // The color of the BISHOP

    // Constructor to initialize the bishop with its color
    public Bishop(Color color) {
        this.color = color;
    }

    // Tentative method to get the legal moves for the bishop
    public List<Move> getLegalMoves(Board board, Position position) {
        List<Move> legalMoves = new ArrayList<>();
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // Represents the 4 diagonal directions as (row, col) offsets in a 4x2 matrix
    
        for (int[] direction : directions) { // Vector of directions to move the bishop
            int rowOffset = direction[0]; // The row offset for the current direction, 1st element of the direction array
            int colOffset = direction[1]; // The column offset for the current direction, second element of the direction array
            int currentRow = position.getRow();
            int currentCol = position.getColumn();
    
            while (true) { // Loop to move in the current direction until the bishop encounters a piece or moves off the board
                currentRow += rowOffset; // Move the bishop one step in the current vertical direction
                currentCol += colOffset; // Move the bishop one step in the current horizontal direction
    
                // Check if the new position is outside the board boundaries
                if (currentRow < 0 || currentRow >= 8 || currentCol < 0 || currentCol >= 8) {
                    break; // Break out of the loop if the bishop moves off the board, move on to the next direction
                }
    
                Position targetPosition = new Position(currentRow, currentCol);
                Piece targetPiece = board.getPieceAt(targetPosition);
    
                // If the target square is empty, add the move and continue in the same direction
                if (targetPiece == null) {
                    legalMoves.add(new Move(position, targetPosition, this)); // this refer to current instance of Bishop
                } else {
                    // If the target square is occupied by an opponent's piece, add the move as a capture and break, cant move further in this direction
                    if (targetPiece.getColor() != this.color) {
                        legalMoves.add(new Move(position, targetPosition, this));
                    }
                    break; // Break out of the loop if the bishop encounters any piece, white or black, we've added if capture possible
                }
            }
        }
        return legalMoves;
    }    

    // Method to return the type of this piece (BISHOP)
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    // Method to return the color of this bishop
    public Color getColor() {
        return this.color;
    }

    // Method to clone the current bishop
    public Piece clonePiece() {
        return new Bishop(this.color);
    }
}
