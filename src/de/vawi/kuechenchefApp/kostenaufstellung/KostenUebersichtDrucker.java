package de.vawi.kuechenchefApp.kostenaufstellung;

import de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenPosition;
import de.vawi.kuechenchefApp.export.AsciiTable;
import java.util.List;


public class KostenUebersichtDrucker {
    
    
    private String ausdruck = new String();
    private KostenUebersicht uebersicht;
    
    public String drucke(KostenUebersicht uebersicht){
        
        ausdruck += "Kostenübersicht\n\n";
        
        ausdruck += erstelleKostenaufstellungProLieferant(uebersicht);
        
        ausdruck += "\n\n";
        
        ausdruck += "Gesamtkosten: " + uebersicht.getGesamtKosten();
        ausdruck += "\n";
        ausdruck += "Lieferkosten: " + uebersicht.getGesamtKosten();
        
        
        return ausdruck;
    }

    private String erstelleKostenaufstellungProLieferant(KostenUebersicht uebersicht) {
        String kostenaufstellung = new String();
        for (Kostenaufstellung kosten : uebersicht.getKostenaufstellungenProLieferant()) {
            kostenaufstellung += "Kosten zur Bestellung bei Lieferant: " + kosten.getLieferant().getName();
            kostenaufstellung += "\n\n";
            kostenaufstellung += druckePositionenZurBestellung(kosten.getEinkaufslistenPositionsListe());
            kostenaufstellung += "\n\n";
        }
        
        return kostenaufstellung;
    }

    private String druckePositionenZurBestellung(List<EinkaufslistenPosition> einkaufslistenPositionsListe) {
        AsciiTable positionen = new AsciiTable();
        positionen.row().cell("Nahrungsmittel").cell("Menge (Gebinde)").cell("Preis €");
        for (EinkaufslistenPosition position : einkaufslistenPositionsListe) {
            positionen.row().cell(position.getName()).cell(String.valueOf(position.getMenge())).cell(String.valueOf(position.getPreis()));
        }
        
        return positionen.writeTable();
    }
    

}
