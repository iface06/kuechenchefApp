/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.PlanungsPeriode;
import de.vawi.kuechenchefApp.export.AsciiTable;
import de.vawi.kuechenchefApp.export.Row;
import java.util.Iterator;

/**
 *
 * @author Tatsch
 */
public class SpeiseplanDrucker {

    private String ausdruck = new String();
    private PlanungsPeriode periode = new PlanungsPeriode();
    
    String drucke(Speiseplan plan) {
        ausdruck += "Speiseplan";
        ausdruck += "\n\n";
        ausdruck += speiseplanTabellen(plan);
        
        return ausdruck;
    }
    
    private String speiseplanTabellen(Speiseplan plan){
        int woche = 1;
        String tabellen = new String();
        while(woche <= periode.getAnzahlWochen()){
            tabellen += ueberschriftWoche(woche);
            tabellen += speiseplanFuerEineWoche(plan);
            tabellen += "\n\n\n";
            woche++;
        }
        return tabellen;
    }
    
    private String ueberschriftWoche(int woche) {
        String ueberschrift = "Woche " + woche;
        ueberschrift += "\n\n";
        return ueberschrift;
    }

    private String speiseplanFuerEineWoche(Speiseplan plan) {
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
}
