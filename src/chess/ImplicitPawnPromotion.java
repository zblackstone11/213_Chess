package chess;

public class ImplicitPawnPromotion {
    // TAKES PRIOR MOVE AND RETURNS BOARD WITH IMPLICIT PAWN PROMOTION TO QUEEN
    public static Board promotePawn(Move priorMove, Board board) {
        // Get the start and end positions of the prior move
        Position start = priorMove.getStartPosition();
        Position end = priorMove.getEndPosition();
        // Get the piece at the end position
        Piece piece = board.getPieceAt(end);
        // Check if the piece is a pawn
        if (piece instanceof Pawn) {
            // Check if the pawn has reached the opposite end of the board
            if (end.getRow() == 0 || end.getRow() == 7) {
                // If the pawn has reached the opposite end of the board, promote it to a queen
                board.setPieceAt(end, new Queen(piece.getColor()));
            }
        }
        return board;
    }
}
