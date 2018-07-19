package com.example.demo.Repository;

import com.example.demo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {


  //  String FIND_BY_TITLE = "select * from category where name like ?1%";
    String FIND_ONE_BY_TITLE = "select * from category where name = ?1";

    String FIND_BY_EXPRESSION = "select * from category where name like ?1% or description like ?1%";
    @Query(value = FIND_BY_EXPRESSION, nativeQuery = true)
    List<Category> searchByAnything(String expression);

    @Query(value = FIND_ONE_BY_TITLE, nativeQuery = true)
    Optional<Category> findOneByTitle(String title);
}
