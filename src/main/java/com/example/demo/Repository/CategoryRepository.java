package com.example.demo.Repository;

import com.example.demo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {


    String FIND_BY_TITLE = "select * from category where name like ?1%";

    @Async
    @Query(value = FIND_BY_TITLE, nativeQuery = true)
    List<Category> findByTitle(String title);
}
