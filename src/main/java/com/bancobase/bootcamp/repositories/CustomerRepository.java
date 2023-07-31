package com.bancobase.bootcamp.repositories;

import com.bancobase.bootcamp.schemas.CustomerSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerSchema, Long> {
    // opciona para evital el null exepction
    Optional<CustomerSchema> findByCurp(String curp);

    List<CustomerSchema> findByNameContaining(String name);
}
