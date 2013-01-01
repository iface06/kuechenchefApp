package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * Diese Klasse ist eine Hilfsklasse für PreisListeImport. Sie liest aus den
 * importierten Dateien die Zeichen ein.
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class PreisListeLesen {

    /**
     * Diese Methode liest die Preislisten-Dateien aus.
     * 
     * Achtung! die Methode macht zuviele Dinge... 
     *
     * @param preisListenDateiName Name der Datei, die hier ausgelesen werden
     * soll.
     * @return Gbit ein Objekt der Klasse Lieferant, unterteilt in Großhändler
     * oder Bauer aus.
     */
    public static Lieferant lesePreisListenDatei(String preisListenDateiName) {

        FileReader preisListenDatei = null;
        StringBuilder text = new StringBuilder(10);
        CsvZeileSeparator csvSepp = new CsvZeileSeparator();
        Lieferant lieferant = null;

        //Öffen Datei, Behandeln von Fehlerfällen beim Öffnen und zu guter Letzt wird die Datei geschlossen. Das sind schon drei Methoden.
        //Einlesen der Zeilen ist auch noch ne Aufgabe. -> sind wir schon bei vier Methoden
        try {
            preisListenDatei = new FileReader(preisListenDateiName);
            int anzahlZeichenGelesen = 0;
            boolean ende = false;

            while (!ende) {
                anzahlZeichenGelesen = preisListenDatei.read();
                if (anzahlZeichenGelesen == -1) {
                    ende = true;
                } else {
                    text.append((char) anzahlZeichenGelesen);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Es ist keine Datei vorhanden!");
            return null;
        } catch (IOException ex) {
            Logger.getLogger(PreisListeImport.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preisListenDatei.close();
            } catch (IOException ex) {
                Logger.getLogger(PreisListeImport.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Hier ist die nächste Verantwortung! Zerlegen der Zeilen in Zellen -> fünte Methode
        //Zelle 1: Instantieren des entsprechenden Typs -> sechste Methode
        String[] zeilen = text.toString().split("\n");
        String lieferantenZeile = zeilen[0];
        List<String> lieferantenZellen = csvSepp.separiere(lieferantenZeile);

        try {
            //TODO der will dass ich hier switch case mache, aber Tobi hat gemeint, das sei nicht clean code??
            //Wer ist der? :-)
            //Switch Case Statements kommen aus der Zeit der sequentiellen Programmierung. Also zu der Zeit wo Code von oben nach unten durchgelaufen ist.
            //Seit der Objektorientierung braucht mach diese Art von Konstrukten nicht mehr, da diese hier durch z. B. Strategy Pattern abgelöst wurden.
            //Das Strategy Pattern hat gegenüber einem Switch Case Konstrukt den Vorteil, dass es einfach erweitert werden kann und keine festen Abhängigkeiten entstehen.
            if (lieferantenZellen.get(0).equals("Grosshandel")) {
                lieferant = new Grosshaendler(lieferantenZellen.get(1), Double.parseDouble(lieferantenZellen.get(2).replace(',', '.')));
            } else if (lieferantenZellen.get(0).equals("Bauer")) {
                lieferant = new Bauer(lieferantenZellen.get(1), Double.parseDouble(lieferantenZellen.get(2).replace(',', '.')));
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        for (int i = 1; i < zeilen.length; i++) {
            String preisListenPositionsZeile = zeilen[i];
            try {
                //Lieferant ist Return Value. Zeichen für evtl. falsche Stelle für Verantwortung. Gefahr durch Seiteneffekte!
                PreisListeVerarbeitung.lesePreisListenPositionsZeile(preisListenPositionsZeile, lieferant);
            } catch (Exception e) {
                System.err.println(e + "at " + preisListenDateiName + ": " + (i + 1));
            }
        }

        return lieferant;
    }
}
