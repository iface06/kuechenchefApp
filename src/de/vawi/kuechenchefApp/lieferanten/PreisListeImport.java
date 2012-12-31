package de.vawi.kuechenchefApp.lieferanten;

import java.io.*;
import java.util.*;

/**
 * Importiert alle Lieferanten und ihre Angebote aus den Preislistendateien.
 *
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class PreisListeImport {

    /**
     * Importiert die Preislistedateien aus dem Ordner "Lieferanten" und
     * erstellt die LieferantenListe mit allen Lieferanten und Angeboten.
     *
     * Die Dateien müssen nach folgender Konvention benannt werden:
     * preisliste_[lfd. Nummer].csv [lfd. Nummer] = Aufeinander folgende Integer
     * (1, 2, 3, ...) Beispiel: preisliste_1.csv, preisliste_2.csv usw.
     *
     * Werden keine Dateien gefunden oder enthält der Ordner keine Dateien, so
     * wird eine FileNotFoundException geworfen und das Programm anschließend
     * beendet.
     *
     * @return Gibt die LieferantenListe zurück.
     */
    //TODO: habe den unten stehenden code nicht gebraucht.
    //public LieferantenVerwaltung importFiles() {
    //        LieferantenVerwaltung verwaltung = new LieferantenVerwaltung(new ArrayList<Lieferant>());
    //return verwaltung ;
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
    public static List<Lieferant> lesePreisListenDateien(String ordner) throws Exception {

        
        List<Lieferant> lieferanten = new ArrayList<>();
        File preisListenOrdner = new File(ordner);
        File[] preisListenDateien = preisListenOrdner.listFiles(new PreisListenDateiFilter());


        for (int i = 0; i < preisListenDateien.length; i++) {
            lieferanten.add(PreisListeLesen.lesePreisListenDatei(preisListenDateien[i].getAbsolutePath()));
        }

        return lieferanten;
    }
/**
 * TODO stimmt das so?
 * @throws FileNotFoundException Wird geworfen, wenn der Ordner nicht gefunden wird.
 * @throws Exception Wird geworfen, wenn keine Datei im Ordner gefunden wird.
 */
    @SuppressWarnings("ConvertToTryWithResources")
    public static void LieferantenListe() throws FileNotFoundException, Exception {
        List<Lieferant> lieferanten = PreisListeImport.lesePreisListenDateien(PreisListenOrdnerSuche.PreisListenOrdnerSuche());
        for (int i = 0; i < lieferanten.size(); i++) {
            Lieferant lieferant = lieferanten.get(i);
            System.out.println(lieferant);

            for (int k = 0; k < lieferant.getPreisListenPositionAnzahl(); k++) {
                System.out.println("\t" + lieferant.getPreisListenPosition(k));
            }
        }
    }
}
