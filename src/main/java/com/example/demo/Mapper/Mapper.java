package com.example.demo.Mapper;

public interface Mapper<F,T> {

    T map(F from);
}
