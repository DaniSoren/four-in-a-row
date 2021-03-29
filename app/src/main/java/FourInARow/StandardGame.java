package FourInARow;

public class StandardGame implements Game {
    @Override
    public boolean putPieceAtColumn(int column) {
        return false;
    }

    @Override
    public PieceType getPieceAt(int row, int column) {
        return PieceType.BLUE;
    }

    @Override
    public void resetBoard() {

    }

    @Override
    public Player getWinner() {
        return null;
    }
}
