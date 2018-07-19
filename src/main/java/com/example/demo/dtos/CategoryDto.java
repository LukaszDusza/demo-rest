package com.example.demo.dtos;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String name;
    private String description;
 //   private List<String> producers;
}
