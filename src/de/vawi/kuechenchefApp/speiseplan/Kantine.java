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
    private double anteilBeliebtestesGericht = 0.5;
    private double anteilZweitbeliebtestesGerichte = 0.25;
    private double anteilDrittbeliebtestesGerichte = 0.25;
    private double faktorSicherheitsbstand = 1.5;
    
    Kantine(int mitarbeiterAnzahl){
        this.mitarbeiterAnzahl = mitarbeiterAnzahl;
    }
    
    /**
     * @return     Anzahl der Mitarbeiter der Kantine
     */
    public int getMitarbeiterAnzahl(){
        return this.mitarbeiterAnzahl;
    }

    public int berechneAnzahlFuerBeliebtestesGericht() {
        double anzahl = rundeAnzahlAufGanzeZahl(berechneSicherheitsbestand() * anteilBeliebtestesGericht);
        return parseAnzahlZuInteger(anzahl);
    }
    public int berechneAnzahlFuerZweitBeliebtestesGericht() {
        double anzahl = rundeAnzahlAufGanzeZahl(berechneSicherheitsbestand() * anteilZweitbeliebtestesGerichte);
        return parseAnzahlZuInteger(anzahl);
    }
    public int berechneAnzahlFuerDrittBeliebtestesGericht() {
        double anzahl = rundeAnzahlAufGanzeZahl(berechneSicherheitsbestand() * anteilDrittbeliebtestesGerichte);
        return parseAnzahlZuInteger(anzahl);
    }

    private double berechneSicherheitsbestand() {
        return mitarbeiterAnzahl * faktorSicherheitsbstand;
    }
    
    private int parseAnzahlZuInteger(double ergebnis){
        return Double.valueOf(ergebnis).intValue();
    }

    private double rundeAnzahlAufGanzeZahl(double anzahl) {
        return Math.ceil(anzahl);
    }
}
