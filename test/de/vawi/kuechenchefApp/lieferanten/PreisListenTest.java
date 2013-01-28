

package de.vawi.kuechenchefApp.lieferanten;

import org.junit.*;
import static org.junit.Assert.*;


public class PreisListenTest {
    
    
    @Test
    public void leseDateien(){
        PreisListen listen = new PreisListen();
        listen.leseDateien("importDatein");
        
        assertTrue(listen.iterator().hasNext());
    }
    

}