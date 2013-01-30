
package de.vawi.kuechenchefApp;

import org.junit.Ignore;
import org.junit.runner.*;
import org.junit.runner.notification.Failure;

/**
 *
 * @author Tatsch
 */
@Ignore
public class TestRunner {
    
    public static void main(String... args){
        printHeadline();
        
        printUnitTestHeadline();
        Result unitTestsResult = runTests(UnitTests.class);
        printResults(unitTestsResult);
        
        printIntegrationTestHeadline();
        Result integrationTestresult = runTests(IntegrationTests.class);
        printResults(integrationTestresult);
        
        printLearningTestHeadline();
        Result learningTestsResult = runTests(LearningTests.class);
        printResults(learningTestsResult);
    }

    private static void printResults(Result result) {
        String testResult = new String();
        testResult += "============================================================\n\n";
        System.out.print(testResult);
        System.out.print("Testergebnisse\n\n");
        System.out.println("Laufzeit: " + result.getRunTime() + "ms");
        System.out.println("Anzahl ausgeführter Tests: " + result.getRunCount());
        System.out.println("Fehlgeschlagene Tests: " + result.getFailureCount());
        System.out.println(result.wasSuccessful() ? "Success!" : "Failed!");
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.getMessage());
            System.out.println(failure.getDescription());
        }
        System.out.print(testResult);
    }

    private static void printHeadline() {
        String testsTitle = new String();
        testsTitle += "+-------------------------------------------------------+\n";
        testsTitle += "| Tests zur Anwendung KüchenchefApp                     |\n";
        testsTitle += "+-------------------------------------------------------+\n\n";
        System.out.print(testsTitle);
    }

    private static void printUnitTestHeadline() {
        String unitTestsTitle = new String();
        unitTestsTitle += "+-------------------------------------------------------+\n";
        unitTestsTitle += "| Unit Tests                                            |\n";
        unitTestsTitle += "+-------------------------------------------------------+\n";
        System.out.print(unitTestsTitle);
    }

    private static Result runTests(Class clazz) {
        Result result = new JUnitCore().run(clazz);
        return result;
    }

    private static void printIntegrationTestHeadline() {
        String unitTestsTitle = new String();
        unitTestsTitle += "+-------------------------------------------------------+\n";
        unitTestsTitle += "| Integrationtests                                      |\n";
        unitTestsTitle += "+-------------------------------------------------------+\n";
        System.out.print(unitTestsTitle);
    }

    private static void printLearningTestHeadline() {
        String unitTestsTitle = new String();
        unitTestsTitle += "+-------------------------------------------------------+\n";
        unitTestsTitle += "| Learning Tests                                        |\n";
        unitTestsTitle += "+-------------------------------------------------------+\n";
        System.out.print(unitTestsTitle);
    }

}
