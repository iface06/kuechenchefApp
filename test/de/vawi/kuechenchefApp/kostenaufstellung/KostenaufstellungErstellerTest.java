package de.vawi.kuechenchefApp.kostenaufstellung;

import de.vawi.kuechenchefApp.einkaufsliste.*;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Sonja
 */
public class KostenaufstellungErstellerTest {

    private Einkaufsliste einkaufsliste;
    
    @Before
    public void initEinkaufsliste(){
        einkaufsliste = erstelleEinkaufsliste(DummyEinkaufslistenPosition.eier(), DummyEinkaufslistenPosition.rinderhuefte());
    }
    
    @Test
    public void testBerechneGesamtKosten() {
 
        KostenaufstellungErsteller ersteller = new KostenaufstellungErsteller();
        ersteller.setEinkaufsliste(einkaufsliste);
        double gesamtkosten = ersteller.berechneGesamtKosten();
        
        assertEquals(850.0, gesamtkosten, 0.001);
    }

    @Test
    public void testGruppierungNachLieferanten() {    
        KostenaufstellungErsteller ersteller = new KostenaufstellungErsteller();
        ersteller.setEinkaufsliste(einkaufsliste);
        List<Kostenaufstellung> aufstellungen = ersteller.kostenaufstellungNachLieferant();
        
        assertEquals(2, aufstellungen.size());
        assertEquals("Heinrich", aufstellungen.get(0).getLieferant().getName());
        assertEquals("Otto Gourmet", aufstellungen.get(1).getLieferant().getName());
    }
    
    @Test
    public void testBerechneEinkaufsKostenProLieferant() {
        KostenaufstellungErsteller ersteller = new KostenaufstellungErsteller();
        ersteller.setEinkaufsliste(einkaufsliste);
        List<Kostenaufstellung> aufstellungen = ersteller.kostenaufstellungNachLieferant();
        
        assertEquals(2, aufstellungen.size());
        assertEquals(500.0, aufstellungen.get(0).berechneEinkaufsKostenProLieferant(), 0.001);
        assertEquals(300.0, aufstellungen.get(1).berechneEinkaufsKostenProLieferant(), 0.001);
    }
    
    @Test
    public void testBerechneLieferKostenProLieferant() {
        KostenaufstellungErsteller ersteller = new KostenaufstellungErsteller();
        ersteller.setEinkaufsliste(einkaufsliste);
        List<Kostenaufstellung> aufstellungen = ersteller.kostenaufstellungNachLieferant();
        
        assertEquals(2, aufstellungen.size());
        assertEquals(20.0, aufstellungen.get(0).berechneLieferKostenProLieferant(), 0.001);
        assertEquals(30.0, aufstellungen.get(1).berechneLieferKostenProLieferant(), 0.001);
    }

    private Einkaufsliste erstelleEinkaufsliste(EinkaufslistenPosition... positionen) {
        Einkaufsliste liste = new Einkaufsliste();
        for (EinkaufslistenPosition position : positionen) {
            liste.hinzufügenEinkaufslistenPosition(position);            
        }
        return liste;
    }
}