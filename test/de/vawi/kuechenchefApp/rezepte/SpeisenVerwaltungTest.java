

package de.vawi.kuechenchefApp.rezepte;

import de.vawi.kuechenchefApp.PlanungsPeriode;
import de.vawi.kuechenchefApp.rezepte.SpeisenVerwaltung.SpeiseNichtGefunden;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;


public class SpeisenVerwaltungTest {
    private SpeisenVerwaltung verwaltung;
    
    @Before
    public void before() {
        verwaltung = new SpeisenVerwaltung();
        Speise speise = erstelleSpeise("Steaks");
        verwaltung.addSpeise(speise);
    }

    @Test
    public void testHinzufuegenEinerSpeise() {
        assertEquals(1, verwaltung.size());
    }
    
    @Test
    public void testHinzufuegenEinerBereitsVorhandenSpeise() { 
        Speise speise = erstelleSpeise("Steaks");
        verwaltung.addSpeise(speise);
        
        assertEquals(1, verwaltung.size());
    }
    
    @Test
    public void testFindeSpeiseMitNamen(){
        Speise steaks = verwaltung.findeSpeise("Steaks");
        
        assertEquals("Steaks", steaks.getName());
    }
    
    @Test
    public void testFindeBeliebtesteSpeisenFuerPlanungsPeriode(){
        PlanungsPeriode periode = new PlanungsPeriode();
        List<Speise> steaks = verwaltung.findeBeliebtesteSpeisenFuerPlanungsPeriode(periode);
        
        assertEquals(45, steaks.size());
    }
    
    @Test(expected=SpeiseNichtGefunden.class)
    public void testFindeNichtDieSpeiseMitNamen(){
        Speise steaks = verwaltung.findeSpeise("Drei im Weckla");
        fail();
    }
    

    private Speise erstelleSpeise(String name) {
        Speise speise = new Speise();
        speise.setName(name);
        return speise;
    }

    

}