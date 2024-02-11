package chess;

public class IsCheck {
    // method to check if the king of the given color is in check
    public static boolean isCheck(Board board, Piece.Color color) {
        // Find the position of the king of the given color
        Position kingPosition = findKingPosition(board, color);
        // Check if the king is in check
        return isCheck(board, kingPosition, color);
    }

    // method to check if the king at the given position is in check, equivaltnt to asking 'am I in check?' not 'is the other king in check?'
    public static boolean isCheck(Board board, Position kingPosition, Piece.Color color) {
        // Iterate through the board to find the pieces of the OPPOSITE color
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position position = new Position(row, col);
                Piece piece = board.getPieceAt(position);
                if (piece != null && piece.getColor() != color) {
                    // Check if the piece can attack the king by iterating through its legal moves and checking if any of them attack the king
                    for (Move move : piece.getLegalMoves(board, position)) {
                        // don't have an equals method so we have to compare the positions manually
                        if (move.getEndPosition().getRow() == kingPosition.getRow() && move.getEndPosition().getColumn() == kingPosition.getColumn()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // method to find the position of the king of the given color
    private static Position findKingPosition(Board board, Piece.Color color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position position = new Position(row, col);
                Piece piece = board.getPieceAt(position);
                if (piece != null && piece.getType() == Piece.PieceType.KING && piece.getColor() == color) {
                    return position;
                }
            }
        }
        // return error message if the king is not found
        return null; // this should never happen
    }

    // method to check if the king of the given color is in checkmate, am I in checkmate?
    public static boolean isCheckmate(Board board, Piece.Color color) {
        // Check if the king is in check
        if (!isCheck(board, color)) {
            return false;
        }
        // Iterate through the board to find the pieces of the same color as the king, (including the king itself)
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position position = new Position(row, col);
                Piece piece = board.getPieceAt(position);
                if (piece != null && piece.getColor() == color) { // we want to look at the pieces of the same color as the king "our own movess"
                    // Check if any of the pieces of the given color have a legal move that gets King out of check (like blocking the attack or capturing the attacking piece or moving the king)
                    for (Move move : piece.getLegalMoves(board, position)) {
                        if (!SelfCheckSimulator.simulateMove(board, new Move(position, move.getEndPosition(), piece))) { // If making this move does NOT result in self-check
                            return false; // we found a move that does not reslt in self-check, so we are not in checkmate
                        }
                    }
                }
            }
        }
        return true; // no valid moves found that resolve the check or keep king from being captured, so we are in checkmate
    }
}
