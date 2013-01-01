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
 * Static-Methoden sollten die Ausnahme sein. Problem ist, dass du Vorteile aus der objektorientierten Programmierung damit aushebelst.
 * Z. B. Kein Polyphormismus möglich. Also wenn du Ableitung von PreiListeImport machst, kannst du static-Methoden nicht überschreiben.
 * Auch wichtig: in der Regel sollte eine Klasse nur eine public Methode haben, die mit Hilfe eines sprechenden Namen sagt, was sie tut. Hilft den Benutzer der Klasse.
 * LieferantenListe(), hm... was macht die eigentlich. Wenn man sich den Code anschaut sieht man, dass die Methode einen Lieferanten ausgibt. 
 * Also könnte man die Mehtode gibLieferantenAufKonsoleAus() nennen. Dann weiß jeder sofort was die Methode macht, ohne sich den Code anschauen zu müssen.
 * Grundsätzlich sollten solche Methoden aber in unserem Projekt nicht notwendig sein, da die Ausgabe  zum einen in Dateien erfolgen soll und die Import-Klasse
 * dafür nicht verantwortlich sein sollte (Eine Verantwortung pro Klasse) Somit hat diese Klasse schon zwei Verantwortungen, zum einen importiert die Klasse PreisListen,
 * zum Andern ist sie dafür Verantwortlich die Preislisten auf die Konsole auszugeben.
 * Ich vermute mal du wolltest testen ob die Dateien auch wirklich importiert werden. Dafür sollten wir allerdings einen Test schreiben. :-)
 * @throws FileNotFoundException Wird geworfen, wenn der Ordner nicht gefunden wird.
 * @throws Exception Wird geworfen, wenn keine Datei im Ordner gefunden wird.
 */
    @SuppressWarnings("ConvertToTryWithResources")
    public static void LieferantenListe() throws FileNotFoundException, Exception {
        //PreisListenOrdnerSuche.PreisListenOrdnerSuche(): Hm, was macht die Methode? Sucht einen Ordner. Aber ich übergeb gar kein Kriterium.
        //Ach so, aber wir brauchen doch eigenltich keine Oberflächen. Das ist zum testen, richtig? :-) Mit einem Unit-Test ist das deutlich weniger aufwendig.
        //Vielleicht wäre der Name PreisListenOrdnerSuche.anzeigen() besser. Wobei die Methode deutlich mehr macht :-)
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
