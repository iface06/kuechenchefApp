
package de.vawi.kuechenchefApp;

import org.junit.Ignore;
import org.junit.runner.*;

/**
 *
 * @author Tatsch
 */
@Ignore
public class IntegrationTestsRunner {
    
    public static void main(String... args){
        Result result = new JUnitCore().run(IntegrationTests.class);
        
        System.out.println("Runtime: " + result.getRunTime() + "ms");
        System.out.println("Run Tests: " + result.getRunCount());
        System.out.println("Failed Tests: " + result.getFailureCount());
        System.out.println(result.wasSuccessful() ? "Success!" : "Failed!");
    }

}
