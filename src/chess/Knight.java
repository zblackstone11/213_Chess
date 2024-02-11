package chess;
import java.util.ArrayList;
// NEEDS WORK
import java.util.List;

public class Knight implements Piece {
    private final Color color; // The color of the knight

    // Constructor to initialize the knight with its color
    public Knight(Color color) {
        this.color = color;
    }

    // method to return a list of legal moves for this knight
    public List<Move> getLegalMoves(Board board, Position position) {
        List<Move> legalMoves = new ArrayList<>();
        int[][] knightMoves = {{-2, -1}, {-2, 1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {-1, 2}, {1, 2}}; // explicitly enumerated 8 moves

        for (int[] move : knightMoves) { // for each vector in knightMoves
            int targetRow = position.getRow() + move[0]; // vertical component of the move
            int targetCol = position.getColumn() + move[1]; // horizontal component of the move

            // Check if the move is within the board boundaries
            if (targetRow >= 0 && targetRow < 8 && targetCol >= 0 && targetCol < 8) {
                Position targetPosition = new Position(targetRow, targetCol);
                Piece targetPiece = board.getPieceAt(targetPosition);

                // Check if the target square is empty or occupied by an opponent's piece
                if (targetPiece == null || targetPiece.getColor() != this.color) {
                    legalMoves.add(new Move(position, targetPosition, this));
                }
            }
        }
        return legalMoves;
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
