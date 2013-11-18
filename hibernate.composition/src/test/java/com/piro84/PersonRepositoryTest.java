package com.piro84;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.piro84.entities.Person;
import com.piro84.repositories.PersonRepository;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = "classpath:META-INF/application-context.xml" )
// @ContextConfiguration(locations="classpath:META-INF/test-context.xml")
public class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;

    @Test
    public void test() {

        Person person = new Person();
        person.setName( "Pluto" );

        repository.save( person );

        // Post dbpost = repository.findOne(post.getPostId());
        // assertNotNull(dbpost);
        // System.out.println(dbpost.getTitle());
    }
}
