package chess;

enum MoveType { RESIGN, DRAW, REGULAR, PAWN_PROMOTION } // Only four move types that can be identified EXPLICITLY by the text input in this class
class ParsedMove { 
    MoveType moveType;
    ReturnPiece.PieceFile startFile;
    int startRank;
    ReturnPiece.PieceFile endFile;
    int endRank;
    char promotionPieceType; // Only used for pawn promotion

    // Constructor to initialize fields, with default values for non-applicable fields
    public ParsedMove(MoveType moveType) {
        this.moveType = moveType;
        this.promotionPieceType = 'x'; // Default, will be set explicitly for pawn promotions
    }

    // Helper method to set start and end squares
    public void setSquares(String start, String end) {
        this.startFile = ReturnPiece.PieceFile.valueOf(start.substring(0, 1)); // can add .toLowerCase if we are supposed to accept uppercase input
        this.startRank = Integer.parseInt(start.substring(1));
        this.endFile = ReturnPiece.PieceFile.valueOf(end.substring(0, 1));
        this.endRank = Integer.parseInt(end.substring(1));
    }

    // Helper method to set promotion piece type
    public void setPromotionPieceType(char promotionChar) {
        switch (promotionChar) {
            case 'Q': // assumption is that we should only be accepting uppercase input
                this.promotionPieceType = 'Q'; 
                break;
            case 'R':
                this.promotionPieceType = 'R';
                break;
            case 'B':
                this.promotionPieceType = 'B';
                break;
            case 'N':
                this.promotionPieceType = 'N';
                break;
            default:
                throw new IllegalArgumentException("Invalid promotion piece type");
        }
    }
}

public class MoveParser {

    public static ParsedMove parseMove(String move) {
        String trimmedMove = move.trim(); // Remove leading/trailing whitespace

        if ("resign".equalsIgnoreCase(trimmedMove)) {
            return new ParsedMove(MoveType.RESIGN);
        }
        else if (trimmedMove.endsWith("draw?")) {
            String baseMove = trimmedMove.substring(0, trimmedMove.length() - 5); // Remove " draw?"
            String[] squares = baseMove.split(" ");
            ParsedMove parsedMove = new ParsedMove(MoveType.DRAW);
            parsedMove.setSquares(squares[0], squares[1]);
            return parsedMove;
        }
        else if (trimmedMove.matches(".* [QRBN]$")) { // Corrected regex for pawn promotion, $ to match end of string
            String[] parts = trimmedMove.split(" ");
            char promotionPiece = parts[2].charAt(0); // Corrected getting the promotion piece character
            ParsedMove parsedMove = new ParsedMove(MoveType.PAWN_PROMOTION);
            parsedMove.setSquares(parts[0], parts[1]);
            parsedMove.setPromotionPieceType(promotionPiece);
            return parsedMove;
        }        
        else { // Handle regular move
            String[] squares = trimmedMove.split(" ");
            ParsedMove parsedMove = new ParsedMove(MoveType.REGULAR);
            parsedMove.setSquares(squares[0], squares[1]);
            return parsedMove;
        }
    }
}