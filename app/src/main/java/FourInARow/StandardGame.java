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
        if(getPieceAt(ROWS-1, column) == null) {
            currentRow = ROWS-1;
        } else if(getPieceAt(ROWS-2, column) == null) {
            currentRow = ROWS-2;
        }
        pieces[currentRow][column] = pieceType;
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
