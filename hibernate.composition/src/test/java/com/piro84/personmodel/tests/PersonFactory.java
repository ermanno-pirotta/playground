package com.piro84.personmodel.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.piro84.entities.personmodel.Person;

/**
 * 
 * Factory class that creates {@link Person} with default values.
 * It handles the creation of embedded objects as well.
 */
@Component
public class PersonFactory {
	
	@Autowired
	PersonDefaultProvider defaultProvider;
	
	public Person createPersonWithDefaultsWithNoNulls(){
		Person person = new Person();
		person.setName(defaultProvider.getName());
		person.setBunkerAddress(defaultProvider.getAddress("bunker"));
		person.setSecretAddress(defaultProvider.getAddress("secret"));
		person.setHomeAddresses(defaultProvider.getAddresses(2, "home"));
		person.setPrivateAddresses(defaultProvider.getAddresses(2, "private"));
		return person;
	}
	
	public Person createPersonWithDefaultsWithNulls(){
		Person person = new Person();
		person.setName(defaultProvider.getName());
		
		return person;
	}

}
