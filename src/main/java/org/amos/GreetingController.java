package org.amos;

import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GreetingController {

    @GetMapping("/greeting")
    public String hello() {
        return "Hello Spring";
    }

    @PostMapping("/person")
    @Transactional
    public void addPerson(Person person){
        Person.persist(person);
    }

    @GetMapping("/person")
    public List<Person> getPersons(){
        return Person.listAll(Sort.by("firstName").ascending());
    }

    @GetMapping("/person/{id}")
    public Person getThatPersons(@PathVariable("id") long id){
        return Person.findById(id);
    }

    @DeleteMapping("/person/{id}")
    @Transactional
    public void deleteThatPersons(@PathVariable("id") long id){
        Person.delete("id",id);
    }

    @GetMapping("/person/name/{name}")
    public List<Person> getThatPersonsWFirstName(@PathVariable("name") String name){
        return Person.getPersonByFirstName(name);
    }

    @GetMapping("/person/name/{fname}/{lname}")
    public List<Person> getThatPersonsWFirstNameNLastName(@PathVariable("fname") String fname,
                                                          @PathVariable("lname") String lname){
        return Person.getPersonByFirstNameAndLastName(fname,lname);
    }
}
