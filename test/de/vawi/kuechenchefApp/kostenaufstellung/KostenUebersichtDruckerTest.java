
package de.vawi.kuechenchefApp.kostenaufstellung;

import de.vawi.kuechenchefApp.lieferanten.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class KostenUebersichtDruckerTest {

    @Test
    public void testKostenuebersichtAusdruck() {
        KostenUebersicht kosten = new KostenUebersicht();
        kosten.setGesamtKosten(35000.0);
        kosten.setLieferKostenGesamt(5000.0);
        List<Kostenaufstellung> kostenaufstellungProLieferant = new ArrayList<>();
        Kostenaufstellung kostenaufstellung = new Kostenaufstellung();
        Lieferant lieferant = new Bauer();
        lieferant.setName("Bauer Huber");
        kostenaufstellung.setLieferant(lieferant);
        kostenaufstellung.setEinkaufslistenPositionsListe(null);
        kosten.setKostenaufstellungenProLieferant(kostenaufstellungProLieferant);
        KostenUebersichtDrucker drucker = new KostenUebersichtDrucker();
        String uebersicht = drucker.drucke(null);
        
        
    }
    
}