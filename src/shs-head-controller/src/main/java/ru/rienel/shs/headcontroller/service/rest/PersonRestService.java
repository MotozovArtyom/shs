package ru.rienel.shs.headcontroller.service.rest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rienel.shs.headcontroller.repository.PersonRepository;
import ru.rienel.shs.headcontroller.domain.Person;

@RestController
@RequestMapping("api/v1/person")
public class PersonRestService {
	private PersonRepository personRepository;

	@Autowired
	public PersonRestService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@GetMapping("all")
	public List<Person> getAllPerson() {
		Iterable<Person> queryResult = personRepository.findAll();
		List<Person> persons = new LinkedList<>();
		for (Person person : queryResult) {
			persons.add(person);
		}
		return persons;
	}
}