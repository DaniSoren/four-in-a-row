package FourInARow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
        game.putPieceAtColumn(3);
        PieceType pieceAtColumn3 = game.getPieceAt(5, 3);
        PieceType blue = PieceType.BLUE;
        assertThat(pieceAtColumn3, is(notNullValue()));
        assertThat(pieceAtColumn3, is(blue));
    }

    @Test
    void shouldPutRedPieceAtColumn3AfterBluePieceAtColumn3() {
        shouldPutBluePieceAtColumn3();
        game.putPieceAtColumn(3);

        PieceType pieceAtColumn3 = game.getPieceAt(4, 3);
        PieceType red = PieceType.RED;
        assertThat(pieceAtColumn3, is(notNullValue()));
        assertThat(pieceAtColumn3, is(red));
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
}
