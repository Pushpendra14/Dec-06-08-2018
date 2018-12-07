package com.synechron;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	private PersonRepository personRepository;
	
	@DeleteMapping("/{id}")
	public String deletePerson(@PathVariable int id) {
		Optional<Person> optPerson = personRepository.findById(id);
		String message = "";
		if(optPerson.isPresent()) {
			personRepository.deleteById(id);
			message = "Deleted";
		}
		else {
			message = "Person with  id " + id + " is not available";
		}
		return message;
	}
	
	@PutMapping("/{id}/{newAge}")
	public Person updateAge(@PathVariable int id, @PathVariable int newAge) {
		Optional<Person> optPerson = personRepository.findById(id);
		Person person = null;
		if(optPerson.isPresent()) {
			person = optPerson.get();
			person.setAge(newAge);
			personRepository.save(person);	
		}
		return person;
	}
	
	@GetMapping("/")
	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<>();
		personRepository.findAll().forEach(person -> persons.add(person));
		return persons;
	}
	
	@PostMapping("/{name}/{age}")
	public String savePerson(@PathVariable String name, @PathVariable int age) {
		Person person = new Person();
		person.setName(name);
		person.setAge(age);
		personRepository.save(person);
		return "Saved";
	}
}







