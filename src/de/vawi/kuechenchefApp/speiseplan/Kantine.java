package de.vawi.kuechenchefApp.speiseplan;


/**
 * Enumeration class Kantine - write a description of the enum class here
 * 
 * @author Beer
 * @version (version number or date here)
 */
public enum Kantine
{
    MUELHEIM_AN_DER_RUHR(300), ESSEN(500);
    
    private int mitarbeiterAnzahl;
    
    Kantine(int mitarbeiterAnzahl){
        this.mitarbeiterAnzahl = mitarbeiterAnzahl;
    }
    
    /**
     * @return     Anzahl der Mitarbeiter der Kantine
     */
    public int getMitarbeiterAnzahl(){
        return this.mitarbeiterAnzahl;
    }
}
