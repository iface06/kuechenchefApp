package de.vawi.kuechenchefApp.kostenaufstellung;

import de.vawi.kuechenchefApp.einkaufsliste.Einkaufsliste;
import de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenPosition;
import de.vawi.kuechenchefApp.lieferanten.Lieferant;
import de.vawi.kuechenchefApp.lieferanten.PreisListenPosition;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import java.util.ArrayList;
import java.util.List;


/**
 * Erstellt anhand einer Einkaufsliste, die entsprechende Kostenaufstellung.
 * @author Struebe 
 * @version 16.01.2013
 */
public class KostenaufstellungErsteller
{
    private Einkaufsliste liste;  
    private List<Einkaufsliste> einkaufsListenPositionen = new ArrayList<>();
    
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

    public double berechneGesamtKosten (){
        double gesamtKosten = 0.0;
        for (Lieferant lieferant : zaehleLieferantenAuf()){
            gesamtKosten += berechneGesamtKostenProLieferant(lieferant);
        }
        return gesamtKosten;
    }
    
    
    public double berechneGesamtKostenProLieferant (Lieferant lieferant){
        double einkaufsKosten = 0.0;
        double lieferKosten = 0.0;
        List<EinkaufslistenPosition> lieferantenFilter = filtereNachLieferanten(lieferant);
        for (EinkaufslistenPosition position : lieferantenFilter){
            einkaufsKosten += position.getPreis();
        }
        lieferKosten = lieferant.berechneLieferkosten(einkaufsKosten);        
        return einkaufsKosten + lieferKosten;
    }
    
    List<EinkaufslistenPosition> filtereNachLieferanten(Lieferant lieferant) {
        List<EinkaufslistenPosition> lieferantenFilter = new ArrayList<>();
        for (EinkaufslistenPosition position : liste.getPositionen()) {
            if (position.getLieferant().equals(lieferant)) {
                lieferantenFilter.add(position);
            }
        }
        return lieferantenFilter;
    }
    
    List<Lieferant> zaehleLieferantenAuf() {
        List<Lieferant> lieferantenAuflistung = new ArrayList<>();
        for (EinkaufslistenPosition position : liste.getPositionen()) {
            Lieferant lieferant = position.getLieferant();
            if (!lieferantenAuflistung.contains(lieferant)) {
                lieferantenAuflistung.add(lieferant);
            }
        }

        return lieferantenAuflistung;
    }
    }