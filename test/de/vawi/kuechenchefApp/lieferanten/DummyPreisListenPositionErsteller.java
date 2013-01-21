

package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.nahrungsmittel.*;


public class DummyPreisListenPositionErsteller {
    
    public static final int GROSSHAENDLER = 1;
    public static final int BAUER = 2;
    
    private PreisListenPosition position = new PreisListenPosition();

    public DummyPreisListenPositionErsteller() {
        position.setNahrungsmittel(new Nahrungsmittel());
        
    }
    
    public DummyPreisListenPositionErsteller nahrungsmittel(String name, Einheit einheit){
        position.getNahrungsmittel().setName(name);
        position.getNahrungsmittel().setEinheit(einheit);
        return this;
    }
    
    public DummyPreisListenPositionErsteller lieferant(String name, int typ){
        Lieferant lieferant = erstelleLieferant(typ);
        lieferant.setName(name);
        lieferant.setLieferKostenFaktor(1.1);
        position.setLieferant(lieferant);
        return this;
    }
    
    public DummyPreisListenPositionErsteller angebot(double preis, double gebinde, int vorat){
        position.setGebindeGroesse(gebinde);
        position.setVorratsBestand(vorat);
        position.setPreis(preis);
        return this;
    }
    
    public PreisListenPosition erstelle(){
        return position;
    }

    private Lieferant erstelleLieferant(int typ) {
        if(typ == GROSSHAENDLER){
            return new Grosshaendler();
        } else {
            return new Bauer();
        }
    }
}
