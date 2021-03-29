package FourInARow;

import org.junit.jupiter.api.BeforeEach;


public class TestGame {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new StandardGame();
    }
}
