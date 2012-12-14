/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.Datei;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateiTest {
    

    @Test
    public void testReadFile() throws IOException {
        System.out.println("eof");
        Datei datei = new TestableDatei("testFile");
        datei.openInFile();
        
        
        List<String> lines = new ArrayList<>();
        while(!datei.eof()){
            String line = datei.readLine();
            lines.add(line);
        }
        
        datei.closeInFile();
        
        assertEquals(2, lines.size());
        assertEquals("1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11", lines.get(0));
        assertNull(lines.get(1));
    }
    
    class TestableDatei extends Datei{

        public TestableDatei(String in_name) {
            super(in_name);
        }
        
        @Override
        protected BufferedReader createBufferedReader() throws FileNotFoundException {
            return new BufferedReader(new StringReader("1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11"));
        }
    }
}
