package FourInARow;

import static FourInARow.GameConstants.*;

public class StandardGame implements Game {
    private Player playerInTurn;
    private PieceType[][] pieces;
    private int recentRow;
    private int recentColumn;

    public StandardGame() {
        playerInTurn = Player.BLUE;
        pieces = new PieceType[ROWS][COLUMNS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                pieces[i][j] = PieceType.NONE;
            }
        }
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

        placeUnitWithTypeAtRowAndColumn(pieceType, availableRow, column);

        return true;
    }

    private void placeUnitWithTypeAtRowAndColumn(PieceType type, int row, int column) {
        pieces[row][column] = type;
    }

    private int calculateNextAvailableRowAtColumn(int column) {
        int availableRow = ROWS;

        for (int row = ROWS - 1; row >= 0; row--) {
            boolean positionIsEmpty = getPieceAt(row, column) == PieceType.NONE;
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
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                pieces[i][j] = null;
            }
        }
    }

    @Override
    public Player getWinner() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Player candidate = switch (getPieceAt(i, j)) {
                    case RED -> Player.RED;
                    case BLUE -> Player.BLUE;
                    default -> null;
                };

                if(i < ROWS - 3) {
                    for (int k = 0; k < 4; k++) {
                        Player ownerOfPiece = switch (getPieceAt(i + k, j)) {
                            case RED -> Player.RED;
                            case BLUE -> Player.BLUE;
                            default -> null;
                        };

                        if (ownerOfPiece != candidate) candidate = null;
                    }
                } else continue;

                if(candidate != null) return candidate;

            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                Player candidate = switch (getPieceAt(i, j)) {
                    case RED -> Player.RED;
                    case BLUE -> Player.BLUE;
                    default -> null;
                };

                for (int k = 0; k < 4; k++) {
                    Player ownerOfPiece = switch (getPieceAt(i, j + k)) {
                        case RED -> Player.RED;
                        case BLUE -> Player.BLUE;
                        default -> null;
                    };

                    if(ownerOfPiece != candidate) candidate = null;
                }

                if(candidate != null) return candidate;

            }
        }
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
        pieces[recentRow][recentColumn] = PieceType.NONE;
    }
}
