

package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.lieferanten.PreisListenPositionErsteller.FehlerBeimErstellenEinerPreislistenPosition;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import org.junit.*;
import static org.junit.Assert.*;


public class PreisListenPositionErstellerTest {
    Lieferant lieferant = erstelleLieferant();
    String zeile = "10,,\"Burgerbroetchen\",,\"5,91\",1000";
    PreisListenPositionErsteller ersteller = new PreisListenPositionErsteller(lieferant);

    @Test
    public void testPreislistenPositionErstellen() {
        PreisListenPosition position = ersteller.erstelle(zeile);
        
        assertEquals("Burgerbroetchen", position.getNahrungsmittel().getName());
        assertEquals(SpeisenUndNahrungsmittelKategorie.VEGETARISCH, position.getNahrungsmittel().getKategorie());
        assertEquals(lieferant, position.getLieferant());
    }
    
    @Test(expected=FehlerBeimErstellenEinerPreislistenPosition.class)
    public void testPreislistenPositionErstellenFail() {
        zeile = ",,,Bei einem Syntaxfehler wie diesem sollte ihr programm nicht abbrechen :-)";
        PreisListenPosition position = ersteller.erstelle(zeile);
        fail();
    }

    private Lieferant erstelleLieferant() {
        Lieferant lieferant = new Lieferant(){

            @Override
            double berechneLieferkosten(double einkaufsWert) {
                return 0.0;
            }
            
        };
        lieferant.setName("Testlieferant");
        return lieferant;
    }
}