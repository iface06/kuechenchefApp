

package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.speisen.Speise;
import de.vawi.kuechenchefApp.speisen.Zutat;
import de.vawi.kuechenchefApp.speisen.SpeisenImport;
import de.vawi.kuechenchefApp.dateien.Datei;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;


public class SpeisenImportTest {
    private SpeisenImport importer;
    private SpeisenVerwaltung speisen;

    /**
     * Speisen aus Hitliste importieren -> OK
     * Zutaten aus Rezpete importieren -> OK
     * Zutaten zu Speisen zuordnen -> OK
     */
    
    @Before
    public void before(){
        fuegeKartoffelUndChilipulvernInNahrungsmittelVerwaltungEin();
        initializiereImporter();
        initizialisiereSpeisenVerwaltung();    
    }
    
    @Test
    public void testSpeisenImportAusHitliste() {
        importer.importFiles();
        
        assertEquals(1, speisen.findeSpeise("Bohneneintopf Mexiko").getBeliebtheit());
        assertEquals(2, speisen.findeSpeise("Falscher Hase").getBeliebtheit());
    }
    
    @Test
    public void testZutatenAusRezepteZuSpeisenImportieren(){
        importer.importFiles();
        
        Speise speise = speisen.findeSpeise("Bohneneintopf Mexiko");
        List<Zutat> zutatenZuSpeise = speise.getZutaten();
        assertEquals("Kartoffeln", zutatenZuSpeise.get(0).getNahrungsmittel().getName());
        assertEquals("Chilipulver", zutatenZuSpeise.get(1).getNahrungsmittel().getName());        
    }
    
    @Test
    public void testKategorisierungDerVegetarischenSpeisen(){
        importer.importFiles();
        
        Speise speise = speisen.findeSpeise("Bohneneintopf Mexiko");
        assertEquals(SpeisenUndNahrungsmittelKategorie.VEGETARISCH, speise.getKategorie());
    }
    
    @Test
    public void testKategorisierungDerFleischSpeisen(){
        importer.importFiles();
        
        Speise speise = speisen.findeSpeise("Falscher Hase");
        assertEquals(SpeisenUndNahrungsmittelKategorie.FLEISCH, speise.getKategorie());
    }
    
    private void fuegeKartoffelUndChilipulvernInNahrungsmittelVerwaltungEin() {
        Nahrungsmittel kartoffeln = new Nahrungsmittel();
        kartoffeln.setName("Kartoffeln");
        kartoffeln.setEinheit(Einheit.GRAMM);
        kartoffeln.setKategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH);
        
        Nahrungsmittel chilipulver = new Nahrungsmittel();
        chilipulver.setName("Chilipulver");
        chilipulver.setEinheit(Einheit.GRAMM);
        chilipulver.setKategorie(SpeisenUndNahrungsmittelKategorie.VEGETARISCH);
        
        Nahrungsmittel hase = new Nahrungsmittel();
        hase.setName("Hase");
        hase.setEinheit(Einheit.GRAMM);
        hase.setKategorie(SpeisenUndNahrungsmittelKategorie.FLEISCH);        
        
        NahrungsmittelVerwaltung.getInstanz().fuegeHinzu(kartoffeln);
        NahrungsmittelVerwaltung.getInstanz().fuegeHinzu(chilipulver);
        NahrungsmittelVerwaltung.getInstanz().fuegeHinzu(hase);
    }

    private void initializiereImporter() {
        Datei hitliste = new TestbareHitlisteDatei();
        Datei rezepte = new TestbareRezpeteDatei();
        importer = new SpeisenImport();
        importer.setHitliste(hitliste);
        importer.setRezepte(rezepte);
    }

    private void initizialisiereSpeisenVerwaltung() {
        speisen = SpeisenVerwaltung.getInstanz();
    }

    private static class TestbareHitlisteDatei implements Datei {

        private static List<String> zeilen = new ArrayList<>();
        static{
            zeilen.add("1,\"Bohneneintopf Mexiko\"");
            zeilen.add("2,\"Falscher Hase\"");
        }

        @Override
        public String getDateinameMitPfad() {
            return "TestbareHitliste.csv";
        }

        @Override
        public Iterator<String> iterator() {
            return zeilen.iterator();
        }
    }
    private static class TestbareRezpeteDatei implements Datei {

        private static List<String> zeilen = new ArrayList<>();
        static{
            zeilen.add("\"Bohneneintopf Mexiko\",150,\"g\",\"Kartoffeln\"");
            zeilen.add("\"Bohneneintopf Mexiko\",10,\"g\",\"Chilipulver\"");
            zeilen.add("\"Falscher Hase\",10,\"g\",\"Hase\"");
            zeilen.add("\"Falscher Hase\",10,\"g\",\"Chilipulver\"");
        }

        @Override
        public String getDateinameMitPfad() {
            return "TestbareHitliste.csv";
        }

        @Override
        public Iterator<String> iterator() {
            return zeilen.iterator();
        }
    }
}