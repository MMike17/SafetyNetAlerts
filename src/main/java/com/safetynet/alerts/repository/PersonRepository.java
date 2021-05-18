package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class to access Person objects in database
 * 
 * @author MikeMatthews
 * @see Person
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	public List<Person> findByFirstNameAndLastName(String firstName, String lastName);

	public List<Person> findByAddress(String address);

	public List<Person> findByCity(String city);
}