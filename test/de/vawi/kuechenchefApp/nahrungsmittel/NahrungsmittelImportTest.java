

package de.vawi.kuechenchefApp.nahrungsmittel;

import de.vawi.kuechenchefApp.dateien.Datei;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;


public class NahrungsmittelImportTest {
    private NahrungsmittelImport importer;
    private Nahrungsmittel erwartetesNahrungsmittel;
    private NahrungsmittelVerwaltung nahrungsmittels;

    @Before
    public void before() {
        initializeImporter();
        erstelleErwartetesNahrungsmittel();
    }
    
    @Test
    public void testImportNahrungsmittelAusRezpeteDatei() {
        
        importer.importFile();
        
        nahrungsmittels = NahrungsmittelVerwaltung.getInstanz();
        
        assertEquals(erwartetesNahrungsmittel, nahrungsmittels.findeDurchName("Kartoffeln"));
    }

    private void initializeImporter() {
        Datei rezpete = new TestbareRezepteDatei();
        importer = new NahrungsmittelImport();
        importer.setRezepteDatei(rezpete);
    }

    private void erstelleErwartetesNahrungsmittel() {
        erwartetesNahrungsmittel = new Nahrungsmittel();
        erwartetesNahrungsmittel.setName("Kartoffeln");
    }

    

    private static class TestbareRezepteDatei implements Datei{

        static List<String> zeilen = new ArrayList<>();
        static{
            zeilen.add("\"Kartoffeln mit Senfsauce und Ei\",150,\"g\",\"Kartoffeln\"");
            zeilen.add("\"Kartoffeln mit Senfsauce und Ei\",\"1,5\",,\"Ei\"");
        }
        
        
        @Override
        public String getDateinameMitPfad() {
            return "rezpete.csv";
        }

        @Override
        public Iterator<String> iterator() {
            return zeilen.iterator();
        }

       
    }

}