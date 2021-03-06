package ru.rienel.shs.headcontroller.domain.dto.converters;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import ru.rienel.shs.headcontroller.domain.Person;
import ru.rienel.shs.headcontroller.domain.dto.PersonDto;

@Component
public class PersonConverter implements Converter<Person, PersonDto> {

	@Override
	public Person fromDto(@NotNull PersonDto dto) {
		Objects.requireNonNull(dto);

		Person person = new Person();
		person.setName(dto.getName());
		person.setSurname(dto.getSurname());
		person.setPatronymic(dto.getPatronymic());
		person.setAddress(dto.getAddress());
		return person;
	}

	@Override
	public PersonDto fromDomain(@NotNull Person person) {
		Objects.requireNonNull(person);

		PersonDto dto = new PersonDto();
		dto.setName(person.getName());
		dto.setSurname(person.getSurname());
		dto.setPatronymic(person.getPatronymic());
		dto.setAddress(person.getAddress());
		return dto;
	}
}
