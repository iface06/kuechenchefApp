package de.vawi.kuechenchefApp.kostenaufstellung;


public class KostenUebersichtDrucker {
    
    
    private String ausdruck = new String();
    private KostenUebersicht uebersicht;
    
    public String drucke(KostenUebersicht uebersicht){
        this.uebersicht = uebersicht;
        ausdruck += "Kostenübersicht\n\n";
        
        
        
        return ausdruck;
    }
    

}
