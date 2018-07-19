package com.example.demo.Repository;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    String FIND_BY_SERIAL_NO = "select * from product where serial_no = ?1";
    String FIND_BY_NAME = "select * from product where name like ?1%";

  //  @Async
    @Query(value = FIND_BY_SERIAL_NO, nativeQuery = true)
    Optional<Product> findBySerialNo(String serialNo);

  //  @Async
    @Query(value = FIND_BY_NAME, nativeQuery = true)
    List<Product> findByProductsName (String name);


}

