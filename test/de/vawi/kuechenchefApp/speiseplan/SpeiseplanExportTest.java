/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.IntegrationTest;
import de.vawi.kuechenchefApp.dateien.Datei;
import de.vawi.kuechenchefApp.dateien.DateiSchreiber;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Categories;

/**
 *
 * @author Tatsch
 */
@Categories(IntegrationTest.class)
public class SpeiseplanExportTest {

    private List<String> dateien = new ArrayList<>();
    
    @Test
    public void testExportSpeiseplaene() {
        
        
        
    }
    
    class TestableExport extends SpeiseplanExport{

        @Override
        protected DateiSchreiber erstelleSchreiberFuer(Speiseplan speiseplan) {
            return super.erstelleSchreiberFuer(speiseplan);
        }   
    }
    
    class TestableSchreiber extends DateiSchreiber{

        public TestableSchreiber(Datei dateiName) {
            super(dateiName);
        }

        @Override
        public void schreibe(String inhalt) {
            dateien.add(inhalt);
        }

        @Override
        protected DateiMangaer erzeugeDatei() {
            return null;
        }
    }
}
