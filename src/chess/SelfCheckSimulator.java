package chess;

public class SelfCheckSimulator {
    // Class to clone the board, make a move on the cloned board from Piece.getLegalMoves, and return the result of the move
    // Useful to simulate for self check, and whether a move gets one out of check with a non-king move
    public static boolean simulateMove(Board board, Move move) {
        // Clone the board
        Board clonedBoard = board.cloneBoard();
        // Make the move on the cloned board before the move is made on the actual board
        clonedBoard = ExecuteMove.executeMove(move, clonedBoard);
        // Run isCheck on the cloned board to see if the move is illegal (1.results in self-check), or 2. if still in check after the move
        // If the move is illegal, 
        return IsCheck.isCheck(clonedBoard, move.getPieceMoved().getColor()); // isCheck asks "am I in check?" If true, move is illegal, if FALSE we pass the  move simulation
    }
}
