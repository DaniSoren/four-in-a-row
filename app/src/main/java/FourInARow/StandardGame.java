package FourInARow;

public class StandardGame implements Game {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private Player playerInTurn;
    private PieceType[][] pieces;
    private int recentRow;
    private int recentColumn;

    public StandardGame() {
        playerInTurn = Player.BLUE;
        pieces = new PieceType[ROWS][COLUMNS];
    }

    @Override
    public boolean putPieceAtColumn(int column) {
        int availableRow = calculateNextAvailableRowAtColumn(column);

        boolean noAvailableRowForUnit = availableRow == ROWS;
        if(noAvailableRowForUnit) return false;

        PieceType pieceType = switch (getPlayerInTurn()) {
            case RED -> PieceType.RED;
            case BLUE -> PieceType.BLUE;
        };

        recentRow = availableRow;
        recentColumn = column;
        pieces[recentRow][recentColumn] = pieceType;

        return true;
    }

    private int calculateNextAvailableRowAtColumn(int column) {
        int availableRow = ROWS;

        for (int row = ROWS - 1; row >= 0; row--) {
            boolean positionIsEmpty = getPieceAt(row, column) == null;
            if (positionIsEmpty) {
                availableRow = row;
                break;
            }
        }
        return availableRow;
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
        pieces[recentRow][recentColumn] = null;
    }
}
