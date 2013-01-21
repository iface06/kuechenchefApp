
package de.vawi.kuechenchefApp.lieferanten;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias
 */
public class PreisListenPositionTest {

    /**
     * Preis pro Einheit
     */
    @Test
    public void testBerechnePreisProEinheit() {
        PreisListenPosition position = new PreisListenPosition();
        position.setPreis(10.0);
        position.setGebindeGroesse(1000.0);
        
        double preis = position.berechnePreisProEinheit();
        assertEquals(0.01, preis, 0.0001);
    }

    
}