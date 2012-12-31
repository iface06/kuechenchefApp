

package de.vawi.kuechenchefApp.nahrungsmittel;

import org.junit.*;
import static org.junit.Assert.*;


public class NahrungsmittelErstellerTest {

    @Test
    public void testName() {
        String rezpetZeile = "\"Kartoffeln mit Senfsauce und Ei\",150,\"g\",\"Kartoffeln\"";
        NahrungsmittelErsteller ersteller = new NahrungsmittelErsteller();
        Nahrungsmittel nahrungsmittel = ersteller.erstelle(rezpetZeile);
        
        assertEquals("Kartoffeln", nahrungsmittel.getName());
    }
    
    @Test
    public void testEinheit() {
        String rezpetZeile = "\"Kartoffeln mit Senfsauce und Ei\",150,\"g\",\"Kartoffeln\"";
        NahrungsmittelErsteller ersteller = new NahrungsmittelErsteller();
        Nahrungsmittel nahrungsmittel = ersteller.erstelle(rezpetZeile);
        
        assertEquals(Einheit.GRAMM, nahrungsmittel.getEinheit());
    }
    

}