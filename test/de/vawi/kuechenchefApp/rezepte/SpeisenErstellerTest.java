

package de.vawi.kuechenchefApp.rezepte;

import org.junit.*;
import static org.junit.Assert.*;


public class SpeisenErstellerTest {

    
    
    @Test
    public void testSomeMethod() {
        String hitlisteZeile = "1,\"Bohneneintopf Mexiko\"";
        Speise speise = new SpeisenErsteller().erstelle(hitlisteZeile);
        
        assertEquals(1, speise.getBeliebtheit());
        assertEquals("Bohneneintopf Mexiko", speise.getName());
    }

}