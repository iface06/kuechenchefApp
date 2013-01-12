package de.vawi.kuechenchefApp.nahrungsmittel;


/**
 * Mögliche Einheiten aus den Importdateien
 * 
 * @author Struebe
 * @version 30.12.2012
 */
public enum Einheit {
   STUECK(""), LITER("l"), GRAMM("g");
   
   private String abkuerzung;

    private Einheit(String abkuerzung) {
        this.abkuerzung = abkuerzung;
    }

    public String getAbkuerzung() {
        return abkuerzung;
    }
    
    public static Einheit nachAbkuerzung(String abkuerzung){
        for (Einheit einheit : values()) {
            if(einheit.getAbkuerzung().equals(abkuerzung)){
                return einheit;
            }
        }
        return Einheit.STUECK;
    }
   
   
}
