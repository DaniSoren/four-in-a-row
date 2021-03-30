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
        PieceType blue = PieceType.BLUE;
        assertThat(pieceAtColumn3, is(notNullValue()));
        assertThat(pieceAtColumn3, is(blue));
    }

    @Test
    void shouldPutRedPieceAtColumn3AfterBluePieceAtColumn3() {
        shouldPutBluePieceAtColumn3();
        game.endOfTurn();
        boolean pieceIsPut = game.putPieceAtColumn(3);

        assertThat(pieceIsPut, is(true));

        PieceType pieceAtColumn3 = game.getPieceAt(4, 3);
        PieceType red = PieceType.RED;
        assertThat(pieceAtColumn3, is(notNullValue()));
        assertThat(pieceAtColumn3, is(red));
    }

    @Test
    void shouldNotPlaceMoreThan6UnitsOnTopOfEachOther() {
        for (int i = 0; i < 6; i++) {
            game.putPieceAtColumn(0);
            game.endOfTurn();
        }
        boolean pieceIsPut = game.putPieceAtColumn(0);
        assertThat(pieceIsPut, is(false));
    }

    @Test
    void shouldBeBluePlayerFirst() {
        Player currentPlayer = game.getPlayerInTurn();
        Player blue = Player.BLUE;
        assertThat(currentPlayer, is(blue));
    }

    @Test
    void shouldBeRedPlayerAfterBluePlayer() {
        shouldBeBluePlayerFirst();
        game.endOfTurn();
        Player currentPlayer = game.getPlayerInTurn();
        Player red = Player.RED;
        assertThat(currentPlayer, is(red));
    }

    @Test
    void shouldBluePlayerUndoMoveAndStillBeBluePlayersTurn() {
        boolean pieceIsPut = game.putPieceAtColumn(4);
        PieceType piece = game.getPieceAt(5, 4);

        assertThat(pieceIsPut, is(true));
        assertThat(piece, is(notNullValue()));

        game.undoMove();

        Player currentPlayer = game.getPlayerInTurn();
        Player blue = Player.BLUE;
        boolean pieceIsRemoved = game.getPieceAt(0, 4) == null;

        assertThat(currentPlayer, is(blue));
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
                if(game.getPieceAt(i, j) != null) numberOfPiecesOnBoard++;
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
}
