package be.domino;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.awt.*;

/**
 * @author Maarten Gielkens
 * Ik heb ongeveer 12 uur gewerkt aan deze taak.
 */

public class AlgoritmeTest {
    @Test
    public void ControleerAlsTweeStenenMetDezelfdeKleurGeplaatstKunnenWorden_IsFalse() {
        Steen steen1 = new Steen(2, 4, Color.RED);
        Steen steen2 = new Steen(4, 2, Color.RED);
        Algoritme algoritme = new Algoritme();

        boolean actual = algoritme.controleerSteen(steen1, steen2);

        assertThat(actual, is(false));
    }

    @Test
    public void ControleerAlsTweeStenenMetNietPassendeNummersGeplaatstKunnenWorden_IsFalse() {
        Steen steen1 = new Steen(2, 6, Color.RED);
        Steen steen2 = new Steen(3, 5, Color.BLUE);
        Algoritme algoritme = new Algoritme();

        boolean actual = algoritme.controleerSteen(steen1, steen2);

        assertThat(actual, is(false));
    }

    @Test
    public void ControleerAlsTweeStenenMetEenPassendeNummersGeplaatstKunnenWorden_dezelfdeKleur_IsFalse() {
        Steen steen1 = new Steen(2, 6, Color.RED);
        Steen steen2 = new Steen(6, 5, Color.RED);
        Algoritme algoritme = new Algoritme();

        boolean actual = algoritme.controleerSteen(steen1, steen2);

        assertThat(actual, is(false));
    }

    @Test
    public void ControleerAlsTweeStenenMetEenPassendeNummersGeplaatstKunnenWorden_verschillendeKleur_IsFalse() {
        Steen steen1 = new Steen(2, 6, Color.RED);
        Steen steen2 = new Steen(6, 5, Color.GREEN);
        Algoritme algoritme = new Algoritme();

        boolean actual = algoritme.controleerSteen(steen1, steen2);

        assertThat(actual, is(true));
    }

    @Test
    public void ControleerAlsTweeStenenMetVerschillendeKleurGeplaatstKunnenWorden_IsTrue() {
        Steen steen1 = new Steen(2, 4, Color.RED);
        Steen steen2 = new Steen(4, 2, Color.GREEN);
        Algoritme algoritme = new Algoritme();

        boolean actual = algoritme.controleerSteen(steen1, steen2);

        assertThat(actual, is(true));
    }

    @Test
    public void ControleerAlsDeSteenFliptOmDeJuisteUitkomstTeBekomen_IsTrue() {
        Steen steen1 = new Steen(2, 4, Color.RED);
        Steen steen2 = new Steen(2, 4, Color.GREEN);
        Algoritme algoritme = new Algoritme();
        algoritme.controleerSteen(steen1, steen2);

        boolean actual = steen2.isFlipped();

        assertThat(actual, is(true));
    }
}
