package de.vawi.kuechenchefApp;

import java.util.*;
/**
 * Diese Klasse verwaltet die importierten Lieferanten und ihre Preislisten.
 * 
 * @author  Struebe
 * @version (a version number or a date)
 */
public class LieferantenVerwaltung{

    private List<Lieferant> lieferanten;

    public LieferantenVerwaltung(List<Lieferant> lieferanten){
        this.lieferanten = lieferanten;
    }
    
    /**
     * @return     Lieferanten aus den Preislisten-Dateien.
     */
    public List<Lieferant> getLieferanten() {
        return this.lieferanten;
    }

    /**
     * @param      nahrungsmittel   Nahrungsmittel zu dem man alle PreisListenPositionen haben möchte
     * @return     Gibt alle PreisListenPositionen zu einem Nahrungsmittel von allen Lieferanten zurück
     */
    public List<PreisListenPosition> findPreisListePositionBy(Nahrungsmittel nahrungsmittel){
        return new ArrayList<PreisListenPosition>();
    }
    
      /**
     * @param      lieferant    Lieferant dessen Preisliste man haben möchte
     * @return     Gibt alle PreisListenPositionen zu einem Lieferanten zurück
     */
    public List<PreisListenPosition> findPreisListePositionBy(Lieferant lieferant){
        return new ArrayList<PreisListenPosition>();
   }
}
