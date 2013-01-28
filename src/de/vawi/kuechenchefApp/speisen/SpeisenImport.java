package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.dateien.*;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import de.vawi.kuechenchefApp.speisen.SpeisenVerwaltung.SpeiseNichtGefunden;
import java.util.*;

/**
 * Importiert die Dateien rezepte.csv und hitliste.csv aus dem Ordner "Rezepte".
 *
 * @author Tatsch
 * @version (a version number or a date)
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
     * Importiert die Dateien rezepte.csv und hitliste.csv aus dem Ordner
     * "Rezepte". Auf Basis dieser Dateien wird die SpeisenVerwaltung für den
     * SpeiseplanErsteller erstellt.
     *
     * Die Datei mit den Rezepten muss den namen "rezepte.csv" haben und im
     * Ordner "Rezepte" liegen.
     *
     * Die Datei mit den beliebtesten Rezepten muss den Namen "hitliste.csv"
     * haben und im Ordner "Rezepte" liegen.
     *
     * Sind diese Dateien nicht vorhanden wird eine FileNotFoundException
     * geworfen. Anschließend wird das Programm mit der Fehlermeldung beendet.
     *
     * @return Gibt die erstellte RezepteListe gekapselt in der
     * Speisenverwaltung zurück.
     */
    public void importFiles() {
        leseDateien();
        fuegeSpeisenVonHitlisteInSpeisenverwaltungEin();
        fuegeZutatenZuSpeisenAusRezepteDateiHinzu();
        loescheUnvollstaendigeSpeisen();
        sortiereZutatenNachKategorie();
    }

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

    private void leseDateien() {
        hitliste = leseDatei(dateiOrdner + "/" + "hitliste.csv");
        rezepte = leseDatei(dateiOrdner + "/" + "rezepte.csv");
    }

    protected Datei leseDatei(String dateiPfad) {
        return new DateiLeser(dateiPfad).leseDatei();
    }

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
