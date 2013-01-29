package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.dateien.*;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import de.vawi.kuechenchefApp.speisen.SpeisenVerwaltung.SpeiseNichtGefunden;
import java.util.*;

/**
 * Importiert die Dateien rezepte.csv und hitliste.csv aus einem vom Anwender
 * vorgegebenen Ordner.
 *
 * @author Tatsch
 * @version 28.01.2013
 */
public class SpeisenImport {

    public static final int SPEISEN_NAME = 0;
    private Datei rezepte;
    private Datei hitliste;
    private CsvZeileSeparator separator = new CsvZeileSeparator();
    private SpeisenVerwaltung speisen = SpeisenVerwaltung.getInstanz();
    private List<Speise> unvollstaendigeSpeisen = new ArrayList<>();
    String dateiOrdner;

    // @todo Dieser Ordner muss noch irgendwo verwendet werden.
    public SpeisenImport(String dateiOrdner) {
        this.dateiOrdner = dateiOrdner;
    }

    /**
     * Importiert die Dateien rezepte.csv und hitliste.csv aus dem vom Anwender
     * angegebenen Ordner. Auf Basis dieser Dateien wird die SpeisenVerwaltung
     * für den SpeiseplanErsteller erstellt.
     *
     * Die Datei mit den Rezepten muss den namen "rezepte.csv" haben.
     *
     * Die Datei mit den beliebtesten Rezepten muss den Namen "hitliste.csv"
     * haben.
     *
     * Sind diese Dateien nicht vorhanden wird eine FileNotFoundException
     * geworfen. Anschließend wird das Programm mit der Fehlermeldung beendet.
     *
     */
    public void importFiles() {
        leseDateien();
        fuegeSpeisenVonHitlisteInSpeisenverwaltungEin();
        fuegeZutatenZuSpeisenAusRezepteDateiHinzu();
        loescheUnvollstaendigeSpeisen();
        sortiereZutatenNachKategorie();
    }

    /**
     *
     * @throws
     * de.vawi.kuechenchefApp.speisen.SpeisenImport.HitlisteDateiIstNichtValide
     * Wird geworfen, wenn die hitliste-Datei nicht gelsen werden kann.
     */
    private void fuegeSpeisenVonHitlisteInSpeisenverwaltungEin() throws HitlisteDateiIstNichtValide {
        for (String zeile : hitliste) {
            try {
                Speise speise = erstelleSpeise(zeile);
                speisen.addSpeise(speise);
            } catch (Exception ex) {
                throw new HitlisteDateiIstNichtValide(zeile);
            }
        }
    }

    private Speise erstelleSpeise(String zeile) {
        SpeisenErsteller ersteller = new SpeisenErsteller();
        Speise speise = ersteller.erstelle(zeile);
        return speise;
    }

    /**
     * Diese Methode fängt den Versuch, Zutaten
     */
    private void fuegeZutatenZuSpeisenAusRezepteDateiHinzu() {
        for (String zeile : rezepte) {
            try {
                versucheZutatZuGenerieren(zeile);
            } catch (SpeiseNichtGefunden ex) {
                handhabeSpeiseNichtGefunden(ex, zeile);
            } catch (Exception ex) {
                handhabeRezepteDateiIstNichtValide(ex, zeile);
            }
        }
    }

    private void loescheUnvollstaendigeSpeisen() {
        for (Speise speise : unvollstaendigeSpeisen) {
            speisen.entferne(speise);
        }
    }

    private void sortiereZutatenNachKategorie() {
        for (Speise speise : speisen) {
            Collections.sort(speise.getZutaten(), new Comparator<Zutat>() {
                @Override
                public int compare(Zutat o1, Zutat o2) {
                    SpeisenUndNahrungsmittelKategorie kategorie1 = o1.getNahrungsmittel().getKategorie();
                    SpeisenUndNahrungsmittelKategorie kategorie2 = o2.getNahrungsmittel().getKategorie();
                    return kategorie1.compareTo(kategorie2);
                }
            });
        }
    }

    /**
     * Diese Methode liest aus einer Zeile der rezepte-Datei den Speisennamen
     * heraus, und überprüft, ob die daneben stehende Zutat als Nahrungsmittel
     * angelegt wurde und vom Programm weiter verwendet werden kann.
     *
     * @param zeile eine Zeile der rezepte-Datei.
     * @throws
     * de.vawi.kuechenchefApp.speisen.SpeisenVerwaltung.SpeiseNichtGefunden wird
     * geworfen, wenn die Speise nicht gefunden wurde, sprich nicht aus der
     * hitliste generiert wurde.
     */
    private void versucheZutatZuGenerieren(String zeile) throws SpeiseNichtGefunden {
        List<String> zellen = separator.separiere(zeile);
        Speise speise = speisen.findeSpeise(zellen.get(SPEISEN_NAME));
        try {
            Zutat zutat = versucheNahrungsmittelZuErstellen(zeile);
            speise.addZutat(zutat);
        } catch (NahrungsmittelVerwaltung.NahrungsmittelNichtGefunden ex) {
            handhabeNahrungsmittelZuZutatNichtVorhanden(speise);
        }
    }

    /**
     * Liest die Hitliste und die Rezepte aus dem vom Anwender angegebenen
     * Ordner ein. Hierbei müssen die Dateien hitliste.csv und rezepte.csv
     * heißen.
     */
    private void leseDateien() {
        hitliste = leseDatei(dateiOrdner + "/" + "hitliste.csv");
        rezepte = leseDatei(dateiOrdner + "/" + "rezepte.csv");
    }

    protected Datei leseDatei(String dateiPfad) {
        return new DateiLeser(dateiPfad).leseDatei();
    }
/**
 * 
 * @param zeile
 * @return 
 */
    protected Zutat versucheNahrungsmittelZuErstellen(String zeile) {
        Zutat zutat;
        zutat = new ZutatErsteller().erstelle(zeile);
        return zutat;
    }

    protected void handhabeNahrungsmittelZuZutatNichtVorhanden(Speise speise) {
        unvollstaendigeSpeisen.add(speise);
    }

    protected void handhabeSpeiseNichtGefunden(SpeiseNichtGefunden ex, String zeile) {
        throw ex;
    }

    protected void handhabeRezepteDateiIstNichtValide(Exception ex, String zeile) throws RezepteDateiIstNichtValide {
        throw new RezepteDateiIstNichtValide(zeile);
    }

    public static class HitlisteDateiIstNichtValide extends RuntimeException {

        public HitlisteDateiIstNichtValide(String zeile) {
            super("Hitlistedatei enthaelt fehlerhafte Zeile: " + zeile);
        }
    }

    public static class RezepteDateiIstNichtValide extends RuntimeException {

        public RezepteDateiIstNichtValide(String zeile) {
            super("Rezeptedatei enthaelt fehlerhafte Zeile: " + zeile);
        }
    }
}
