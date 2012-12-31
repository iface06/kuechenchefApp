

package de.vawi.kuechenchefApp.nahrungsmittel;

import java.util.HashSet;
import java.util.Set;


public class NahrungsmittelVerwaltung {

    public static NahrungsmittelVerwaltung INSTANZ;
    
    private Set<Nahrungsmittel> nahrungsmittels = new HashSet<>();

    public Nahrungsmittel findeDurchName(String kartoffeln) {
        for (Nahrungsmittel nahrungsmittel1 : nahrungsmittels) {
            if(nahrungsmittel1.getName().contains(kartoffeln)){
                return nahrungsmittel1;
            }
        }
        return null;
    }

    public void fuegeHinzu(Nahrungsmittel nahrungsmittel) {
        nahrungsmittels.add(nahrungsmittel);
    }
    
    public static NahrungsmittelVerwaltung getInstanz(){
        if(INSTANZ == null){
            INSTANZ = new NahrungsmittelVerwaltung();
        }
        return INSTANZ;
        
    }
    
    
    
}
