package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.dateien.DateiOrdnerSuche;
import de.vawi.kuechenchefApp.einkaufsliste.*;
import de.vawi.kuechenchefApp.kostenaufstellung.KostenaufstellungErsteller;
import de.vawi.kuechenchefApp.kostenaufstellung.KostenaufstellungExport;
import de.vawi.kuechenchefApp.lieferanten.PreisListenImport;
import de.vawi.kuechenchefApp.speisen.SpeisenImport;
import de.vawi.kuechenchefApp.speiseplan.SpeiseplanErsteller;
import de.vawi.kuechenchefApp.speiseplan.SpeiseplanExport;


/**
 * Diese Klasse ist der Einstiegspunkt der Anwendung KüchenchefApp.
 * 
 * @author Tatsch 
 * @version Beta
 * 
 * 
 */
public class KuechenchefApp {
    
    /**
     * Diese Methode ist der Einstiegspunkt der Anwendung KüchenchefApp. 
     * 
     * @param args Keine Argumente bislang notwendig!
     */
    public static void main(String[] args) throws Exception {
//        String dateiOrdner = importDateienOrdnerAbfragen();
        importiereDateien("C:\\Users\\Max\\Dropbox\\WS-12-Java-Gruppe\\TL2\\Beispiel Eingabedateien");

        ProzessSteuerung steuerung = erstelleProzessSteuerung();
        steuerung.start();
        
        exportiereErgebnisse(steuerung);
    }
    
    private static String importDateienOrdnerAbfragen() {
        String dateiOrdner = new DateiOrdnerSuche().dateiOrdnerSuche();
        return dateiOrdner;
    }

    private static void importiereDateien(String dateiOrdner) {
        importierePreisListen(dateiOrdner);
        importiereSpeisen(dateiOrdner);
    }
    
    private static void importiereSpeisen(String dateiOrdner) {
        new SpeisenImport(dateiOrdner).importFiles();
    }

    private static void importierePreisListen(String dateiOrdner) {
        new PreisListenImport(dateiOrdner).importFiles();
    }

    private static ProzessSteuerung erstelleProzessSteuerung() {
        ProzessSteuerung prozess = new ProzessSteuerung();
        prozess.setSpeiseplanErsteller(new SpeiseplanErsteller());
        prozess.setEinkaufslistenErsteller(new EinkaufslistenErsteller());
        prozess.setKostenaufstellungErsteller(new KostenaufstellungErsteller());
        return prozess;
    }

    private static void exportiereErgebnisse(ProzessSteuerung steuerung) {
        new SpeiseplanExport().export(steuerung.getSpeiseplaene());
        new EinkaufslistenExport().export(steuerung.getEinkaufsliste());
        new KostenaufstellungExport().export(steuerung.getKostenUbersicht());
    }
}
