
package de.vawi.kuechenchefApp;

import org.junit.extensions.cpsuite.ClasspathSuite;
import org.junit.extensions.cpsuite.ClasspathSuite.ClassnameFilters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(ClasspathSuite.class)
@ClassnameFilters({".*Test"})
public class AllTests {

}