package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String title;
    @JsonManagedReference
    @OneToMany(mappedBy = "company",cascade = CascadeType.PERSIST)
    private List<Job> jobs;
    @JsonManagedReference
    @OneToMany(mappedBy = "company", cascade = CascadeType.PERSIST)
    private List<Review> reviews;
}
