package com.example.demo.dtos;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String name;
    private String description;
    private String categories;
    private BigDecimal price;
    private String producer;
    private int pieces;
    private boolean promotion;


}
