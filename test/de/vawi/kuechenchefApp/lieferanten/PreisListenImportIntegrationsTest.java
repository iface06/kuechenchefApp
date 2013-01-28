
package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.IntegrationTest;
import de.vawi.kuechenchefApp.dateien.DateiLeser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * Wird wohl ne Art Integrationstest... 
 * @author Tatsch
 */

@Category(IntegrationTest.class)
public class PreisListenImportIntegrationsTest {    
    
    @Test
    public void testLeseAlleDateienEin() {
        PreisListenImport importer = new PreisListenImport("importDatein");
        importer.importFiles();
        assertEquals(467, LieferantenVerwaltung.getInstanz().anzahlPreisListenPositionen());
    }
}
