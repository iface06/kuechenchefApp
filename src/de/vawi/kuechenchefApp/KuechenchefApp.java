package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenErsteller;
import de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenExport;
import de.vawi.kuechenchefApp.kostenaufstellung.KostenaufstellungErsteller;
import de.vawi.kuechenchefApp.kostenaufstellung.KostenaufstellungExport;
import de.vawi.kuechenchefApp.lieferanten.Lieferant;
import de.vawi.kuechenchefApp.lieferanten.PreisListeImport;
import de.vawi.kuechenchefApp.lieferanten.PreisListenOrdnerSuche;
import de.vawi.kuechenchefApp.rezepte.SpeisenImport;
import de.vawi.kuechenchefApp.rezepte.SpeisenVerwaltung;
import de.vawi.kuechenchefApp.rezepte.SpeiseplanExport;
import de.vawi.kuechenchefApp.speiseplan.SpeiseplanErsteller;
import java.util.List;


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
        new SpeisenImport().importFiles();
        PreisListeImport.LieferantenListe();
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
