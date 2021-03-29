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
        switch (playerInTurn) {
            case BLUE -> playerInTurn = Player.RED;
            case RED -> playerInTurn = Player.BLUE;
            default -> playerInTurn = null;
        }
    }

    @Override
    public void undoMove() {

    }
}
