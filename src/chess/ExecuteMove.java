package chess;
// NEEDS WORK
public class ExecuteMove {

    // Thinking this is redundant now but not method below
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
        return board;
    }
}
