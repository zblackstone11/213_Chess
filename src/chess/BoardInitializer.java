package chess;

public class BoardInitializer {

    // Method to initialize the board with null values

	static void resetBoard(ReturnPiece[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = null;
			}
		}
	}

    // Method to initialize the board with pieces in their initial positions

    static void initializeBoard(ReturnPiece[][] board) {
        // Initialize the white rooks
		board[0][0] = CreatePiece.createPiece(ReturnPiece.PieceType.WR, ReturnPiece.PieceFile.a, 1);
		board[0][7] = CreatePiece.createPiece(ReturnPiece.PieceType.WR, ReturnPiece.PieceFile.h, 1);

		// Initialize the white knights
		board[0][1] = CreatePiece.createPiece(ReturnPiece.PieceType.WN, ReturnPiece.PieceFile.b, 1);
		board[0][6] = CreatePiece.createPiece(ReturnPiece.PieceType.WN, ReturnPiece.PieceFile.g, 1);

		// Initialize the white bishops
		board[0][2] = CreatePiece.createPiece(ReturnPiece.PieceType.WB, ReturnPiece.PieceFile.c, 1);
		board[0][5] = CreatePiece.createPiece(ReturnPiece.PieceType.WB, ReturnPiece.PieceFile.f, 1);

		// Initialize the white queen
		board[0][3] = CreatePiece.createPiece(ReturnPiece.PieceType.WQ, ReturnPiece.PieceFile.d, 1);

		// Initialize the white king
		board[0][4] = CreatePiece.createPiece(ReturnPiece.PieceType.WK, ReturnPiece.PieceFile.e, 1);

		// Initialize the white pawns using the enum values() method, which returns an array of the enum values
		// This way, we can iterate through the array and initialize the pawns in a loop
		for (int i = 0; i < 8; i++) {
			board[1][i] = CreatePiece.createPiece(ReturnPiece.PieceType.WP, ReturnPiece.PieceFile.values()[i], 2);
		}

		// Initialize the black rooks
		board[7][0] = CreatePiece.createPiece(ReturnPiece.PieceType.BR, ReturnPiece.PieceFile.a, 8);
		board[7][7] = CreatePiece.createPiece(ReturnPiece.PieceType.BR, ReturnPiece.PieceFile.h, 8);

		// Initialize the black knights
		board[7][1] = CreatePiece.createPiece(ReturnPiece.PieceType.BN, ReturnPiece.PieceFile.b, 8);
		board[7][6] = CreatePiece.createPiece(ReturnPiece.PieceType.BN, ReturnPiece.PieceFile.g, 8);

		// Initialize the black bishops
		board[7][2] = CreatePiece.createPiece(ReturnPiece.PieceType.BB, ReturnPiece.PieceFile.c, 8);
		board[7][5] = CreatePiece.createPiece(ReturnPiece.PieceType.BB, ReturnPiece.PieceFile.f, 8);

		// Initialize the black queen
		board[7][3] = CreatePiece.createPiece(ReturnPiece.PieceType.BQ, ReturnPiece.PieceFile.d, 8);

		// Initialize the black king
		board[7][4] = CreatePiece.createPiece(ReturnPiece.PieceType.BK, ReturnPiece.PieceFile.e, 8);

		// Initialize the black pawns
		for (int i = 0; i < 8; i++) {
			board[6][i] = CreatePiece.createPiece(ReturnPiece.PieceType.BP, ReturnPiece.PieceFile.values()[i], 7);
        }
    }
}