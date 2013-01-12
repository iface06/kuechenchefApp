

package de.vawi.kuechenchefApp.rezepte;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.nahrungsmittel.NahrungsmittelKategorie;
import de.vawi.kuechenchefApp.nahrungsmittel.NahrungsmittelVerwaltung;
import org.junit.*;
import static org.junit.Assert.*;


public class ZutatErstellerTest {
    private Nahrungsmittel erwatetesNahrungsmittel;
    private String rezpetZeile;
    private ZutatErsteller ersteller;

    /**
     * Separiere Zeile aus Rezepte-Datei
     * Erstelle Zutat aus separierter Zeile mit Menge
     * Erstelle Nahrungsmittel wenn noch nicht vorhanden, ansonsten verknüpfe Zutat mit bereits importierten Nahrungsmittel
     */
    
    @Before
    public void before() {
        fuegeKartoffelnInNahrungsmittelVerwaltungEin();
        erstelleErwartetesNahrungsmittel();
        rezpetZeile = "\"Kartoffeln mit Senfsauce und Ei\",150,\"g\",\"Kartoffeln\"";
        ersteller = new ZutatErsteller();
    }
    
    @Test
    public void testErstelleZutat() {
        Zutat zutat = ersteller.erstelle(rezpetZeile);
        assertEquals(150, zutat.getMenge());
    }
    
    @Test
    public void testNahrungsmittelZurZutat(){
        Zutat zutat = ersteller.erstelle(rezpetZeile);
        assertEquals(erwatetesNahrungsmittel, zutat.getNahrungsmittel());
    }

    private void fuegeKartoffelnInNahrungsmittelVerwaltungEin() {
        Nahrungsmittel kartoffeln = new Nahrungsmittel();
        kartoffeln.setName("Kartoffeln");
        kartoffeln.setEinheit(Einheit.GRAMM);
        kartoffeln.setKategorie(NahrungsmittelKategorie.VEGETARISCH);
        
        NahrungsmittelVerwaltung.getInstanz().fuegeHinzu(kartoffeln);
    }

    private void erstelleErwartetesNahrungsmittel() {
        erwatetesNahrungsmittel = new Nahrungsmittel();
        erwatetesNahrungsmittel.setName("Kartoffeln");
        erwatetesNahrungsmittel.setEinheit(Einheit.GRAMM);
        erwatetesNahrungsmittel.setKategorie(NahrungsmittelKategorie.VEGETARISCH);   
    }

    
}