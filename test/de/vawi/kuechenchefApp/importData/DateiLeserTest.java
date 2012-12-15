package de.vawi.kuechenchefApp.importData;


import de.vawi.kuechenchefApp.rezepte.RezepteDatei;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DateiLeserTest {
    
    String dateiInhalt;
    String log = new String();
    
    @Before
    public void erstelleTestDateiKontent(){
        dateiInhalt = new String();
        dateiInhalt += "1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11\r\n";
        dateiInhalt += "1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11";
    }
    
    @Test
    public void testeDateiEinlesen() {
        DateiLeser leser = new TestableDateiLeser();
        leser.setDatei(TestableDatei.TESTDATEI);
        List<String> inhalt = leser.leseDatei();
    
        assertTrue(!inhalt.isEmpty());
        assertEquals(2, inhalt.size());
    }
    
    class TestableDateiLeser extends DateiLeser{

        @Override
        protected DateiManager erstelleDateiManager() {
            return new TestableDateiManager(datei.getDateinameMitPfad());
        }

        @Override
        protected void behandleFehlerfall(IOException ex) {
            log = ex.getMessage();
        }
        
    }
    
    enum TestableDatei implements Datei{
        TESTDATEI("testDateiOhneBedeutung");

        private String pathname;

        private TestableDatei(String pathname) {
            this.pathname = pathname;
        }
        
        
        
        @Override
        public String getDateinameMitPfad() {
            return pathname;
        }
    }
    
     class TestableDateiManager extends DateiManager{

        public TestableDateiManager(String in_name) {
            super(in_name);
        }
        
        @Override
        protected BufferedReader createBufferedReader() throws FileNotFoundException {
            return new BufferedReader(new StringReader(dateiInhalt));
        }
    }
}
