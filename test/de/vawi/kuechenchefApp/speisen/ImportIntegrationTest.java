

package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.IntegrationTest;
import de.vawi.kuechenchefApp.lieferanten.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.experimental.categories.*;

@Category(IntegrationTest.class)
public class ImportIntegrationTest {
    
   
    
    @Test
    public void testImportSpeisenVonDateiHitlisteUndRezepte() {
        importierePreislisten();
        importiereSpeisen();
        assertEquals(109, SpeisenVerwaltung.getInstanz().size());
    }

    private void importierePreislisten() {
        PreisListenImport importer = new PreisListenImport("importDatein");
        importer.importFiles();
    }

    private void importiereSpeisen() {
        SpeisenImport importer = new SpeisenImport("importDatein");
        importer.importFiles();
    }

}