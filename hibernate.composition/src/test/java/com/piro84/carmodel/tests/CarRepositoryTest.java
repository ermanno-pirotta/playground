package com.piro84.carmodel.tests;

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

import com.piro84.entities.carmodel.Car;
import com.piro84.entities.personmodel.Person;
import com.piro84.repositories.CarRepository;


@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = "classpath:META-INF/application-context.xml" )
// @ContextConfiguration(locations="classpath:META-INF/test-context.xml")
public class CarRepositoryTest {

    @Autowired
    CarRepository repository;
    
    @Autowired
    CarFactory factory;

    @Autowired
    JpaTransactionManager transactionManager;
    
    Car car;
    
    /**
     * Creates a {@link Person} with defaults and save it in the database
     */
    @Before
    public void setUp(){
    	car=factory.createCarWithDefaultsWithNoNulls();
    	repository.save(car);    	
    }
    
    @After
    public void tearDown(){
    	//clean all after executing the tests
    	repository.deleteAll();
    }
    
    
    
    @Test
    public void create() {
    	Integer carId = null;
    	{
	    	Car newCar = factory.createCarWithDefaultsWithNoNulls();
	        repository.save( newCar );
	        carId = newCar.getId();
    	}
    	{    	
    		
    		assertTrue(repository.exists(carId));
    	}
    }
    
    @Test
    public void update(){
    	{
    		//get the person from the db
    		TransactionStatus transaction = transactionManager.getTransaction(null);
    		editCar(car.getId());
    		//explicitly commit the transaction that was opened by spring on the repository save method
    		transactionManager.commit(transaction);
    	}
    	{
    		TransactionStatus transaction = transactionManager.getTransaction(null);
    		//get the person from the db
    		Car updatedCar = repository.findOne(car.getId());
    		
    		
    		/*
    		 * here is the critical point of the entity->embeddable->embeddable[] mapping:
    		 * setting the embeddable to null will correctly delete the embeddable and the embeddable[]
    		 * BUT updatedCar.getSteer() will NOT return null.
    		 */
    		
    		//check that the components are set to null
    		assertNotNull(updatedCar.getSteer());
    		assertNull(updatedCar.getSteer().getName());
    		assertEquals(Collections.emptyList(),updatedCar.getSteer().getElectricalComponents());
    		assertEquals(Collections.emptyList(),updatedCar.getSteer().getMechanicalComponents());
    		
    		transactionManager.commit(transaction);
    	}
    }
    
    private void editCar(Integer carId){
    	//get the person from the db
		Car oldCar = repository.findOne(car.getId());
		//change the person name and delete the inner value objects
		oldCar.setSteer(null);
		//persist the changes
		repository.save(oldCar);
    }
    
    @Test
    public void delete(){
    	{
    		//delete the person that was created in the setUp method
    		repository.delete(car.getId());
    		assertFalse(repository.exists(car.getId()));
    	}
    }
}
