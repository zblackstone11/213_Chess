package chess;
import java.util.ArrayList;
// NEEDS WORK
import java.util.List;

public class Queen implements Piece {
    private final Color color; // The color of the QUEEN

    // Constructor to initialize the queen with its color
    public Queen(Color color) {
        this.color = color;
    }

    // method to return a list of legal moves for this queen
    public List<Move> getLegalMoves(Board board, Position position) {
        List<Move> legalMoves = new ArrayList<>();
        // Combines the directions of both Rook and Bishop
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},  // Rook's horizontal and vertical vectors
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // Bishop's diagonal vectors
        };

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
                    break; // Break out of the loop if the queen moves off the board
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
                    break; // Break out of the loop if the queen encounters any piece
                }
            }
        }
        return legalMoves;
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
