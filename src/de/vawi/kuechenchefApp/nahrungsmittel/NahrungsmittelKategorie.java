package de.vawi.kuechenchefApp.nahrungsmittel;


/**
 * Mögliche Kategorien die einem Nahrungsmittel zugeordnet sein können.
 * 
 * @author Struebe
 * @version 30.12.2012
 */
public enum NahrungsmittelKategorie {
    FLEISCH("m"), FISCH("f"), VEGETARISCH("");
    
    private String abkuerzung;

    private NahrungsmittelKategorie(String abkuerzung) {
        this.abkuerzung = abkuerzung;
    }

    public String getAbkuerzung() {
        return abkuerzung;
    }
    
    public static NahrungsmittelKategorie nachAbkuerzung(String abkuerzung){
        for (NahrungsmittelKategorie kategorie : values()) {
            if(kategorie.getAbkuerzung().equals(abkuerzung)){
                return kategorie;
            }
        }
        return NahrungsmittelKategorie.VEGETARISCH;
    }
}
