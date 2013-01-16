
package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.lieferanten.PreisListenPosition;
import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.speisen.Speise;
import de.vawi.kuechenchefApp.speisen.Zutat;
import de.vawi.kuechenchefApp.speiseplan.Kantine;
import de.vawi.kuechenchefApp.speiseplan.Speiseplan;
import de.vawi.kuechenchefApp.speiseplan.Tag;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias
 */
public class EinkaufslistenErstellerTest {

    @Test
    public void testErzeugeEinkauflistenPosition() {
       Speiseplan plan = erzeugeDummySpeiseplan();
        
       EinkaufslistenErsteller ersteller = new EinkaufslistenErsteller();
       ersteller.add(plan);
       Einkaufsliste liste = ersteller.erzeuge();
       EinkaufslistenPosition position = liste.getPositionen().get(0);
   
       assertEquals("Kartoffeln", position.getNahrungsmittel());
       assertEquals(Einheit.STUECK,position.getEinheit());
    }
    
    @Test
    public void testFuegeGleichesNahrungsmittelHinzu() {
       Speiseplan plan = erzeugeDummySpeiseplan();
        
       EinkaufslistenErsteller ersteller = new EinkaufslistenErsteller();
       ersteller.add(plan);
       Einkaufsliste liste = ersteller.erzeuge();
       EinkaufslistenPosition position = liste.getPositionen().get(0);
   
       
       assertEquals(1, liste.getPositionen().size());
       assertEquals(3000.0, position.getMenge(), 0.0001);
    }
    @Test
    public void testBerechneAnzahlBenoetigterGerichte() {

        double berechneteMenge = 1.5*3;
        System.out.print(berechneteMenge);
        
    }
    
    //@Test
    //public void testFindePreiswertestenLieferantenFuerEinkaufslistenposition() {
    //         
    //    PreisListenPosition positionsliste = new PreisListenPosition();
    //    
    //    System.out.print("Hallo Welt!");
    //    
    //}

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
    
    //private PreisListenPosition erzeugeDummyPreisliste(){
    //    List<PreisListenPosition> positionen = new ArrayList<PreisListenPosition>();
    //    PreisListenPosition preisListenPosition = erzeugePreisListenPosition();
    //    preisListenPosition.add(preisListenPosition);
    //    PreisListenPosition Preisliste = new PreisListenPosition();
        
    //    return Preisliste;
    //}
    
}