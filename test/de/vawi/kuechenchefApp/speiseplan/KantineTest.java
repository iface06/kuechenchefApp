
package de.vawi.kuechenchefApp.speiseplan;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tatsch
 */
public class KantineTest {
    
    @Test
    public void testBeliebtesteEssenMuehlheim() {
        int anzahl = Kantine.MUELHEIM_AN_DER_RUHR.berechneAnzahlFuerBeliebtestesGericht();
        assertEquals(225, anzahl);
    }
    
    @Test
    public void testBeliebtesteEssenEssen() {
        int anzahl = Kantine.ESSEN.berechneAnzahlFuerBeliebtestesGericht();
        assertEquals(375, anzahl);
    }
    
    @Test
    public void testZweitBeliebtesteEssenMuehlheim() {
        int anzahl = Kantine.MUELHEIM_AN_DER_RUHR.berechneAnzahlFuerZweitBeliebtestesGericht();
        assertEquals(113, anzahl);
    }
    
    @Test
    public void testZweitBeliebtesteEssenEssen() {
        int anzahl = Kantine.ESSEN.berechneAnzahlFuerZweitBeliebtestesGericht();
        assertEquals(188, anzahl);
    }
    
    @Test
    public void testDritttBeliebtesteEssenMuehlheim() {
        int anzahl = Kantine.MUELHEIM_AN_DER_RUHR.berechneAnzahlFuerDrittBeliebtestesGericht();
        assertEquals(113, anzahl);
    }
    
    @Test
    public void testDrittBeliebtesteEssenEssen() {
        int anzahl = Kantine.ESSEN.berechneAnzahlFuerDrittBeliebtestesGericht();
        assertEquals(188, anzahl);
    }
}
