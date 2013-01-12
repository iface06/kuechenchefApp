

package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.speisen.Speise;
import org.junit.*;
import static org.junit.Assert.*;


public class SpeiseTest {

    @Test
    public void testNotEquals() {
        Speise a = new Speise();
        a.setName("Steaks");
        
        Speise b = new Speise();
        b.setName("Blumenkohl");
        
        assertFalse(a.equals(b));
    }
    
    @Test
    public void testEquals() {
        Speise a = new Speise();
        a.setName("Steaks");
        
        Speise b = new Speise();
        b.setName("Steaks");
        
        assertTrue(a.equals(b));
    }

}