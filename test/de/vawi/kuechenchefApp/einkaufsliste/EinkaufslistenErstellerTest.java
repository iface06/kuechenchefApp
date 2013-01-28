package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.lieferanten.*;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import de.vawi.kuechenchefApp.speisen.*;
import de.vawi.kuechenchefApp.speiseplan.*;
import java.util.*;
import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author Matthias
 */
public class EinkaufslistenErstellerTest {

    private static LieferantenVerwaltung verwaltung;

    @BeforeClass
    public static void beforeClass() {
        PreisListenPosition kartoffelAngebotA = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Einheit.GRAMM).lieferant("Müller", DummyPreisListenPositionErsteller.BAUER).angebot(5.0, 1000.0, 10000).erstelle();
        PreisListenPosition kartoffelAngebotB = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Einheit.GRAMM).lieferant("Meier", DummyPreisListenPositionErsteller.BAUER).angebot(10.0, 1000.0, 5000).erstelle();
        PreisListenPosition moehrenAngebot = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Einheit.GRAMM).lieferant("Meier", DummyPreisListenPositionErsteller.BAUER).angebot(1.0, 500.0, 300).erstelle();
        verwaltung = LieferantenVerwaltung.getInstanz();
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(kartoffelAngebotA, kartoffelAngebotB, moehrenAngebot));
    }
    private EinkaufslistenErsteller ersteller;
    
    @Before
    public void before() {
        Speiseplan plan = erzeugeDummySpeiseplan();
        Speiseplan plan1 = erzeugeDummySpeiseplan();
        ersteller = new EinkaufslistenErsteller();
        ersteller.add(plan);
        ersteller.add(plan1);
    }

    @Test
    public void testErzeugeEinkauflistenPosition() {
        
        Einkaufsliste liste = ersteller.erzeuge();
        EinkaufslistenPosition position = liste.getPositionen().get(0);

        assertEquals("Kartoffeln", position.getName());
        assertEquals(Einheit.STUECK, position.getEinheit());
    }

    @Test
    public void testFuegeGleichesNahrungsmittelHinzu() {
        
        Einkaufsliste liste = ersteller.erzeuge();
        EinkaufslistenPosition position = liste.getPositionen().get(0);

        assertEquals(1, liste.getPositionen().size());
        assertEquals(1502000.0, position.getMenge(), 0.0001);
    }

    /*
     * Billigsten Lieferanten für Position findens
     * Prüfe ob angebotene Menge ausreicht
     * Passt Gebindegröße zu benötigter Menge
     * Wenn angebotene Menge nicht ausreicht nächstgünstigesten Lieferanten wählen
     * 
     */
    @Test
    public void testFindePreiswertestenLieferantenFuerEinkaufslistenposition() {
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals("Müller", liste.getPositionen().get(0).getLieferant().getName());
    }
    

    @Test
    public void testFindeGünstigstenGesamtPreisFuerEinkaufslistenposition() {
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals(3850.0, liste.getPositionen().get(0).getPreis(), 0.001);
    }
    
    
    /*/
    @Test
    public void testBerechneBenoetigteAnzahlAnGebinden() {
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals(100.0, liste.getPositionen().get(0).getMenge(), 0.0001);
        
    }
    /*/
    
    /*/
    @Test
    public void testVergleicheAnzahlBenoetigterGebindeMitVorhandenerAnzahl() {
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals(100.0, liste.getPositionen().get(0).getMenge(), 0.0001);
        
    }
    /*/

    /*/
    @Test
    public void testVergleicheAnzahlBenoetigterGebindeMitVorhandenerAnzahl() {
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals(100.0, liste.getPositionen().get(0).getMenge(), 0.0001);
        
    }
    /*/    
    
    private Speiseplan erzeugeDummySpeiseplan() {
        List<Tag> tage = new ArrayList<Tag>();
        Tag tag = erzeugeTag();
        tage.add(tag);
        Speiseplan plan = new Speiseplan(Kantine.ESSEN, tage);

        return plan;
    }

    private Tag erzeugeTag() {
        Tag tag = new Tag(1);
        Speise speise = erzeugeSpeise();
        Speise speise1 = erzeugeSpeise();
        Speise speise2 = erzeugeSpeise();
        tag.setBeliebtesteSpeise(speise);
        tag.setZweitbeliebtesteSpeise(speise1);
        tag.setDrittbeliebtesteSpeise(speise2);
        return tag;
    }

    private Speise erzeugeSpeise() {
        return new DummySpeise().mit("Kartoffeln", 1).mitZutat("Kartoffeln", Einheit.STUECK, 1000.0, 100000).erstelle();
    }
}
