package com.piro84.tasks;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class LongRunningService implements Callable<Long>{

	public final static int MAX_TASK_DELAY_MILLIS = 5000;
	final String serviceName;
	
	final long taskOperationaTime;
	
	public LongRunningService(String name) {
		taskOperationaTime = new Random().nextInt(MAX_TASK_DELAY_MILLIS);
		this.serviceName = name;
	}

	public Long call() throws Exception {
		System.out.format("%s with taskOperationaTime %d in milliseconds \n", serviceName, taskOperationaTime);
		TimeUnit.MILLISECONDS.sleep(taskOperationaTime);
		return taskOperationaTime;
	}
}