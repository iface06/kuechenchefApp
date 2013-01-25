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
       List<Kostenaufstellung> kostenaufstellungNachLieferant = kostenaufstellungNachLieferant();
        for (Kostenaufstellung kostenaufstellung : kostenaufstellungNachLieferant){
            gesamtKosten += kostenaufstellung.berechneGesamtKostenProLieferant();
        }
        return gesamtKosten;
    }
    
       
    public Kostenaufstellung filtereNachLieferanten(Lieferant lieferant) {
        List<EinkaufslistenPosition> lieferantenFilter = new ArrayList<>();
        for (EinkaufslistenPosition position : liste.getPositionen()) {
            if (position.getLieferant().equals(lieferant)) {
                lieferantenFilter.add(position);
            }
        }
        Kostenaufstellung kostenaufstellungProLieferanten = new Kostenaufstellung();
        kostenaufstellungProLieferanten.setLieferant(lieferant);
        kostenaufstellungProLieferanten.setEinkaufslistenPositionsListe(lieferantenFilter);
        return kostenaufstellungProLieferanten;
    }
    
    public List<Kostenaufstellung> kostenaufstellungNachLieferant(){
        List<Kostenaufstellung> kostenaufstellungNachLieferant = new ArrayList<>();
        List<Lieferant> alleBeteiligtenLieferanten = zaehleLieferantenAuf();
        int j = alleBeteiligtenLieferanten.size();
        for (int i = 0; i < j; i++ ){
            Lieferant lieferant = alleBeteiligtenLieferanten.get(i);
            Kostenaufstellung temporäreSpeicherverschwendendeKostenaufstellungDamitSonjaEsVersteht = filtereNachLieferanten(lieferant);
            kostenaufstellungNachLieferant.add(temporäreSpeicherverschwendendeKostenaufstellungDamitSonjaEsVersteht);
    }
        return kostenaufstellungNachLieferant;
    }
    
    private List<Lieferant> zaehleLieferantenAuf() {
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