package com.piro84.model1.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertFalse;
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

import com.piro84.model1.Embeddable1;
import com.piro84.model1.Entity1;
import com.piro84.repositories.Model1EntityRepository;


@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = "classpath:META-INF/application-context.xml" )
// @ContextConfiguration(locations="classpath:META-INF/test-context.xml")
public class EntityRepositoryTest {

    @Autowired
    Model1EntityRepository repository;
    
    @Autowired()
    EntityFactory factory;

    @Autowired
    JpaTransactionManager transactionManager;
    
    Entity1 person;
    
    /**
     * Creates a {@link Entity1} with defaults and save it in the database
     */
    @Before
    public void setUp(){
    	person=factory.createPersonWithDefaultsWithNoNulls();
    	repository.save(person);
    }
    
    @After
    public void tearDown(){
    	//clean all after executing the tests
    	repository.deleteAll();
    }
    
    
    
    @Test
    public void create() {
    	Integer personId = null;
    	{
	    	Entity1 newPerson = factory.createPersonWithDefaultsWithNoNulls();
	        repository.save( newPerson );
	        personId = newPerson.getId();
    	}
    	{    	
    		
    		assertTrue(repository.exists(personId));
    	}
    }
    
    /**
     * Tests updating a {@link Entity1} entity. It changes the name and set all
     * embedded components and embedded collections to null.
     * The update and checkChanges method are executed in different transactions, to check 
     * interaction with the database. This is done on purpose to check in particular "null" collection retrieval:
     * although the collection is set as null, when retrieved from the database it is created as an empty list.
     */
    @Test
    public void update(){
    	String newName="Pippo";
    	{
    		TransactionStatus transaction = transactionManager.getTransaction(null);
    		
    		editPerson(person);
    		
    		transactionManager.commit(transaction);
    	}
    	
    	{
    		TransactionStatus transaction = transactionManager.getTransaction(null);
    		checkChanges(newName);
    		transactionManager.commit(transaction);
    	}
    }
    
    private void editPerson(Entity1 person){
    	String newName="Pippo";
    	{
    		//get the person from the db
    		Entity1 oldPerson = repository.findOne(person.getId());
    		//change the person name and delete the inner value objects
    		oldPerson.setName(newName);
    		oldPerson.setEmbeddable2(null);
    		oldPerson.setEmbeddable1(null);
    		oldPerson.setEmbeddableList1(null);
    		oldPerson.setEmbeddableList2(null);
    		//persist the changes
    		repository.save(oldPerson);
    	}
    }
    
    private void checkChanges(String expectedName){
    	//get the person from the db
    	Entity1 updatedPerson = repository.findOne(person.getId());
    	
    	//check that the name has changed and that the components are set to null
    	assertEquals(expectedName,updatedPerson.getName());
    	assertNull(updatedPerson.getEmbeddable2());
    	assertNull(updatedPerson.getEmbeddable1());
    	assertEquals(Collections.EMPTY_LIST,updatedPerson.getEmbeddableList1());
    	assertEquals(Collections.EMPTY_LIST,updatedPerson.getEmbeddableList2());
    }
    
    @Test
    public void delete(){
    	{
    		//delete the person that was created in the setUp method
    		repository.delete(person.getId());
    		assertFalse(repository.exists(person.getId()));
    	}
    }
    
    /**
     * Tests the creation of two {@link Entity1} entities that have the same instance of {@link Embeddable1} component.
     * Each entity will have each own copy of the component.
     */
    @Test 
    public void createTwoEntitiesWithSameComposite(){
    	Entity1 instance1 = null;
    	Entity1 instance2 = null;
    	{
    		TransactionStatus transaction = transactionManager.getTransaction(null);
    		
    		//create two person objects with default values
    		instance1 = factory.createPersonWithDefaultsWithNoNulls();
    		instance2 = factory.createPersonWithDefaultsWithNoNulls();
    		//create an address object with default values
    		//for convenience, change one of the address contained in instance1
    		Embeddable1 address = instance1.getEmbeddable2();
    		address.setName("Custom bunker address");
    		
    		//edit the person and add the address previously created
    		instance1.setEmbeddable2(address);
    		instance2.setEmbeddable2(address);
    		
    		//save the 2 person objects to the db
    		repository.save(instance1);
    		repository.save(instance2);
    		transactionManager.commit(transaction);
    	}
    	
    	{
    		TransactionStatus transaction = transactionManager.getTransaction(null);
    		
    		Entity1 personFromDb1= repository.findOne(instance1.getId());
    		Entity1 personFromDb2= repository.findOne(instance2.getId());
    		
    		transactionManager.commit(transaction);
    		assertNotSame(personFromDb1.getEmbeddable2(), personFromDb2.getEmbeddable2());
    	}
    }
}
