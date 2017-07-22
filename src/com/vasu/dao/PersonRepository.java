package com.vasu.dao;

import com.vasu.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {
}
