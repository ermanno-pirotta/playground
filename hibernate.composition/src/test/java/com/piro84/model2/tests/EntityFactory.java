package com.piro84.model2.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.piro84.model2.Entity1;

/**
 * 
 * Factory class that creates {@link Entity1} with default values.
 * It handles the creation of embedded objects as well.
 */
@Component("model2.entityfactory")
public class EntityFactory {
	
	@Autowired
	EntityDefaultProvider defaultProvider;
	
	public Entity1 createEntityWithDefaultsWithNoNulls(){
		Entity1 entity = new Entity1();
		entity.setEmbeddable(defaultProvider.getSteer());
		
		return entity;
	}
	
	public Entity1 createEntityWithDefaultsWithNulls(){
		return new Entity1();
	}

}
