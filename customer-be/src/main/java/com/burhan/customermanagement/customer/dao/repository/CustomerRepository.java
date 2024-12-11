package com.burhan.customermanagement.customer.dao.repository;

import com.burhan.customermanagement.customer.dao.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer,Long> {
    boolean existsByTcNo (String tcNo);
}
