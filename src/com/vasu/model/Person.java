package com.vasu.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "PERSON")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PERSON_ID")
    private Long personId;

    @Column(name="PERSON_NAME", unique = true)
    private String name;
}
