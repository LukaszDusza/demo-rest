package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
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


    @ManyToMany(mappedBy = "products")
    private Set<Category> categories;

    @Column(name = "price")
    private BigDecimal price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_producer")
    private Producer producer;

    @Column(name = "pieces")
    private int pieces;

    @Column(name = "promotion")
    private boolean promotion;




}
