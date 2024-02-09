package chess;

public class Board {
    private Piece[][] board = new Piece[8][8]; // matrix of pieces

    public Piece getPieceAt(Position position) { // returns the piece at a given position
        return board[position.getRow()][position.getColumn()];
    }

    public void setPieceAt(Position position, Piece piece) { // for resets and promotions
        board[position.getRow()][position.getColumn()] = piece; 
    }

    public void removePieceAt(Position position) { // for captures
        board[position.getRow()][position.getColumn()] = null;
    }
    public void resetBoard(){ // for resets of game by draw or resign etc.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = null;
            }
        }
    }

    // method to initialize Board with pieces in starting position
    public void initializeBoard() {
        // Initialize the white rooks
        board[0][0] = new Rook(Piece.Color.WHITE);
        board[0][7] = new Rook(Piece.Color.WHITE);
        // Initialize the white knights
        board[0][1] = new Knight(Piece.Color.WHITE);
        board[0][6] = new Knight(Piece.Color.WHITE);
        // Initialize the white bishops
        board[0][2] = new Bishop(Piece.Color.WHITE);
        board[0][5] = new Bishop(Piece.Color.WHITE);
        // Initialize the white queen
        board[0][3] = new Queen(Piece.Color.WHITE);
        // Initialize the white king
        board[0][4] = new King(Piece.Color.WHITE);
        // Initialize the white pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Piece.Color.WHITE);
        }
        // Initialize the black rooks
        board[7][0] = new Rook(Piece.Color.BLACK);
        board[7][7] = new Rook(Piece.Color.BLACK);
        // Initialize the black knights
        board[7][1] = new Knight(Piece.Color.BLACK);
        board[7][6] = new Knight(Piece.Color.BLACK);
        // Initialize the black bishops
        board[7][2] = new Bishop(Piece.Color.BLACK);
        board[7][5] = new Bishop(Piece.Color.BLACK);
        // Initialize the black queen
        board[7][3] = new Queen(Piece.Color.BLACK);
        // Initialize the black king
        board[7][4] = new King(Piece.Color.BLACK);
        // Initialize the black pawns
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Piece.Color.BLACK);
        }
    }

    private boolean[][] hasMoved = new boolean[8][8]; // matrix of booleans to track if a piece has moved, need for castling

    // Getter for hasMoved
    public boolean getHasMoved(Position position) {
        return hasMoved[position.getRow()][position.getColumn()];
    }
    // Setter for hasMoved
    public void setHasMoved(Position position, boolean value) {
        hasMoved[position.getRow()][position.getColumn()] = value;
    }
    // Reset hasMoved status of each piece (known by initial square) to false
    public void resetHasMoved() {
        for (int i = 0; i < hasMoved.length; i++) {
            for (int j = 0; j < hasMoved[i].length; j++) {
                hasMoved[i][j] = false;
            }
        }
    }
    public Board cloneBoard() {
        Board clonedBoard = new Board(); // Create a new Board instance
    
        // Iterate over the current board and copy each piece to the new board
        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[row].length; col++) {
                // Get the current piece from the original board
                Piece currentPiece = this.board[row][col];
                
                // Clone the piece if it's not null
                if (currentPiece != null) {
                    clonedBoard.board[row][col] = currentPiece.clonePiece();
                }
            }
        }
        // Clone the hasMoved array
        for (int row = 0; row < this.hasMoved.length; row++) {
            System.arraycopy(this.hasMoved[row], 0, clonedBoard.hasMoved[row], 0, this.hasMoved[row].length);
        }
    
        return clonedBoard;
    }    
}