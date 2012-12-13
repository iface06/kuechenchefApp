package de.vawi.kuechenchefApp;


/**
 * Erstellt anhand einer Einkaufsliste, die entsprechende Kostenaufstellung.
 * @author Struebe 
 * @version (a version number or a date)
 */
public class KostenaufstellungErsteller
{
    private Einkaufsliste liste;  

    /**
     * Setzt die Einkaufsliste. Diese ist die Basis für die Berechnung der Kostenaufstellung.
     * 
     * @param liste     Die zu setzende Einkaufsliste.
     */
    public void setEinkaufsliste(Einkaufsliste liste){
        this.liste = liste;
    }

    /**
     * Erzeugt die Kostenaufstellung. 
     * Die Kosten für jedes Nahrungsmittel werden nach Nahrungsmittel kumuliert. 
     * Die Transportkosten werden kumuliert dargestellt.
     * 
     * @return     erzeugte Kostenaufstellung 
     */
    public Kostenaufstellung erzeuge()
    {
        return new Kostenaufstellung();
    }
}
