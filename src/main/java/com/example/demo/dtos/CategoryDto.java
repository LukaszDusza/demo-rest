package com.example.demo.dtos;

import lombok.*;


@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String name;
    private String description;
    private String products;
}
