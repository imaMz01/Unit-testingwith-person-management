package com.person.management.Service;

import com.person.management.Entity.Person;
import com.person.management.Repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;


    @Override
    public List<Person> all() {
        return personRepository.findAll();
    }

    @Override
    public Person add(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person getById(int id) {
        return personRepository.findById(id).orElseThrow(
                () ->new RuntimeException("person not found")
        );
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public Person update(Person person) {
        Person person1 = getById(person.getPersonId());
        person1.setPersonCity(person.getPersonCity());
        person1.setPersonName(person.getPersonName());
        return personRepository.save(person1);
    }
}
