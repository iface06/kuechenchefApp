package de.vawi.kuechenchefApp.lieferanten;


import de.vawi.kuechenchefApp.dateien.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * Diese Klasse importiert alle Lieferanten und ihre Angebote aus den Preislistendateien.
 *
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class PreisListenImport {
    private PreisListen listen;
    private LieferantenVerwaltung lieferantenVerwaltung = LieferantenVerwaltung.getInstanz();

    /**
     * Diese Methode importiert die Preislistendateien.
     * 
     * Werden keine Preislisten-Dateien gefunden, so
     * wird eine Exception geworfen und das Programm anschließend
     * beendet.
     *
     */

    public void importFiles() {
        try {
            LieferantenVerwaltung verwaltung = LieferantenVerwaltung.getInstanz();
            lesePreisListenDateien(DateiOrdnerSuche.dateiOrdnerSuche());
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Diese Methode liest die Dateien aus dem angegebenen Ordner ein und listet
     * sie auf.
     *
     * @param ordner Hier wird beim Aufrufen der Methode der Pfad zum Ordner, wo
     * die Preislisten hinterlegt sind, als Parameter angegeben.
     * @return Gibt eine Auflistung der Lieferanten mit ihren jeweiligen
     * Preislisten-Positionen aus.
     * @throws Exception Wird geworfen, wenn Dateien gefunden werden, aber nicht
     * geöffnet werden können.
     */
    private void lesePreisListenDateien(String ordner) throws Exception {
        lesePreisListenDateien();
        for (PreisListe preisListe : listen) {
            erstelleLieferantUndPreisListenPositionen(preisListe);   
        }
    }
    
     private CsvZeileSeparator csvSepp = new CsvZeileSeparator();

    /**
     * Diese Methode liest die Preislisten-Dateien aus.
     *
     *
     * @param preisListenDateiName Name der Datei, die hier ausgelesen werden
     * soll.
     * @return Gbit ein Objekt der Klasse Lieferant, unterteilt in Großhändler
     * oder Bauer aus.
     */
     
     /**
      * Diese Methode erstellt einer Liste an Preislistenpositionen pro Lieferant.
      * @param preisListe 
      * 
      */
    private void erstelleLieferantUndPreisListenPositionen(PreisListe preisListe) {
        Lieferant lieferant = erstelleLieferant(preisListe);
        List<PreisListenPosition> positionen = erstellePreislistenPositionenZuLieferant(preisListe, lieferant);
        lieferantenVerwaltung.hinzufuegenPreisListenPosition(positionen);
    }
    
    private Lieferant erstelleLieferantAusZellen(List<String> lieferantenZellen) {
        return new LieferantenErsteller().erstelle(lieferantenZellen);
    }

    private List<PreisListenPosition> erstellePreislistenPositionenZuLieferant(PreisListe preisliste, Lieferant lieferant) {
        List<PreisListenPosition> positionen = new ArrayList<>();
        PreisListenPositionErsteller ersteller = new PreisListenPositionErsteller(lieferant);
        
        Iterator<String> positionszeilen = preisliste.preisListenPositionenIterator();
        while(positionszeilen.hasNext()){
            String zeile = positionszeilen.next();
            PreisListenPosition position = ersteller.erstelle(zeile);
            positionen.add(position);
        }
        
        return positionen;
    }


    private void lesePreisListenDateien() {
        listen = new PreisListen();
        listen.leseDateien();
    }

    private List<String> separiereZeileInZellen(String lieferantenZeile) {
        List<String> lieferantenZellen = csvSepp.separiere(lieferantenZeile);
        return lieferantenZellen;
    }

    private Lieferant erstelleLieferant(PreisListe preisListe) {
        String lieferantenZeile = preisListe.getLieferantenZeile();
        List<String> lieferantenZellen = separiereZeileInZellen(lieferantenZeile);
        Lieferant lieferant = erstelleLieferantAusZellen(lieferantenZellen);
        return lieferant;
    }
}
