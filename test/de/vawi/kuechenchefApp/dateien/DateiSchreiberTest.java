
package de.vawi.kuechenchefApp.dateien;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Tobias
 */
public class DateiSchreiberTest {
   
    private String inhalt;
    private TestableDateiManager manager;
    
    @Test
    public void testSchreibeInDatei() {
        
        Datei datei = new TestbareDatei();
        DateiSchreiber schreiber = new TestbarerDateiSchreiber(datei);
        schreiber.schreibe("Hallo ich bin eine Datei!");
        
        assertEquals("Hallo ich bin eine Datei!\n", manager.getDateiInhalt());
    }
    
    class TestbarerDateiSchreiber extends DateiSchreiber{
        

        public TestbarerDateiSchreiber(Datei dateiName) {
            super(dateiName);
        }

        @Override
        protected DateiManager erzeugeDatei() {
            manager =  new TestableDateiManager("test");
            return manager;
        }
        
    }
    
    
    class TestbareDatei implements Datei{
        
        @Override
        public String getDateinameMitPfad() {
            return "testdatei";
        }   
    }
}