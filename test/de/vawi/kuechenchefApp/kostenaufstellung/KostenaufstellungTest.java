package de.vawi.kuechenchefApp.kostenaufstellung;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Sonja
 */
public class KostenaufstellungTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getKostenPositionen method, of class Kostenaufstellung.
     */
    @Test
    public void testGetKostenPositionen() {
        System.out.println("getKostenPositionen");
        Kostenaufstellung instance = new Kostenaufstellung();
        List expResult = null;
        List result = instance.getKostenPositionen();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hinzufügenKostenPosition method, of class Kostenaufstellung.
     */
    @Test
    public void testHinzufügenKostenPosition() {
        System.out.println("hinzuf\u00fcgenKostenPosition");
        KostenPosition position = null;
        Kostenaufstellung instance = new Kostenaufstellung();
        instance.hinzufügenKostenPosition(position);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGesamtKosten method, of class Kostenaufstellung.
     */
    @Test
    public void testGetGesamtKosten() {
        System.out.println("getGesamtKosten");
        Kostenaufstellung instance = new Kostenaufstellung();
        double expResult = 0.0;
        double result = instance.getGesamtKosten();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}