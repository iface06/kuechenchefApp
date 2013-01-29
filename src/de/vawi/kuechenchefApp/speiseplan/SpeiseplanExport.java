package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.export.DateiExport;
import de.vawi.kuechenchefApp.dateien.Datei;
import de.vawi.kuechenchefApp.dateien.DateiSchreiber;
import de.vawi.kuechenchefApp.speiseplan.Speiseplan;
import java.util.*;
/**
 * Diese Klasse ist für den Export der Speisepläne verantwortlich.
 * 
 * @author Lepping 
 * @version (a version number or a date)
 */
public class SpeiseplanExport extends DateiExport<List<Speiseplan>>
{

   /**
    * Exportiert jeden Speiseplan in eine seperate Datei.
    * Name der Dateien: Speiseplan_[KANTINE].txt
    * Beispiel: Speiseplan_Essen.txt;
    * 
    * @param  exportants    Liste von Speisepläne
   */
   public void export(List<Speiseplan> exportants){
       SpeiseplanDrucker drucker = new SpeiseplanDrucker();
       for (Speiseplan speiseplan : exportants) {
           String plan = drucker.drucke(speiseplan);
           DateiSchreiber schreiber = erstelleSchreiberFuer(speiseplan);
           schreiber.schreibe(plan);
       }
   }

    protected DateiSchreiber erstelleSchreiberFuer(Speiseplan speiseplan) {
        return new DateiSchreiber(new SpeiseplanDatei(speiseplan.getKantine()));
    }
}
