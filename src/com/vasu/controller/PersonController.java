package com.vasu.controller;

import com.vasu.dao.PersonRepository;
import com.vasu.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/save", method = POST)
    @ResponseBody
    public String savePerson(@RequestBody Person person) {
        log.info(person.toString());
        try {
            return personRepository.save(person).getName();
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Persistence broke!!!";
        }
    }


    @RequestMapping(value = "/{id}", method = GET)
    public Person getPerson(@PathVariable Long id) {
        return personRepository.findOne(id);
    }


    @RequestMapping(value="/all", method = GET)
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

}
