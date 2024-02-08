package chess;

public class Move {
    private final Position startPosition;
    private final Position endPosition;
    private final Piece pieceMoved;
    private Piece pieceCaptured; // This can be null if no capture

    // Constructor for a move without a capture
    public Move(Position startPosition, Position endPosition, Piece pieceMoved) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.pieceMoved = pieceMoved;
        this.pieceCaptured = null; // No piece captured in this move
    }

    // Constructor for a move with a capture
    public Move(Position startPosition, Position endPosition, Piece pieceMoved, Piece pieceCaptured) {
        this(startPosition, endPosition, pieceMoved);
        this.pieceCaptured = pieceCaptured;
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

    public Piece getPieceCaptured() {
        return pieceCaptured;
    }
}
