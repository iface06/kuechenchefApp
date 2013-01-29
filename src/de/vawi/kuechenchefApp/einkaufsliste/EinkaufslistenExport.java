
package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.export.DateiExport;
import de.vawi.kuechenchefApp.dateien.DateiSchreiber;
import de.vawi.kuechenchefApp.lieferanten.Lieferant;
import java.util.*;


/**
 * Diese Klasse ist für den Export der Einkaufsliste verantwortlich.
 * 
 * @author Lepping 
 * @version (a version number or a date)
 */
public class EinkaufslistenExport extends DateiExport<Einkaufsliste>
{
    
    /**
     * Exportiert die Einkaufsliste in eine Datei.
     * Dateiname: Einkaufsliste_[Lieferantenname].txt;
     * 
     * @param  einkaufsliste    Einkaufsliste
     */
    public void export(Einkaufsliste einkaufsliste){
        
        
        Set<Lieferant> lieferanten = einkaufsliste.holeLieferanten();
        EinkaufslisteDrucker drucker = new EinkaufslisteDrucker();
        for (Lieferant lieferant : lieferanten) {
            String einkaufslisteZuLieferant  = erstelleEinkaufslisteAlsString(einkaufsliste, lieferant, drucker);
            schreibeEinkaufslisteInDatei(lieferant, einkaufslisteZuLieferant);
        }
    }

    private String erstelleEinkaufslisteAlsString(Einkaufsliste einkaufsliste, Lieferant lieferant, EinkaufslisteDrucker drucker) {
        List<EinkaufslistenPosition> positionen = einkaufsliste.holeEinkaufslistenpositionenVonLieferant(lieferant);
        String einkaufslisteZuLieferant  = drucker.drucke(lieferant, positionen);
        return einkaufslisteZuLieferant;
    }

    private void schreibeEinkaufslisteInDatei(Lieferant lieferant, String einkaufslisteZuLieferant) {
        DateiSchreiber schreiber = oeffneEinkaufslisteDatei(lieferant);
        schreiber.schreibe(einkaufslisteZuLieferant);
    }

    protected DateiSchreiber oeffneEinkaufslisteDatei(Lieferant lieferant) {
        DateiSchreiber schreiber = new DateiSchreiber(new EinkaufslistenDatei(lieferant.getName()));
        return schreiber;
    }

    
}
