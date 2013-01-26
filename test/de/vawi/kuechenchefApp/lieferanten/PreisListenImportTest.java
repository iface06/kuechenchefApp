
package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.DateiLeser;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Wird wohl ne Art Integrationstest... 
 * @author Tatsch
 */

public class PreisListenImportTest {    
    
    @Test
    public void testLeseAlleDateienEin() {
        PreisListenImport importer = new PreisListenImport("testordner");
        //importer.importFiles();
    }
}
