package com.piro84.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.piro84.carmodel.tests.CarRepositoryTest;
import com.piro84.personmodel.tests.PersonRepositoryTest;

/**
 * Test suite with all test cases for the project.
 * @author pie
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ CarRepositoryTest.class, PersonRepositoryTest.class })
public class AllTests {

}
