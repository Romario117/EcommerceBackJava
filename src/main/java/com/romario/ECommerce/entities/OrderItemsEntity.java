package com.romario.ECommerce.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long idOrderItem;

    @Column(name = "order_id")
    private OrderEntity idOrder;
    @Column(name = "product_id")
    private ProductEntity idProduct;
    private Long quantity;
    private Double price;

    public OrderItemsEntity() {
    }

    public Long getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(Long idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public OrderEntity getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(OrderEntity idOrder) {
        this.idOrder = idOrder;
    }

    public ProductEntity getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(ProductEntity idProduct) {
        this.idProduct = idProduct;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
