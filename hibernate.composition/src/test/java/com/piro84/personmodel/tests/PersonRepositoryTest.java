package com.piro84.personmodel.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

import com.piro84.entities.personmodel.Address;
import com.piro84.entities.personmodel.Person;
import com.piro84.repositories.PersonRepository;


@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = "classpath:META-INF/application-context.xml" )
// @ContextConfiguration(locations="classpath:META-INF/test-context.xml")
public class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;
    
    @Autowired
    PersonFactory factory;

    @Autowired
    JpaTransactionManager transactionManager;
    
    Person person;
    
    /**
     * Creates a {@link Person} with defaults and save it in the database
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
	    	Person newPerson = factory.createPersonWithDefaultsWithNoNulls();
	        repository.save( newPerson );
	        personId = newPerson.getId();
    	}
    	{    	
    		
    		assertTrue(repository.exists(personId));
    	}
    }
    
    /**
     * Tests updating a {@link Person} entity. It changes the name and set all
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
    
    private void editPerson(Person person){
    	String newName="Pippo";
    	{
    		//get the person from the db
    		Person oldPerson = repository.findOne(person.getId());
    		//change the person name and delete the inner value objects
    		oldPerson.setName(newName);
    		oldPerson.setBunkerAddress(null);
    		oldPerson.setSecretAddress(null);
    		oldPerson.setPrivateAddresses(null);
    		oldPerson.setHomeAddresses(null);
    		//persist the changes
    		repository.save(oldPerson);
    	}
    }
    
    private void checkChanges(String expectedName){
    	//get the person from the db
    	Person updatedPerson = repository.findOne(person.getId());
    	
    	//check that the name has changed and that the components are set to null
    	assertEquals(expectedName,updatedPerson.getName());
    	assertNull(updatedPerson.getBunkerAddress());
    	assertNull(updatedPerson.getSecretAddress());
    	assertEquals(Collections.EMPTY_LIST,updatedPerson.getPrivateAdresses());
    	assertEquals(Collections.EMPTY_LIST,updatedPerson.getHomeAddresses());
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
     * Tests the creation of two {@link Person} entities that have the same instance of {@link Address} component.
     * Each entity will have each own copy of the component.
     */
    @Test 
    public void createTwoEntitiesWithSameComposite(){
    	Person instance1 = null;
    	Person instance2 = null;
    	{
    		TransactionStatus transaction = transactionManager.getTransaction(null);
    		
    		//create two person objects with default values
    		instance1 = factory.createPersonWithDefaultsWithNoNulls();
    		instance2 = factory.createPersonWithDefaultsWithNoNulls();
    		//create an address object with default values
    		//for convenience, change one of the address contained in instance1
    		Address address = instance1.getBunkerAddress();
    		address.setName("Custom bunker address");
    		
    		//edit the person and add the address previously created
    		instance1.setBunkerAddress(address);
    		instance2.setBunkerAddress(address);
    		
    		//save the 2 person objects to the db
    		repository.save(instance1);
    		repository.save(instance2);
    		transactionManager.commit(transaction);
    	}
    	
    	{
    		TransactionStatus transaction = transactionManager.getTransaction(null);
    		
    		Person personFromDb1= repository.findOne(instance1.getId());
    		Person personFromDb2= repository.findOne(instance2.getId());
    		
    		transactionManager.commit(transaction);
    		assertNotEquals(personFromDb1.getBunkerAddress(), personFromDb2.getBunkerAddress());
    	}
    }
}
