package chess;

public class SelfCheckSimulator {
    // Class to clone the board, make a move on the cloned board from Piece.getLegalMoves, and return the result of the move
    // Useful to simulate for self check
    public static boolean simulateMove(Board board, Move move) {
        // Clone the board
        Board clonedBoard = board.cloneBoard();
        // Make the move on the cloned board
        clonedBoard = ExecuteMove.executeMove(move, clonedBoard);
        // Run is check on the cloned board to see if the move is illegal (results in self-check), or if still in check after the move
        return !IsCheck.isCheck(clonedBoard, move.getPieceMoved().getColor());
    }
}
