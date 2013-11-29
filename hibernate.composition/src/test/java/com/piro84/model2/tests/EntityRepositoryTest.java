package com.piro84.model2.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;

import com.piro84.model2.Entity1;
import com.piro84.repositories.Model2EntityRepository;


@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = "classpath:META-INF/application-context.xml" )
// @ContextConfiguration(locations="classpath:META-INF/test-context.xml")
public class EntityRepositoryTest {

    @Autowired
    Model2EntityRepository repository;
    
    @Autowired
    EntityFactory factory;

    @Autowired
    JpaTransactionManager transactionManager;
    
    Entity1 entity;
    
    /**
     * Creates a {@link Entity1} with defaults and save it in the database
     */
    @Before
    public void setUp(){
    	entity=factory.createEntityWithDefaultsWithNoNulls();
    	repository.save(entity);    	
    }
    
    @After
    public void tearDown(){
    	//clean all after executing the tests
    	repository.deleteAll();
    }
    
    
    
    @Test
    public void create() {
    	Integer EntityId = null;
    	{
	    	Entity1 newEntity = factory.createEntityWithDefaultsWithNoNulls();
	        repository.save( newEntity );
	        EntityId = newEntity.getId();
    	}
    	{    	
    		
    		assertTrue(repository.exists(EntityId));
    	}
    }
    
    @Test
    public void update(){
    	{
    		//get the person from the db
    		TransactionStatus transaction = transactionManager.getTransaction(null);
    		editEntity(entity.getId());
    		//explicitly commit the transaction that was opened by spring on the repository save method
    		transactionManager.commit(transaction);
    	}
    	{
    		TransactionStatus transaction = transactionManager.getTransaction(null);
    		//get the person from the db
    		Entity1 updatedEntity = repository.findOne(entity.getId());
    		
    		
    		/*
    		 * here is the critical point of the entity->embeddable->embeddable[] mapping:
    		 * setting the embeddable to null will correctly delete the embeddable and the embeddable[]
    		 * BUT updatedEntity.getSteer() will NOT return null.
    		 */
    		
    		//check that the components are set to null
    		assertNotNull(updatedEntity.getEmbeddable());
    		assertNull(updatedEntity.getEmbeddable().getName());
    		assertEquals(Collections.emptyList(),updatedEntity.getEmbeddable().getEmbeddableList2());
    		assertEquals(Collections.emptyList(),updatedEntity.getEmbeddable().geEmbeddableList1());
    		
    		transactionManager.commit(transaction);
    	}
    }
    
    private void editEntity(Integer EntityId){
    	//get the person from the db
		Entity1 oldEntity = repository.findOne(entity.getId());
		//change the person name and delete the inner value objects
		oldEntity.setEmbeddable(null);
		//persist the changes
		repository.save(oldEntity);
    }
    
    @Test
    public void delete(){
    	{
    		//delete the person that was created in the setUp method
    		repository.delete(entity.getId());
    		assertFalse(repository.exists(entity.getId()));
    	}
    }
}
