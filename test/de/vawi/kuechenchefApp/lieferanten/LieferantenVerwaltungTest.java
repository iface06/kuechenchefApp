

package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import java.util.*;
import static org.junit.Assert.assertEquals;
import org.junit.*;


public class LieferantenVerwaltungTest {
    private static LieferantenVerwaltung verwaltung;
    
    @BeforeClass
    public static void beforeClass() {
        PreisListenPosition kartoffelAngebotA = createNahrungsmittel("Kartoffeln");
        PreisListenPosition kartoffelAngebotB = createNahrungsmittel("Kartoffeln");
        PreisListenPosition moehrenAngebot = createNahrungsmittel("Möhren");
        verwaltung = LieferantenVerwaltung.getInstance();
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(kartoffelAngebotA, kartoffelAngebotB, moehrenAngebot));
    }
    
    @Test
    public void testFindeKartoffeln(){
        Nahrungsmittel kartoffeln = new Nahrungsmittel();
        kartoffeln.setName("Kartoffeln");
        List<PreisListenPosition> positionen = verwaltung.findeDurchNahrungsmittel(kartoffeln);
        
        assertEquals(2, positionen.size());
    }
    
    @Test
    public void testFindeMoehren(){
        Nahrungsmittel moehren = new Nahrungsmittel();
        moehren.setName("Möhren");
        List<PreisListenPosition> positionen = verwaltung.findeDurchNahrungsmittel(moehren);
        
        assertEquals(1, positionen.size());
    }

    private static PreisListenPosition createNahrungsmittel(String name) {
        PreisListenPosition position = new PreisListenPosition();
        Nahrungsmittel kartoffel = new Nahrungsmittel();
        kartoffel.setName(name);
        position.setNahrungsmittel(kartoffel);
        return position;
    }


}