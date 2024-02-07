package chess;

public class PieceMovementTracker {
    // Track if each rook has moved (false by default)
    private boolean whiteKingSideRookMoved = false;
    private boolean whiteQueenSideRookMoved = false;
    private boolean blackKingSideRookMoved = false;
    private boolean blackQueenSideRookMoved = false;

    // Track if kings have moved (false by default)
    private boolean whiteKingMoved = false;
    private boolean blackKingMoved = false;

    // Methods to update the movement status
    public void setWhiteKingSideRookMoved() {
        whiteKingSideRookMoved = true;
    }

    public void setWhiteQueenSideRookMoved() {
        whiteQueenSideRookMoved = true;
    }

    public void setBlackKingSideRookMoved() {
        blackKingSideRookMoved = true;
    }

    public void setBlackQueenSideRookMoved() {
        blackQueenSideRookMoved = true;
    }

    public void setWhiteKingMoved() {
        whiteKingMoved = true;
    }

    public void setBlackKingMoved() {
        blackKingMoved = true;
    }

    // Methods to check if pieces have moved
    public boolean hasWhiteKingSideRookMoved() {
        return whiteKingSideRookMoved;
    }

    public boolean hasWhiteQueenSideRookMoved() {
        return whiteQueenSideRookMoved;
    }

    public boolean hasBlackKingSideRookMoved() {
        return blackKingSideRookMoved;
    }

    public boolean hasBlackQueenSideRookMoved() {
        return blackQueenSideRookMoved;
    }

    public boolean hasWhiteKingMoved() {
        return whiteKingMoved;
    }

    public boolean hasBlackKingMoved() {
        return blackKingMoved;
    }

    // Method to reset all movement flags (for game restarts)
    public void reset() {
        whiteKingSideRookMoved = false;
        whiteQueenSideRookMoved = false;
        blackKingSideRookMoved = false;
        blackQueenSideRookMoved = false;
        whiteKingMoved = false;
        blackKingMoved = false;
    }
}