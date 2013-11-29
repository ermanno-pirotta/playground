package com.piro84.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite with all test cases for the project.
 * @author pie
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ com.piro84.model1.tests.EntityRepositoryTest.class, com.piro84.model2.tests.EntityRepositoryTest.class })
public class AllTests {

}
