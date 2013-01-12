
package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias
 */
public class EinkaufslisteTest {

    @Test
    public void testFindeVorhandenePositionDurchNahrungsmittel() {
        Einkaufsliste liste = new Einkaufsliste();
        Nahrungsmittel kartoffeln = new Nahrungsmittel();
        liste.hinzufügenEinkaufslistenPosition(new EinkaufslistenPosition(kartoffeln));
        kartoffeln.setName("Kartoffeln");
        EinkaufslistenPosition position = liste.findePositionDurchNahrungsmittel(kartoffeln);
    
        assertEquals(kartoffeln.getName(), position.getNahrungsmittel());
    }
    
    @Test
    public void testFindeNichtVorhandenePositionDurchNahrungsmittel() {
        Einkaufsliste liste = new Einkaufsliste();
        Nahrungsmittel kartoffeln = new Nahrungsmittel();
        kartoffeln.setName("Kartoffeln");
        EinkaufslistenPosition position = liste.findePositionDurchNahrungsmittel(kartoffeln);
    
        assertEquals(kartoffeln.getName(), position.getNahrungsmittel());
    }
}