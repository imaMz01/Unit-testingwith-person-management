package com.person.management.Service;

import com.person.management.Entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> all();
    Person add(Person person);
    Person getById(int id);
    void delete(Person person);
    Person update(Person person);
}
