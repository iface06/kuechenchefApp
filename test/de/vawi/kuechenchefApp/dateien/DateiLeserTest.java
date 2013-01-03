package de.vawi.kuechenchefApp.dateien;


import java.io.IOException;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class DateiLeserTest {
    
    String zeilen;
    String log = new String();
    
    @Before
    public void erstelleTestDateiKontent(){
        zeilen = new String();
        zeilen += "1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11\r\n";
        zeilen += "1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11";
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
            TestableDateiManager manager = new TestableDateiManager(datei.getDateinameMitPfad());
            manager.setDateiInhalt(zeilen);
            return manager;
        }

        @Override
        protected void behandelFehlerfall(IOException ex) {
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

        @Override
        public Iterator<String> iterator() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        
    }
    
     
}
