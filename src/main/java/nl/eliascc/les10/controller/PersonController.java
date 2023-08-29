package nl.eliascc.les10.controller;

import nl.eliascc.les10.model.Person;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    private List<Person> persons;

    public PersonController() {
        persons = new ArrayList<>();
        Person p = new Person();
        p.setName("Albert Einstein");
        p.setDob(LocalDate.of(1847, 10, 1));
        p.setGender('m');
        persons.add(p);
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person newPerson) {
        persons.add(newPerson);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @GetMapping("/persons/{index}")
    public ResponseEntity<Person> getPerson(@PathVariable int index) {
        if (index >= 0 && index < persons.size()) {
            Person p = persons.get(index);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
