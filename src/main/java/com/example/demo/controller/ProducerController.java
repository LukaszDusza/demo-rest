package com.example.demo.controller;


import com.example.demo.Mapper.ProducerMapper;
import com.example.demo.Repository.ProducerRepository;
import com.example.demo.dtos.ProducerDto;
import com.example.demo.dtos.ProductDto;
import com.example.demo.entities.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class ProducerController {

    @Autowired
    ProducerRepository producerRepository;

    @GetMapping("producers")
    public List<ProducerDto> producers() {
        List<Producer> list = producerRepository.findAll();
        ProducerMapper mapper = new ProducerMapper();
        List<ProducerDto> dtos = new ArrayList<>();
        for(Producer p : list) {
            ProducerDto producerDto = mapper.map(p);
            dtos.add(producerDto);
        }
        return dtos;
    }

}
