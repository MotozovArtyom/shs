package ru.rienel.shsheadcontroller.repository;

import org.springframework.data.repository.CrudRepository;

import ru.rienel.shsheadcontroller.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
