package chess;

public class Move {
    private final Position startPosition;
    private final Position endPosition;
    private final Piece pieceMoved;

    // Constructor for a move
    public Move(Position startPosition, Position endPosition, Piece pieceMoved) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.pieceMoved = pieceMoved;
    }

    // Getters
    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }
    public static Move convertParsedMoveToMove(ParsedMove parsedMove, Board board) {
        // Convert the start and end files from PieceFile to their respective column indices
        int startColumn = parsedMove.startFile.ordinal(); // Returns an int representing position in the enum, starting at 0
        int endColumn = parsedMove.endFile.ordinal(); // Convert enum to its ordinal value
    
        // Create Position objects for the start and end positions
        // Ranks are 1-based and need to be converted to 0-based row index
        Position start = new Position(parsedMove.startRank - 1, startColumn);
        Position end = new Position(parsedMove.endRank - 1, endColumn);
    
        // Create a new Move object
        Move move = new Move(start, end, board.getPieceAt(start));
    
        return move;
    }    
}
