package chess;
import java.util.ArrayList;
// NEEDS WORK FOR CASTLING
import java.util.List;

public class King implements Piece {
    private final Color color; // The color of the KING

    // Constructor to initialize the king with its color
    public King(Color color) {
        this.color = color;
    }

    // method to return a list of legal moves for this king
    public List<Move> getLegalMoves(Board board, Position position) {
        List<Move> legalMoves = new ArrayList<>();
        // Directions the King can move: horizontally, vertically, and diagonally, one square
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},  // Horizontal and vertical vectors
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // Diagonal vectors
        };
        for (int[] direction : directions) {
            int targetRow = position.getRow() + direction[0];
            int targetCol = position.getColumn() + direction[1];

            // Check if the new position is within the board boundaries
            if (targetRow >= 0 && targetRow < 8 && targetCol >= 0 && targetCol < 8) {
                Position targetPosition = new Position(targetRow, targetCol);
                Piece targetPiece = board.getPieceAt(targetPosition);

                // The King can (tentatively) move to the target square if it's empty or occupied by an opponent's piece
                // wonder if we need logic for only king and king on board being drawn...
                if (targetPiece == null || targetPiece.getColor() != this.color) {
                    legalMoves.add(new Move(position, targetPosition, this));
                }
            }
        }
        // Castling logic
        // Determine the row based on the king's color
        int kingRow = (this.color == Color.WHITE) ? 0 : 7;
        if (!IsCheck.isCheckWithoutKing(board, new Position(kingRow, 4), this.color)) {
            // Logic for white king first
            if (this.color == Color.WHITE) {
                // Logic for white king side castle
                if (!board.getHasMoved(new Position(0, 4)) && !board.getHasMoved(new Position(0, 7))) {
                    // New position for adjacent square to the right of the king
                    Position rightOfKing = new Position(0, 5);
                    Position rightOfRightOfKing = new Position(0, 6);
                    Position kingPosition = new Position(0, 4);
                    Position rookPosition = new Position(0, 7);
                    if (board.getPieceAt(rightOfKing) == null && board.getPieceAt(rightOfRightOfKing) == null) {
                        if (!IsCheck.isCheckWithoutKing(board, rightOfKing, this.color) && !IsCheck.isCheckWithoutKing(board, rightOfRightOfKing, this.color)) {
                            Position potentialOpposingKingPosition = new Position(1, 6);  // Just need to check one position for opposing king
                            Piece pieceAtPosition = board.getPieceAt(potentialOpposingKingPosition);
                            if (pieceAtPosition == null || !(pieceAtPosition.getType() == Piece.PieceType.KING)) {
                                // This condition now checks if the position is either empty or does not contain an opposing king, all others have been checked above
                                legalMoves.add(new Move(kingPosition, rightOfRightOfKing, this, rookPosition, rightOfKing));
                            }
                        }
                    }
                }
                // Logic for white queen side castle
                if (!board.getHasMoved(new Position(0, 4)) && !board.getHasMoved(new Position(0, 0))) {
                    // New position for adjacent square to the left of the king
                    Position leftOfKing = new Position(0, 3);
                    Position leftOfLeftOfKing = new Position(0, 2);
                    Position kingPosition = new Position(0, 4);
                    Position rookPosition = new Position(0, 0);
                    if (board.getPieceAt(leftOfKing) == null && board.getPieceAt(leftOfLeftOfKing) == null) {
                        if (!IsCheck.isCheckWithoutKing(board, leftOfKing, this.color) && !IsCheck.isCheckWithoutKing(board, leftOfLeftOfKing, this.color)) {
                            Position potentialOpposingKingPosition = new Position(1, 1);  // Just need to check two position for opposing king for queenside
                            Piece pieceAtPosition = board.getPieceAt(potentialOpposingKingPosition);
                            if (pieceAtPosition == null || !(pieceAtPosition.getType() == Piece.PieceType.KING)) {
                                Position potentialOpposingKingPosition2 = new Position(1, 2);  // Just need to check two position for opposing king for queenside
                                Piece pieceAtPosition2 = board.getPieceAt(potentialOpposingKingPosition2);
                                if (pieceAtPosition2 == null || !(pieceAtPosition2.getType() == Piece.PieceType.KING)) {
                                    // This condition now checks if the position is either empty or does not contain an opposing king.
                                    legalMoves.add(new Move(kingPosition, leftOfLeftOfKing, this, rookPosition, leftOfKing));
                                }
                            }
                        }
                    }
                }
            }
            // Logic for black king
            else if (this.color == Color.BLACK) {
                // Logic for black king side castle
                if (!board.getHasMoved(new Position(7, 4)) && !board.getHasMoved(new Position(7, 7))) {
                    // New position for adjacent square to the right of the king
                    Position rightOfKing = new Position(7, 5);
                    Position rightOfRightOfKing = new Position(7, 6);
                    Position kingPosition = new Position(7, 4);
                    Position rookPosition = new Position(7, 7);
                    if (board.getPieceAt(rightOfKing) == null && board.getPieceAt(rightOfRightOfKing) == null) {
                        if (!IsCheck.isCheckWithoutKing(board, rightOfKing, this.color) && !IsCheck.isCheckWithoutKing(board, rightOfRightOfKing, this.color)) {
                            Position potentialOpposingKingPosition = new Position(6, 6);  // Just need to check one position for opposing king
                            Piece pieceAtPosition = board.getPieceAt(potentialOpposingKingPosition);
                            if (pieceAtPosition == null || !(pieceAtPosition.getType() == Piece.PieceType.KING)) {
                                // This condition now checks if the position is either empty or does not contain an opposing king.
                                legalMoves.add(new Move(kingPosition, rightOfRightOfKing, this, rookPosition, rightOfKing));
                            }
                        }
                    }
                }
                // Logic for black queen side castle
                if (!board.getHasMoved(new Position(7, 4)) && !board.getHasMoved(new Position(7, 0))) {
                    // New position for adjacent square to the left of the king
                    Position leftOfKing = new Position(7, 3);
                    Position leftOfLeftOfKing = new Position(7, 2);
                    Position kingPosition = new Position(7, 4);
                    Position rookPosition = new Position(7, 0);
                    if (board.getPieceAt(leftOfKing) == null && board.getPieceAt(leftOfLeftOfKing) == null) {
                        if (!IsCheck.isCheckWithoutKing(board, leftOfKing, this.color) && !IsCheck.isCheckWithoutKing(board, leftOfLeftOfKing, this.color)) {
                            Position potentialOpposingKingPosition = new Position(6,1);  // Just need to check two position for opposing king for queenside
                            Piece pieceAtPosition = board.getPieceAt(potentialOpposingKingPosition);
                            if (pieceAtPosition == null || !(pieceAtPosition.getType() == Piece.PieceType.KING)) {
                                Position potentialOpposingKingPosition2 = new Position(6, 2);  // Just need to check two position for opposing king for queenside
                                Piece pieceAtPosition2 = board.getPieceAt(potentialOpposingKingPosition2);
                                if (pieceAtPosition2 == null || !(pieceAtPosition2.getType() == Piece.PieceType.KING)) {
                                    // This condition now checks if the position is either empty or does not contain an opposing king.
                                    legalMoves.add(new Move(kingPosition, leftOfLeftOfKing, this, rookPosition, leftOfKing));
                                }
                            }
                        }
                    }
                }
            }
        }
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