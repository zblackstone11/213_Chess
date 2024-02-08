package chess;

public class Position {
    private final int row;  // Corresponds to chess board ranks (1-8)
    private final int column; // Corresponds to chess board files (a-h), easier to work with than letters

    // Just keep in mind that it is the reverse of chess notation, where the column comes first and the row comes second

    // Constructor to initialize the Position object with a row and column
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    // Getter for row
    public int getRow() {
        return row;
    }

    // Getter for column
    public int getColumn() {
        return column;
    }
}
