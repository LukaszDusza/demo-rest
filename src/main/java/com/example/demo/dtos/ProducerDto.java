package com.example.demo.dtos;


import com.example.demo.entities.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProducerDto {


    private String name;
    private String description;
    private List<String> products;
    private List<String> categories;
}
