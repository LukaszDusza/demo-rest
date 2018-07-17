package com.example.demo.Repository;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
