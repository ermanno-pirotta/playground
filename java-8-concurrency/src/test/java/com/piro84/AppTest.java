package com.piro84;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.piro84.App;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	
	public void testRunWithExecutor() {
		App app = new App();
		
		long startTime = System.currentTimeMillis();
		System.out.println("/******************************************************************/");
		System.out.format("runWithExecutor method started \n");
		
		long maximumExecutionTime = app.runWithExecutor();
		long executionTime = System.currentTimeMillis() - startTime;
		System.out.format("runWithExecutor method completed in %d milliseconds, over a maximum of %d \n", executionTime, maximumExecutionTime);
		
		assertTrue("execution time with executor is less than the maximum service running time", executionTime < maximumExecutionTime);
	}
	
	public void testRunSequential() {
		App app = new App();
		long startTime = System.currentTimeMillis();
		System.out.println("/******************************************************************/");
		System.out.format("runSequentially method started \n");
		
		long maximumExecutionTime = app.runSequentially();
		long executionTime = System.currentTimeMillis() - startTime;
		
		System.out.format("runSequentially method completed in %d milliseconds, over a maximum of %d \n", executionTime, maximumExecutionTime);
		System.out.println("/******************************************************************/");
		
		assertTrue("sequential execution time is great than or equal the maximum service running time", executionTime >=  maximumExecutionTime);
	}
	
	public void testRunWithParallelStream() {
		App app = new App();
		long startTime = System.currentTimeMillis();
		System.out.println("/******************************************************************/");
		System.out.format("runWithParallelStream method started \n");
		
		long maximumExecutionTime = app.runWithParallelStream();
		
		long executionTime = System.currentTimeMillis() - startTime;
		
		System.out.format("runWithParallelStream method completed in %d milliseconds, over a maximum of %d \n", executionTime, maximumExecutionTime);
		System.out.println("/******************************************************************/");
		
		assertTrue("execution time with parallel stream is less than the maximum service running time", executionTime < maximumExecutionTime);
	}
	
}
