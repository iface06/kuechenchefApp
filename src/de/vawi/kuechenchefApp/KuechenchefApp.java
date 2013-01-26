package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.dateien.DateiOrdnerSuche;
import de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenExport;
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
public class KuechenchefApp
{
    

    /**
     * Diese Methode ist der Einstiegspunkt der Anwendung KüchenchefApp. 
     * 
     * @param args Keine Argumente bislang notwendig!
     */
    public static void main(String[] args) throws Exception
    {
        String dateiOrdner = new DateiOrdnerSuche().dateiOrdnerSuche();
        new SpeisenImport(dateiOrdner).importFiles();
        new PreisListenImport(dateiOrdner).importFiles();
        
        ProzessSteuerung prozess = new ProzessSteuerung();

        prozess.setSpeiseplanErsteller(new SpeiseplanErsteller());
        //TODO prozess.setEinkaufslistenErsteller(new EinkaufslistenErsteller(lieferanten));

        prozess.setKostenaufstellungErsteller(new KostenaufstellungErsteller());
        prozess.start();
        
        new SpeiseplanExport().export(prozess.getSpeiseplaene());
        new EinkaufslistenExport().export(prozess.getEinkaufsliste());
        new KostenaufstellungExport().export(prozess.getKostenaufstellung());
    }
    
    
}
