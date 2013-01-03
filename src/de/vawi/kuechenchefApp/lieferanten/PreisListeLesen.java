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

    private CsvZeileSeparator csvSepp = new CsvZeileSeparator();

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
    public Lieferant lesePreisListenDatei(String preisListenDateiName) {
        StringBuilder text = leseDatei(preisListenDateiName);
        String[] zeilen = text.toString().split("\n");
        Lieferant lieferant = erstelleLieferant(zeilen);
        lesePreisListenPositionZuLieferantenEin(zeilen, lieferant);
        return lieferant;
    }

    protected FileReader oeffneDatei(String preisListenDateiName) throws FileNotFoundException {
        FileReader preisListenDatei;
        preisListenDatei = new FileReader(preisListenDateiName);
        return preisListenDatei;
    }

    protected StringBuilder leseZeichenAusDatei(FileReader preisListenDatei) throws IOException {
        StringBuilder text = new StringBuilder();
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
        return text;
    }

    protected StringBuilder leseDatei(String preisListenDateiName) {
        FileReader preisListenDatei = null;
        StringBuilder text = new StringBuilder();
        try {
            preisListenDatei = oeffneDatei(preisListenDateiName);
            text = leseZeichenAusDatei(preisListenDatei);
        } catch (FileNotFoundException ex) {
            System.err.println("Es ist keine Datei vorhanden!");
        } catch (IOException ex) {
            Logger.getLogger(PreisListeImport.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            schliesseDatei(preisListenDatei);
        }
        return text;
    }

    protected void schliesseDatei(FileReader preisListenDatei) {
        try {
            preisListenDatei.close();
        } catch (IOException ex) {
            Logger.getLogger(PreisListeImport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected double parseStringToDouble(List<String> lieferantenZellen) {
        try {
            return Double.parseDouble(lieferantenZellen.get(2).replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println(lieferantenZellen.get(1) + ": Lieferkostensatz bzw. Entfernung nicht korrekt!");
            return 0.0;
        }
    }

    protected Lieferant erstelleLieferantAusZellen(List<String> lieferantenZellen) {
        //TODO der will dass ich hier switch case mache, aber Tobi hat gemeint, das sei nicht clean code??
        //Wer ist der? :-)
        //Switch Case Statements kommen aus der Zeit der sequentiellen Programmierung. Also zu der Zeit wo Code von oben nach unten durchgelaufen ist.
        //Seit der Objektorientierung braucht mach diese Art von Konstrukten nicht mehr, da diese hier durch z. B. Strategy Pattern abgelöst wurden.
        //Das Strategy Pattern hat gegenüber einem Switch Case Konstrukt den Vorteil, dass es einfach erweitert werden kann und keine festen Abhängigkeiten entstehen.
        Lieferant lieferant = null;
        if (lieferantenZellen.get(0).equals("Grosshandel")) {
            lieferant = new Grosshaendler(lieferantenZellen.get(1), parseStringToDouble(lieferantenZellen));
        } else if (lieferantenZellen.get(0).equals("Bauer")) {
            lieferant = new Bauer(lieferantenZellen.get(1), parseStringToDouble(lieferantenZellen));
        }
        return lieferant;
    }

    protected void lesePreisListenPositionZuLieferantenEin(String[] zeilen, Lieferant lieferant) {
        PreisListenPositionZeileTransformer zeilenTransformer = new PreisListenPositionZeileTransformer();
        for (int i = 1; i < zeilen.length; i++) {
            String positionsZeile = zeilen[i];
            PreisListenPosition position = zeilenTransformer.transformiere(positionsZeile);
            position.setLieferant(lieferant);
            lieferant.hinzufuegenPreisListenPosition(position);
        }
    }

    protected Lieferant erstelleLieferant(String[] zeilen) {
        String lieferantenZeile = zeilen[0];
        List<String> lieferantenZellen = csvSepp.separiere(lieferantenZeile);
        Lieferant lieferant = erstelleLieferantAusZellen(lieferantenZellen);
        return lieferant;
    }
}
