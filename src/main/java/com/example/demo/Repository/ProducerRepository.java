package com.example.demo.Repository;

import com.example.demo.entities.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

public interface ProducerRepository extends JpaRepository<Producer, Long>, JpaSpecificationExecutor<Producer> {

    String FIND_BY_TITLE = "select * from producer where name like ?1%";

    @Async
    @Query(value = FIND_BY_TITLE, nativeQuery = true)
    Producer findByTitle(String title);
}
