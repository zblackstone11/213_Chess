package chess;

public class Move {
    private final Position startPosition;
    private final Position endPosition;
    private final Piece pieceMoved;

    // Additional fields for rook's move in case of castling
    private Position rookStartPosition;
    private Position rookEndPosition;

    // Additional fields for en passant
    private boolean isEnPassant = false;
    private Position capturedPawnPosition;


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
        this(startPosition, endPosition, pieceMoved); // Call the regular move constructor
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

    // Constructor for en passant move
    public Move(Position startPosition, Position endPosition, Piece pieceMoved, boolean isEnPassant, Position capturedPawnPosition) {
        this(startPosition, endPosition, pieceMoved);
        this.isEnPassant = isEnPassant; 
        this.capturedPawnPosition = capturedPawnPosition;
    }

    // Getter for en passant flag
    public boolean isEnPassant() {
        return isEnPassant;
    }

    // Getter for captured pawn position in en passant
    public Position getCapturedPawnPosition() {
        return capturedPawnPosition;
    }

    public static Move convertParsedMoveToMove(ParsedMove parsedMove, Board board) {
        // Convert the start and end files from PieceFile to their respective column indices
        int startColumn = parsedMove.startFile.ordinal(); // Returns an int representing position in the enum, starting at 0
        int endColumn = parsedMove.endFile.ordinal(); // Convert enum to its ordinal value
    
        // Create Position objects for the start and end positions
        // Ranks are 1-based and need to be converted to 0-based row index
        Position start = new Position(parsedMove.startRank - 1, startColumn);
        Position end = new Position(parsedMove.endRank - 1, endColumn);

        // Get the piece at the start position, checking if it's null
        Piece pieceAtStart = board.getPieceAt(start);
        if (pieceAtStart == null) {
            // If there's no piece at the start, return a Move with null piece which is handled in the next method chess
            return new Move(start, end, null);
        }

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

        // For an en passant move
        else if (board.getPieceAt(start).getType() == Piece.PieceType.PAWN && board.getPieceAt(end) == null) {
            Move priorMove = Chess.getPriorMove();
            if (priorMove != null && priorMove.getPieceMoved().getType() == Piece.PieceType.PAWN) {
                int startRow = priorMove.getStartPosition().getRow(); // Get the row of the prior move's start position
                int endRow = priorMove.getEndPosition().getRow(); // Get the row of the prior move's end position
                int pawnRow = start.getRow(); // Get the row of the current pawn's position
                int pawnColumn = start.getColumn(); // Get the column of the current pawn's position
                int priorMoveEndColumn = priorMove.getEndPosition().getColumn(); // Get the column of the prior move's end position
        
                // Check if the pawn moved two squares
                boolean pawnMovedTwoSquares = Math.abs(startRow - endRow) == 2;
        
                // Check if the pawn is on the correct rank
                boolean isPawnOnFifthRank = (board.getPieceAt(start).getColor() == Piece.Color.WHITE && pawnRow == 4) || (board.getPieceAt(start).getColor() == Piece.Color.BLACK && pawnRow == 3);
        
                // Check if the prior move's end position is adjacent to the current pawn's position
                boolean isAdjacentColumn = Math.abs(pawnColumn - priorMoveEndColumn) == 1;

                // Check if the intended end position is on the same column as the last move's end column
                boolean isCorrectEndColumn = end.getColumn() == priorMoveEndColumn;
        
                if (pawnMovedTwoSquares && isPawnOnFifthRank && isAdjacentColumn && isCorrectEndColumn) {
                    // Calculate the captured pawn position
                    Position capturedPawnPosition = priorMove.getEndPosition();
                    // Create an en passant move
                    return new Move(start, end, board.getPieceAt(start), true, capturedPawnPosition);
                }
            }
        }        
    
        // Create a new Move object for normal moves
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
