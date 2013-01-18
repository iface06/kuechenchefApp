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
        PreisListenPosition kartoffelAngebotA = createNahrungsmittel("Kartoffeln", 5.0, 1000.0, "Müller");
        PreisListenPosition kartoffelAngebotB = createNahrungsmittel("Kartoffeln", 10.0, 1000.0, "Meier");
        PreisListenPosition moehrenAngebot = createNahrungsmittel("Möhren", 1.0, 500.0, "Meier");
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
    public void testFindeGünstigsterGesamtPreisFuerEinkaufslistenposition() {
        Einkaufsliste liste = ersteller.erzeuge();
        assertEquals(3850.0, liste.getPositionen().get(0).getPreis(), 0.001);
    }

    /*/ findezweitpreiswertestenLieferanten
     @Test
     public void testFindePreiswertestenLieferantenFuerEinkaufslistenposition() {
             
     PreisListenPosition positionsliste = new PreisListenPosition();
        
     System.out.print("Hallo Welt!");
     
     }
     */
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
        Speise speise = new Speise();
        Zutat zutat = erzeugeZutat();
        speise.addZutat(zutat);
        return speise;
    }

    private Zutat erzeugeZutat() {
        Zutat zutat = new Zutat();
        Nahrungsmittel nahrungsmittel = erzeugeNahrungsmittel(zutat);
        zutat.setMenge(1000.0);
        zutat.setNahrungsmittel(nahrungsmittel);
        return zutat;
    }

    /*/ private Zutat berechneBenoetigteMenge() {
        
        
     Zutat zutat = new Zutat();
     Nahrungsmittel nahrungsmittel = erzeugeNahrungsmittel(zutat);
     zutat.setMenge(1000.0);
     zutat.setNahrungsmittel(nahrungsmittel);
     return null;
     }
     //*/
    private Nahrungsmittel erzeugeNahrungsmittel(Zutat zutat) {
        Nahrungsmittel nahrungsmittel = new Nahrungsmittel();
        nahrungsmittel.setName("Kartoffeln");
        nahrungsmittel.setEinheit(Einheit.STUECK);
        return nahrungsmittel;
    }

    private static PreisListenPosition createNahrungsmittel(String name, double preis, double gebindeGroesse, String lieferantenName) {
        PreisListenPosition position = new PreisListenPosition();
        Nahrungsmittel kartoffel = new Nahrungsmittel();
        kartoffel.setName(name);
        position.setNahrungsmittel(kartoffel);
        position.setGebindeGroesse(gebindeGroesse);
        position.setPreis(preis);
        Lieferant lieferant = new Grosshaendler();
        lieferant.setName(lieferantenName);
        position.setLieferant(lieferant);
        return position;
    }

    
}