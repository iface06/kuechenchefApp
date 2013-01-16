package de.vawi.kuechenchefApp.nahrungsmittel;


/**
 * Mögliche Kategorien die einem Nahrungsmittel zugeordnet sein können.
 * 
 * @author Struebe
 * @version 30.12.2012
 */
public enum SpeisenUndNahrungsmittelKategorie{
    FLEISCH("m"), FISCH("f"), VEGETARISCH("");
    
    private String abkuerzung;

    private SpeisenUndNahrungsmittelKategorie(String abkuerzung) {
        this.abkuerzung = abkuerzung;
    }

    public String getAbkuerzung() {
        return abkuerzung;
    }
    
    public static SpeisenUndNahrungsmittelKategorie nachAbkuerzung(String abkuerzung){
        for (SpeisenUndNahrungsmittelKategorie kategorie : values()) {
            if(kategorie.getAbkuerzung().equals(abkuerzung)){
                return kategorie;
            }
        }
        return SpeisenUndNahrungsmittelKategorie.VEGETARISCH;
    }
}
