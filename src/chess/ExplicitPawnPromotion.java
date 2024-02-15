package chess;

public class ExplicitPawnPromotion {
    // TAKES PRIOR MOVE AND PROMOTION PIECE AND RETURNS BOARD WITH EXPLICIT PAWN PROMOTION
    // pass the method the prior move and the parsed move which contains the promotion piece and the board
    public static Board promotePawn(Move priorMove, ParsedMove parsedMove, Board board) {
        // Get the start and end positions of the prior move
        Position start = priorMove.getStartPosition();
        Position end = priorMove.getEndPosition();
        
        // Get the piece at the end position
        Piece piece = board.getPieceAt(end);
        
        // Check if the piece is a pawn
        if (piece instanceof Pawn) {
            // Check if the pawn has reached the opposite end of the board
            if ((piece.getColor() == Piece.Color.WHITE && end.getRow() == 7) || (piece.getColor() == Piece.Color.BLACK && end.getRow() == 0)) {
                // Promote the pawn to the specified piece in the parsed move
                Piece promotedPiece;
                switch (parsedMove.promotionPieceType) {
                    case 'Q':
                        promotedPiece = new Queen(piece.getColor());
                        break;
                    case 'R':
                        promotedPiece = new Rook(piece.getColor());
                        break;
                    case 'B':
                        promotedPiece = new Bishop(piece.getColor());
                        break;
                    case 'N':
                        promotedPiece = new Knight(piece.getColor());
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid promotion piece type");
                }
                
                // Set the promoted piece on the board
                board.setPieceAt(end, promotedPiece);
            }
        } 
        return board;
    }
}