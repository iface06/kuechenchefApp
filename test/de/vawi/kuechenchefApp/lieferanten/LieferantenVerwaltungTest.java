

package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import java.util.*;
import static org.junit.Assert.assertEquals;
import org.junit.*;


public class LieferantenVerwaltungTest {
    private static LieferantenVerwaltung verwaltung;
    
    @BeforeClass
    public static void beforeClass() {
        PreisListenPosition kartoffelAngebotA = createNahrungsmittel("Kartoffeln", 5.0, 1000.0);
        PreisListenPosition kartoffelAngebotB = createNahrungsmittel("Kartoffeln", 10.0, 1000.0);
        PreisListenPosition moehrenAngebot = createNahrungsmittel("Möhren", 1.0, 500.0);
        verwaltung = LieferantenVerwaltung.getInstanz();
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(kartoffelAngebotA, kartoffelAngebotB, moehrenAngebot));
    }
    
    @Test
    public void testFindeKartoffelnSortiertNachPreis(){ 
        Nahrungsmittel kartoffeln = new Nahrungsmittel();
        kartoffeln.setName("Kartoffeln");
        List<PreisListenPosition> positionen = verwaltung.findeDurchNahrungsmittel(kartoffeln);
        
        assertEquals(2, positionen.size());
        assertEquals(5.0, positionen.get(0).getPreis(), 0.0001);
        assertEquals(10.0, positionen.get(1).getPreis(), 0.0001);
    }
    
    @Test
    public void testFindeMoehren(){
        Nahrungsmittel moehren = new Nahrungsmittel();
        moehren.setName("Möhren");
        List<PreisListenPosition> positionen = verwaltung.findeDurchNahrungsmittel(moehren);
        
        assertEquals(1, positionen.size());
    }
    
    private static PreisListenPosition createNahrungsmittel(String name, double preis, double gebindeGroesse) {
        PreisListenPosition position = new PreisListenPosition();
        Nahrungsmittel kartoffel = new Nahrungsmittel();
        kartoffel.setName(name);
        position.setNahrungsmittel(kartoffel);
        position.setGebindeGroesse(gebindeGroesse);
        position.setPreis(preis);
        return position;
    }


}
