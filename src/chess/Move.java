package chess;

public class Move {
    private final Position startPosition;
    private final Position endPosition;
    private final Piece pieceMoved;

    // Additional fields for rook's move in case of castling
    private Position rookStartPosition;
    private Position rookEndPosition;

    // Constructor for a regular move
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

    // Constructor for a castling move
    public Move(Position startPosition, Position endPosition, Piece pieceMoved, Position rookStartPosition, Position rookEndPosition) {
        this(startPosition, endPosition, pieceMoved);
        this.rookStartPosition = rookStartPosition;
        this.rookEndPosition = rookEndPosition;
    }

    // Getters for rook's move
    public Position getRookStartPosition() {
        return rookStartPosition;
    }

    public Position getRookEndPosition() {
        return rookEndPosition;
    }

    public static Move convertParsedMoveToMove(ParsedMove parsedMove, Board board) {
        // Convert the start and end files from PieceFile to their respective column indices
        int startColumn = parsedMove.startFile.ordinal(); // Returns an int representing position in the enum, starting at 0
        int endColumn = parsedMove.endFile.ordinal(); // Convert enum to its ordinal value
    
        // Create Position objects for the start and end positions
        // Ranks are 1-based and need to be converted to 0-based row index
        Position start = new Position(parsedMove.startRank - 1, startColumn);
        Position end = new Position(parsedMove.endRank - 1, endColumn);

        // if the piece at the start position is the king, and the end position is two squares to the right or left, then it's a castling move

        // Check if the piece at the start position is a king
        if (board.getPieceAt(start).getType() == Piece.PieceType.KING) {
            // Check if the end position is two squares to the right or left of the start position
            if (end.getColumn() - start.getColumn() == 2) {
                // If the end position is two squares to the right, then the rook's move is to the right of the king
                Position rookStart = new Position(start.getRow(), 7);
                Position rookEnd = new Position(start.getRow(), 5);
                // Create a new Move object for the castling move
                Move move = new Move(start, end, board.getPieceAt(start), rookStart, rookEnd);
                return move;
            } else if (end.getColumn() - start.getColumn() == -2) {
                // If the end position is two squares to the left, then the rook's move is to the left of the king
                Position rookStart = new Position(start.getRow(), 0);
                Position rookEnd = new Position(start.getRow(), 3);
                // Create a new Move object for the castling move
                Move move = new Move(start, end, board.getPieceAt(start), rookStart, rookEnd);
                return move;
            }
        }
    
        // Create a new Move object
        Move move = new Move(start, end, board.getPieceAt(start));
    
        return move;
    }    

    // Override the equals method to compare two Move objects, used in the another method
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
    
        Move otherMove = (Move) obj;
    
        // Directly compare the row and column of startPosition and endPosition
        boolean positionsEqual = this.startPosition.getRow() == otherMove.startPosition.getRow() &&
                                 this.startPosition.getColumn() == otherMove.startPosition.getColumn() &&
                                 this.endPosition.getRow() == otherMove.endPosition.getRow() &&
                                 this.endPosition.getColumn() == otherMove.endPosition.getColumn();
    
        boolean piecesEqual = this.pieceMoved != null && otherMove.pieceMoved != null &&
                              this.pieceMoved.getType() == otherMove.pieceMoved.getType() &&
                              this.pieceMoved.getColor() == otherMove.pieceMoved.getColor();
    
        return positionsEqual && piecesEqual;
    }
}
