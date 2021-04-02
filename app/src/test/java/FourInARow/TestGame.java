package FourInARow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static FourInARow.GameConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class TestGame {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new StandardGame();
    }

    @Test
    void shouldPutBluePieceAtColumn3() {
        boolean pieceIsPut = game.putPieceAtColumn(3);

        assertThat(pieceIsPut, is(true));

        PieceType pieceAtColumn3 = game.getPieceAt(5, 3);
        assertThat(pieceAtColumn3, is(notNullValue()));
        assertThat(pieceAtColumn3, is(PieceType.BLUE));
    }

    @Test
    void shouldPutRedPieceAtColumn3AfterBluePieceAtColumn3() {
        shouldPutBluePieceAtColumn3();
        game.endOfTurn();

        boolean pieceIsPut = game.putPieceAtColumn(3);

        assertThat(pieceIsPut, is(true));

        PieceType pieceAtColumn3 = game.getPieceAt(4, 3);
        assertThat(pieceAtColumn3, is(notNullValue()));
        assertThat(pieceAtColumn3, is(PieceType.RED));
    }

    @Test
    void shouldNotPlaceMoreThan6UnitsOnTopOfEachOther() {
        for (int i = 0; i < 6; i++) {
            boolean pieceIsPut = game.putPieceAtColumn(0);
            assertThat(pieceIsPut, is(true));

            game.endOfTurn();
        }

        boolean pieceIsPut = game.putPieceAtColumn(0);
        assertThat(pieceIsPut, is(false));
    }

    @Test
    void shouldBeBluePlayerFirst() {
        Player currentPlayer = game.getPlayerInTurn();
        assertThat(currentPlayer, is(Player.BLUE));
    }

    @Test
    void shouldBeRedPlayerAfterBluePlayer() {
        shouldBeBluePlayerFirst();
        game.endOfTurn();

        Player currentPlayer = game.getPlayerInTurn();
        assertThat(currentPlayer, is(Player.RED));
    }

    @Test
    void shouldBluePlayerUndoMoveAndStillBeBluePlayersTurn() {
        boolean pieceIsPut = game.putPieceAtColumn(4);
        PieceType piece = game.getPieceAt(5, 4);

        assertThat(pieceIsPut, is(true));
        assertThat(piece, is(notNullValue()));

        game.undoMove();

        Player currentPlayer = game.getPlayerInTurn();
        boolean pieceIsRemoved = game.getPieceAt(0, 4) == PieceType.NONE;

        assertThat(currentPlayer, is(Player.BLUE));
        assertThat(pieceIsRemoved, is(true));
    }

    @Test
    void shouldBoardBeResetAfterBeingInFixedPositions() {
        int[] columns = {0, 4, 3, 0, 3, 0, 4, 1, 1, 4, 1, 4};
        for (Integer column :
                columns) {
            boolean pieceIsPut = game.putPieceAtColumn(column);
            assertThat(pieceIsPut, is(true));

            game.endOfTurn();
        }

        int numberOfPiecesOnBoard = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if(game.getPieceAt(i, j) != PieceType.NONE) numberOfPiecesOnBoard++;
            }
        }

        assertThat(numberOfPiecesOnBoard, is(columns.length));

        game.resetBoard();

        int numberOfPiecesOnBoardAfterReset = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if(game.getPieceAt(i, j) != null) numberOfPiecesOnBoardAfterReset++;
            }
        }

        assertThat(numberOfPiecesOnBoardAfterReset, is(0));
    }

    @Test
    void shouldHaveVerticalWinner() {
        int[] columns = {1, 2, 3, 2, 4, 2, 4, 2};
        for (Integer column :
                columns) {
            game.putPieceAtColumn(column);
            game.endOfTurn();
        }

        assertThat(game.getPieceAt(ROWS-1, 2), is(PieceType.RED));
        assertThat(game.getPieceAt(ROWS-2, 2), is(PieceType.RED));
        assertThat(game.getPieceAt(ROWS-3, 2), is(PieceType.RED));
        assertThat(game.getPieceAt(ROWS-4, 2), is(PieceType.RED));

        Player winner = game.getWinner();
        assertThat(winner, is(Player.RED));
    }

    @Test
    void shouldNotHaveWinnerWhenWinConditionsNotMet() {
        Integer[] columns = {4, 4, 5, 5, 5, 5};
        for (Integer column :
                columns) {
            game.putPieceAtColumn(column);
            game.endOfTurn();
        }

        Player winner = game.getWinner();
        assertThat(winner, is(nullValue()));
    }
}
