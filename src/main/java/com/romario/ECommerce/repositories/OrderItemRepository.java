package com.romario.ECommerce.repositories;

import com.romario.ECommerce.entities.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemsEntity,Long> {
}
