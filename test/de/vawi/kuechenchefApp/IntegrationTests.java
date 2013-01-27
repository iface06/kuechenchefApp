
package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.speisen.ImportIntegrationTest;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Categories.class)
@IncludeCategory(IntegrationTest.class)
@SuiteClasses({AllTests.class})
public class IntegrationTests {

}