package com.piro84.model1.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.piro84.model1.Entity1;

/**
 * 
 * Factory class that creates {@link Entity1} with default values.
 * It handles the creation of embedded objects as well.
 */
@Component("model1.entityfactory")
public class EntityFactory {
	
	@Autowired
	EntityDefaultProvider defaultProvider;
	
	public Entity1 createPersonWithDefaultsWithNoNulls(){
		Entity1 person = new Entity1();
		person.setName(defaultProvider.getName());
		person.setEmbeddable2(defaultProvider.getAddress("bunker"));
		person.setEmbeddable1(defaultProvider.getAddress("secret"));
		person.setEmbeddableList2(defaultProvider.getAddresses(2, "home"));
		person.setEmbeddableList1(defaultProvider.getAddresses(2, "private"));
		return person;
	}
	
	public Entity1 createPersonWithDefaultsWithNulls(){
		Entity1 person = new Entity1();
		person.setName(defaultProvider.getName());
		
		return person;
	}

}
