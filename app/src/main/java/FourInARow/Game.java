package FourInARow;

public interface Game {
    boolean putPieceAtColumn(int column);
    PieceType getPieceAt(int row, int column);
    void resetBoard();
    Player getWinner();
}
