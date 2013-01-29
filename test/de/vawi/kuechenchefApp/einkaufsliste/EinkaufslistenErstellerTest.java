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
    public static void initLieferantenVerwaltung() {
        PreisListenPosition kartoffelAngebotA = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Einheit.GRAMM).lieferant("Müller", DummyPreisListenPositionErsteller.BAUER).angebot(5.0, 1000.0, 10000).erstelle();
        PreisListenPosition kartoffelAngebotB = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Einheit.GRAMM).lieferant("Meier", DummyPreisListenPositionErsteller.BAUER).angebot(10.0, 1000.0, 5000).erstelle();
        PreisListenPosition moehrenAngebotA = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Einheit.GRAMM).lieferant("Meier", DummyPreisListenPositionErsteller.BAUER).angebot(1.0, 500.0, 4).erstelle();
        PreisListenPosition moehrenAngebotB = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Einheit.GRAMM).lieferant("Müller", DummyPreisListenPositionErsteller.BAUER).angebot(2.0, 500.0, 10).erstelle();
        verwaltung = LieferantenVerwaltung.getInstanz();
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(kartoffelAngebotA, kartoffelAngebotB, moehrenAngebotA, moehrenAngebotB));
    }
    private EinkaufslistenErsteller ersteller;
    
    
    public void initSpeiseplaene() {
        Speiseplan plan = erzeugeDummySpeiseplan(Kantine.MUELHEIM_AN_DER_RUHR);
        Speiseplan plan1 = erzeugeDummySpeiseplan(Kantine.ESSEN);
        ersteller = new EinkaufslistenErsteller();
        ersteller.add(plan);
        ersteller.add(plan1);
    }

    @Test
    public void testErzeugeEinkauflistenPosition() {
        initSpeiseplaene();
        Einkaufsliste liste = ersteller.erzeuge();
        EinkaufslistenPosition position = liste.getPositionen().get(0);

        assertEquals(1, liste.getPositionen().size());
        assertEquals("Kartoffeln", position.getName());
        assertEquals(Einheit.STUECK, position.getEinheit());
    }

    @Test
    public void testFuegeGleichesNahrungsmittelHinzu() {
        initSpeiseplaene();
        Einkaufsliste liste = ersteller.erzeuge();
        EinkaufslistenPosition position = liste.getPositionen().get(0);

        assertEquals(1, liste.getPositionen().size());
        assertEquals(3.0, position.getAnzahlGebinde(), 0.0001);
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
        initSpeiseplaene();
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals("Müller", liste.getPositionen().get(0).getLieferant().getName());
    }
    

    @Test
    public void testFindeGünstigstenGesamtPreisFuerEinkaufslistenposition() {
        initSpeiseplaene();
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals(15.0, liste.getPositionen().get(0).getPreis(), 0.001);
    }
    
    @Test
    public void testGesamtmengeGroeßerAlsVoratsmengeEinesLieferanten() {
        Zutat moehren = new DummyZutat().name("Möhren").einheit(Einheit.STUECK).menge(2.0).kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).erstelle();
        Speise speise = new DummySpeise().name("Buttermöhren").mitZutat(moehren).erstelle();
        Speiseplan planEssen = new DummySpeiseplan().fuerKantine(Kantine.ESSEN).plusTag(speise, speise, speise).erstelle();
        Speiseplan planMuehlheim = new DummySpeiseplan().fuerKantine(Kantine.MUELHEIM_AN_DER_RUHR).plusTag(speise, speise, speise).erstelle();
        ersteller = new EinkaufslistenErsteller();
        ersteller.add(planEssen);
        ersteller.add(planMuehlheim);
    
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals(2, liste.getPositionen().size());
        assertEquals(4.0, liste.getPositionen().get(0).getAnzahlGebinde(), 0.001);
        assertEquals(1.0, liste.getPositionen().get(1).getAnzahlGebinde(), 0.001);
    }    
    
    private Speiseplan erzeugeDummySpeiseplan(Kantine kantine) {
        List<Tag> tage = new ArrayList<Tag>();
        Tag tag = erzeugeTag();
        tage.add(tag);
        Speiseplan plan = new Speiseplan(kantine, tage);

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
        Zutat kartoffeln = new DummyZutat().name("Kartoffeln").einheit(Einheit.STUECK).menge(2.0).kategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH).erstelle();
        return new DummySpeise().name("Bratkaroffeln").mitZutat(kartoffeln).beliebtheit(1).erstelle();
    }
}
