package FourInARow;

public class StandardGame implements Game {
    private Player playerInTurn;

    public StandardGame() {
        playerInTurn = Player.BLUE;
    }

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

    @Override
    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    @Override
    public void endOfTurn() {
        if(playerInTurn == Player.BLUE) playerInTurn = Player.RED;
        else playerInTurn = Player.BLUE;
    }

    @Override
    public void undoMove() {

    }
}
