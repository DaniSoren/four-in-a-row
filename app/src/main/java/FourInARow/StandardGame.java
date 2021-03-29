package FourInARow;

public class StandardGame implements Game {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private Player playerInTurn;
    private PieceType[][] pieces;

    public StandardGame() {
        playerInTurn = Player.BLUE;
        pieces = new PieceType[ROWS][COLUMNS];
    }

    @Override
    public boolean putPieceAtColumn(int column) {
        PieceType pieceType = switch (getPlayerInTurn()) {
            case RED -> PieceType.RED;
            case BLUE -> PieceType.BLUE;
        };

        int currentRow = ROWS;

        for (int row = ROWS - 1; row >= 0; row--) {
            boolean positionIsEmpty = getPieceAt(row, column) == null;
            if (positionIsEmpty) {
                currentRow = row;
                break;
            }
        }

        if(currentRow == ROWS) return false;

        pieces[currentRow][column] = pieceType;

        endOfTurn();

        return true;
    }

    @Override
    public PieceType getPieceAt(int row, int column) {
        return pieces[row][column];
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
