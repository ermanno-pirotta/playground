package com.piro84;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.piro84.tasks.LongRunningService;

/**
 * Sample app for playing around with java 8 executors and callable.
 *
 */
public class App {
	
	final List<Callable<Long>> tasks = Arrays.asList(new LongRunningService("Service A"),
			new LongRunningService("Service B"), new LongRunningService("Service C"));
	

	public long runWithExecutor() {
		ExecutorService executor = Executors.newWorkStealingPool();

		Long maximumExecTime = 0L;
		
		try {
			maximumExecTime = executor.invokeAll(tasks).stream().mapToLong(future -> {
				try {
					return future.get();
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			}).sum();

			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("tasks interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("shutdown finished");
		}

		return maximumExecTime;
		
	}

	public Long runSequentially() {
		return tasks.stream().mapToLong(execution -> {
			try {
				return execution.call();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}).sum();

	}
	
	public Long runWithParallelStream() {
		return tasks.parallelStream().mapToLong(execution -> {
			try {
				return execution.call();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}).sum();
	}
}
