package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    //@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "products")
//    private Set<Category> categories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_category")
    private Category category;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL) //, orphanRemoval = true
//    private Set<Category> categories = new HashSet<>();

    @Column(name = "price")
    private BigDecimal price = new BigDecimal(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_producer")
    private Producer producer;

    @Column(name = "pieces")
    private Integer pieces;

    @Column(name = "promotion")
    private boolean promotion;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "picture") //zmienic w bazie danych na long text, gdy linki z google.
    private String picture;

}
