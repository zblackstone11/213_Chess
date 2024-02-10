package chess;
import java.util.ArrayList;
// NEEDS WORK
import java.util.List;

public class King implements Piece {
    private final Color color; // The color of the KING

    // Constructor to initialize the pawn with its color
    public King(Color color) {
        this.color = color;
    }

    // method to return a list of legal moves for this king
    public List<Move> getLegalMoves(Board board, Position position) {
        List<Move> legalMoves = new ArrayList<>();
        // Directions the King can move: horizontally, vertically, and diagonally, one square
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},  // Horizontal and vertical
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // Diagonal
        };
        for (int[] direction : directions) {
            int targetRow = position.getRow() + direction[0];
            int targetCol = position.getColumn() + direction[1];

            // Check if the new position is within the board boundaries
            if (targetRow >= 0 && targetRow < 8 && targetCol >= 0 && targetCol < 8) {
                Position targetPosition = new Position(targetRow, targetCol);
                Piece targetPiece = board.getPieceAt(targetPosition);

                // The King can (tentatively) move to the target square if it's empty or occupied by an opponent's piece
                if (targetPiece == null || targetPiece.getColor() != this.color) {
                    legalMoves.add(new Move(position, targetPosition, this));
                }
            }
        }
               // Will need special logic to check for castling
            // 1. If in check, cannot castle, use isCheck method if get casle input first
            // 2. If king has moved, cannot castle, check board's hasMoved method
            // 3. If rook has moved, cannot castle, check board's hasMoved method
            // 4. If there are pieces between the king and rook, cannot castle, check board's getPieceAt method
            // 5. If the king would move through check, cannot castle, use isCheck method and simulate 
            //    the move to see if the king would be in check
            // 6. Cannot move into check, use simulate move to see if self check
        return legalMoves;
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