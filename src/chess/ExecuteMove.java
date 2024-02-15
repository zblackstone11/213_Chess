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

        // Check if this is a castling move by checking if the move has rook start and end positions
        if (move.getRookStartPosition() != null && move.getRookEndPosition() != null) {
            // Execute the castling move for the king
            board.setPieceAt(move.getEndPosition(), move.getPieceMoved());
            board.setHasMoved(move.getEndPosition(), true);

            // Move the rook as part of the castling move
            Piece rook = board.getPieceAt(move.getRookStartPosition());
            board.removePieceAt(move.getRookStartPosition());
            board.setPieceAt(move.getRookEndPosition(), rook);
            board.setHasMoved(move.getRookEndPosition(), true);
        } else {
            // For regular moves, remove the piece from the end position if there is one
            board.removePieceAt(move.getEndPosition());
            // Then set the piece at the end position to the piece that was moved
            board.setPieceAt(move.getEndPosition(), move.getPieceMoved());
            // Update the hasMoved matrix for the piece that was moved
            board.setHasMoved(move.getEndPosition(), true);
        }

        // Update the hasMoved status for the piece that was moved
        board.setHasMoved(move.getStartPosition(), true);

        return board;
    }
}
