package com.person.management.ServiceTest;


import static org.assertj.core.api.Assertions.assertThat;

import com.person.management.Entity.Person;
import com.person.management.Repository.PersonRepository;
import com.person.management.Service.PersonService;
import com.person.management.Service.PersonServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImp personService;

    @Test
    void getAll(){
        Person person1= new Person( 1,"Ahnis","Gotham");
        Person person2= new Person(2,"Saksham","New york");
        given(personRepository.findAll()).willReturn(List.of(person1,person2));
        var  personList = personService.all();
        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(2);
    }

    @Test
    void addPerson(){
        Person person3= new Person( 3,"Ahram","Cairo");
        when(personRepository.save(person3)).thenReturn(person3);
        var person = personService.add(person3);
        assertThat(person).isNotNull();
        assertThat(person.getPersonName()).isEqualTo("Ahram");
        assertThat(person.getPersonCity()).isEqualTo("Cairo");
        verify(personRepository,times(1)).save(person3);
    }

    @Test
    void getById(){

        Person person1= new Person( 1,"Ahnis","Gotham");
        given(personRepository.findById(person1.getPersonId())).willReturn(Optional.of(person1));
        var person = personService.getById(person1.getPersonId());
        assertThat(person).isNotNull();
        assertThat(person.getPersonId()).isEqualTo(1);
        assertThat(person.getPersonCity()).isEqualTo("Gotham");
        assertThat(person.getPersonName()).isEqualTo("Ahnis");
        verify(personRepository,times(1)).findById(person1.getPersonId());
    }

    @Test
    void delete(){
        Person person1= new Person( 1,"Ahnis","Gotham");
        given(personRepository.findById(person1.getPersonId())).willReturn(Optional.of(person1));
        var person = personService.getById(person1.getPersonId());
        assertThat(person).isNotNull();
        assertThat(person.getPersonId()).isEqualTo(person1.getPersonId());
        personService.delete(person);
        verify(personRepository,times(1)).delete(person);
    }

    @Test
//    void update(){
//        Person existingPerson = new Person(1, "Ahnis", "Gotham");
//        Person updatedPerson = new Person(1, "Ahnis Updated", "Metropolis");
//        given(personRepository.findById(existingPerson.getPersonId())).willReturn(Optional.of(existingPerson));
//        when(personRepository.save(updatedPerson)).thenReturn(updatedPerson);
//        Person person = personService.update(updatedPerson);
//        assertThat(person).isNotNull();
//        assertThat(person.getPersonId()).isEqualTo(updatedPerson.getPersonId());
//        assertThat(person.getPersonName()).isEqualTo(updatedPerson.getPersonName());
//        assertThat(person.getPersonName()).isEqualTo(updatedPerson.getPersonName());
//        verify(personRepository,times(1)).save(updatedPerson);
//
//    }

    void updatePerson() {

        Person personToUpdate = new Person(1, "fati", "casa");
        Person personUpdated = new Person(1, "fati-zahra", "casa");

        given(personRepository.findById(1)).willReturn(Optional.of(personToUpdate));
        when(personRepository.save(any(Person.class))).thenReturn(personUpdated);

        Person person = personService.update(personUpdated);

        assertThat(person).isNotNull();
        assertThat(person.getPersonName()).isEqualTo("fati-zahra");
        assertThat(person.getPersonCity()).isEqualTo("casa");
        verify(personRepository, times(1)).save(any(Person.class));


    }
}
