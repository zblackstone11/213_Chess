package chess;

public class ExecuteMove {

    public static Move ConvertMove(ParsedMove parsedMove, Board board) {
        // Assuming Move is legal, convert the parsed move to a Move object
        Move move = Move.convertParsedMoveToMove(parsedMove, board);
        return move;
    }

    public static Board executeMove(Move move, Board board) {
        // Execute the move by first removing the piece from the start position
        board.removePieceAt(move.getStartPosition());
        // Then remove the piece from the end position if there is one
        board.removePieceAt(move.getEndPosition());
        // Then set the piece at the end position to the piece that was moved
        board.setPieceAt(move.getEndPosition(), move.getPieceMoved());
        // Need Special logic for pawn promotion, castling, check, checkmate, and en passant

        // Update the hasMoved matrix for the piece that was moved
        // Check if the starting position of the move is false in the matrix, and update to true if it is
        if (!board.getHasMoved(move.getStartPosition())) {
            board.setHasMoved(move.getStartPosition(), true);
        }
        return board;
    }
}
