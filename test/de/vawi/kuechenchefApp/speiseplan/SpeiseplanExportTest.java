
package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.dateien.*;
import de.vawi.kuechenchefApp.speisen.*;
import java.io.IOException;
import java.util.*;
import org.junit.Test;

/**
 *
 * @author Tatsch
 */
public class SpeiseplanExportTest {
    
    @Test
    public void testExportSpeiseplanInDatei(){
        Speise surfAndTurf = new DummySpeise().name("Surf and Turf").beliebtheit(1).mitZutat(DummyZutat.steaks()).mitZutat(DummyZutat.garnelen()).erstelle();
        DummySpeiseplan dummy = new DummySpeiseplan();
        dummy.fuerKantine(Kantine.ESSEN);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        dummy.plusTag(surfAndTurf, surfAndTurf, surfAndTurf);
        Speiseplan plan = dummy.erstelle();
        SpeiseplanExport exporter = new TetsableSpieseplanExport();
        exporter.export(Arrays.asList(plan));
    }
    
    class TetsableSpieseplanExport extends SpeiseplanExport{

        List<String> inhalt = new ArrayList<>();
        @Override
        protected DateiSchreiber erstelleSchreiberFuer(Speiseplan speiseplan) {
            return new TestableDateiSchreiber(new Datei() {

                @Override
                public String getDateinameMitPfad() {
                    return "speiseplan.txt";
                }

                @Override
                public Iterator<String> iterator() {
                    return inhalt.iterator();
                }
            });
        }
    }
    
    class TestableDateiSchreiber extends DateiSchreiber{

        public TestableDateiSchreiber(Datei dateiName) {
            super(dateiName);
        }
        
        @Override
        protected DateiSchreiberManager erzeugeDatei() {
            return new DateiSchreiberManager() {

                @Override
                public void openOutFile() throws IOException {
                    
                }

                @Override
                public void writeLine(String in_str) {
                    System.out.println(in_str);
                }

                @Override
                public void closeOutFile() {
                    
                }
            };
        }
    }
    
}


