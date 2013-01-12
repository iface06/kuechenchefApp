package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.DateiExport;
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
   }
}
