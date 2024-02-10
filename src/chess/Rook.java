package chess;
import java.util.ArrayList;
// NEEDS WORK
import java.util.List;

public class Rook implements Piece {
    private final Color color; // The color of the ROOK

    // Constructor to initialize the rook with its color
    public Rook(Color color) {
        this.color = color;
    }

    // method to return a list of legal moves for this rook
    public List<Move> getLegalMoves(Board board, Position position) {
        List<Move> legalMoves = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Represents up, down, right, and left

        for (int[] direction : directions) {
            int rowOffset = direction[0];
            int colOffset = direction[1];
            int currentRow = position.getRow();
            int currentCol = position.getColumn();

            while (true) {
                currentRow += rowOffset;
                currentCol += colOffset;

                // Check if the new position is outside the board boundaries
                if (currentRow < 0 || currentRow >= 8 || currentCol < 0 || currentCol >= 8) {
                    break; // Break out of the loop if the rook moves off the board
                }

                Position targetPosition = new Position(currentRow, currentCol);
                Piece targetPiece = board.getPieceAt(targetPosition);

                // If the target square is empty, add the move and continue in the same direction
                if (targetPiece == null) {
                    legalMoves.add(new Move(position, targetPosition, this));
                } else {
                    // If the target square is occupied by an opponent's piece, add the move as a capture and break
                    if (targetPiece.getColor() != this.color) {
                        legalMoves.add(new Move(position, targetPosition, this));
                    }
                    break; // Break out of the loop if the rook encounters any piece
                }
            }
        }
        return legalMoves;
    }


    // Method to return the type of this piece (ROOK)
    public PieceType getType() {
        return PieceType.ROOK;
    }

    // Method to return the color of this rook
    public Color getColor() {
        return this.color;
    }
    // Method to clone the rook
    public Piece clonePiece() {
        return new Rook(this.color);
    }
}
