package chess;

public class ExecuteMoveResult {
    private ReturnPiece[][] updatedBoard;
    private boolean isCheck;
    private boolean isCheckmate;
    private Chess.Player nextPlayer;

    public ExecuteMoveResult(ReturnPiece[][] updatedBoard, boolean isCheck, boolean isCheckmate, Chess.Player currentPlayer) {
        this.updatedBoard = updatedBoard;
        this.isCheck = isCheck;
        this.isCheckmate = isCheckmate;
        // Determine the next player based on the current player
        if (currentPlayer == Chess.Player.white) {
            this.nextPlayer = Chess.Player.black;
        }
        else {
            this.nextPlayer = Chess.Player.white;
        }
    }

    // Getters
    public ReturnPiece[][] getUpdatedBoard() {
        return updatedBoard;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public boolean isCheckmate() {
        return isCheckmate;
    }

    public Chess.Player getNextPlayer() {
        return nextPlayer;
    }
}
