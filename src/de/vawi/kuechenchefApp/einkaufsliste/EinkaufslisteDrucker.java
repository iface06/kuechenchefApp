package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.export.AsciiTable;

/**
 * 
 * @author Matthias
 */
public class EinkaufslisteDrucker {
    
    private String ausdruck = new String();
    
    String drucke(Einkaufsliste einkaufslistejelieferant) {
        ausdruck += "Einkaufsliste fuer Lieferanten X";
        ausdruck += "\n\n";
        ausdruck += einkaufsListe(einkaufslistejelieferant);
        
        return ausdruck;
    }
    
        private String einkaufsListe(Einkaufsliste einkaufsListe){
        String tabelle = new String();
            //tabelle += einkaufslisteFuerEinenLieferanten(einkaufsListe);
            tabelle += "\n\n\n";

        return tabelle;
        }
        
    /*/    
    private String einkaufslisteFuerEinenLieferanten(Einkaufsliste einkaufsListe) {
        AsciiTable tabelle = new AsciiTable();
        Row headRow = tabelle.row();
        Row variante1 = tabelle.row();
        Row variante2 = tabelle.row();
        Row variante3 = tabelle.row();
        Iterator<Tag> tage = plan.iterator();
        int anzahlTage = 1;
        while(tage.hasNext() && anzahlTage <= periode.getAnzahlTageProWoche()){
            Tag tag = tage.next();
            headRow.cell("Tag " + anzahlTage);
            variante1.cell(tag.getBeliebtesteSpeise().getName());
            variante2.cell(tag.getZweitbeliebtesteSpeise().getName());
            variante3.cell(tag.getDrittbeliebtesteSpeise().getName());
            anzahlTage++;
        }
        return tabelle.writeTable();
    }
     /*/   
}
