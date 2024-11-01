package chess;

public class Position {
    private final int row;  // Corresponds to chess board ranks (1-8), values will be 0-based
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

    // Method for equality check
    public boolean equals(Position other) {
        return this.row == other.row && this.column == other.column;
    }
}
