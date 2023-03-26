package com.romario.ECommerce.repositories;

import com.romario.ECommerce.entities.CustomerEntity;
import com.romario.ECommerce.services.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
}
