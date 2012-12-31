

package de.vawi.kuechenchefApp.nahrungsmittel;

import de.vawi.kuechenchefApp.dateien.Datei;
import de.vawi.kuechenchefApp.rezepte.RezepteDatei;


public class NahrungsmittelImport {

    
    private  Datei rezepteDatei;
    private NahrungsmittelVerwaltung nahrungsmittels = NahrungsmittelVerwaltung.getInstanz();
    
    public void importFile(){
        fuegeNahrungsmittelAusRezepteDateiInNahrungsmittelVerwaltungEin();
    }

    protected void setRezepteDatei(Datei rezpeteDatei) {
        this.rezepteDatei = rezpeteDatei;
    }

    private void fuegeNahrungsmittelAusRezepteDateiInNahrungsmittelVerwaltungEin() {
        NahrungsmittelErsteller ersteller = new NahrungsmittelErsteller();
        for (String zeile : rezepteDatei) {
            Nahrungsmittel nahrungsmittel = ersteller.erstelle(zeile);
            nahrungsmittels.fuegeHinzu(nahrungsmittel);   
        }
    }
    
    
}
