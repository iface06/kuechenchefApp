package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import java.util.*;

/**
 * Diese Klasse verwaltet die importierten Lieferanten.
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class LieferantenVerwaltung {

    private static LieferantenVerwaltung INSTANCE;
    
    private List<PreisListenPosition> preisListenPositionen = new ArrayList<>();

    
    LieferantenVerwaltung() {
    }
    
    public static LieferantenVerwaltung getInstance(){
        if(INSTANCE == null){
            INSTANCE = new LieferantenVerwaltung();
        }
        
        return INSTANCE;
    }

    void hinzufuegenPreisListenPosition(List<PreisListenPosition> positionen) {
        preisListenPositionen.addAll(positionen);
    }

    List<PreisListenPosition> findeDurchNahrungsmittel(Nahrungsmittel nahrungsmittel) {
        List<PreisListenPosition> positionen = new ArrayList<>();
        for (PreisListenPosition position : preisListenPositionen) {
            if(position.getNahrungsmittel().equals(nahrungsmittel)){
                positionen.add(position);
            }
        }
        
        return positionen;
    }
    
    
    
}
