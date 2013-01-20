

package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import java.util.*;


public class DummyLieferantenVerwaltungErsteller {
    
    private LieferantenVerwaltung verwaltung = LieferantenVerwaltung.getInstanz();
    
    public LieferantenVerwaltung erstelle(){
        return verwaltung;
    }

    public DummyLieferantenVerwaltungErsteller hinzufuegen(PreisListenPosition position){
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(position));
        return this;
    }
    
    public DummyLieferantenVerwaltungErsteller kartoffeln(){
        PreisListenPosition kartoffelAngebotA = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Einheit.GRAMM).lieferant("Müller", DummyPreisListenPositionErsteller.GROSSHAENDLER).angebot(5.0, 1000.0, 10000).erstelle();
        PreisListenPosition kartoffelAngebotB = new DummyPreisListenPositionErsteller().nahrungsmittel("Kartoffeln", Einheit.GRAMM).lieferant("Meier", DummyPreisListenPositionErsteller.GROSSHAENDLER).angebot(10.0, 1000.0, 5000).erstelle();
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(kartoffelAngebotA, kartoffelAngebotB));
        return this;
        
    }
    
    public DummyLieferantenVerwaltungErsteller moehren(){
        PreisListenPosition moehrenAngebot = new DummyPreisListenPositionErsteller().nahrungsmittel("Möhren", Einheit.STUECK).lieferant("Meier", DummyPreisListenPositionErsteller.BAUER).angebot(1.0, 500.0, 300).erstelle();
        verwaltung.hinzufuegenPreisListenPosition(Arrays.asList(moehrenAngebot));
        return this;
        
    }
    

}
