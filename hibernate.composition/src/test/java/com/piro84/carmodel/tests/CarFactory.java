package com.piro84.carmodel.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.piro84.entities.carmodel.Car;
import com.piro84.entities.personmodel.Person;

/**
 * 
 * Factory class that creates {@link Person} with default values.
 * It handles the creation of embedded objects as well.
 */
@Component
public class CarFactory {
	
	@Autowired
	CarDefaultProvider defaultProvider;
	
	public Car createCarWithDefaultsWithNoNulls(){
		Car car = new Car();
		car.setSteer(defaultProvider.getSteer());
		
		return car;
	}
	
	public Car createCarWithDefaultsWithNulls(){
		return new Car();
	}

}
