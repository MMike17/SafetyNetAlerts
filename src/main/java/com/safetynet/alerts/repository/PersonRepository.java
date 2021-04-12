package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class to access Person objects in database
 * 
 * @author MikeMatthews
 * @see com.safetynet.alerts.model.Person
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}