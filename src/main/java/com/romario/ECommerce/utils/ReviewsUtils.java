package com.romario.ECommerce.utils;

import com.romario.ECommerce.entities.CustomerEntity;
import com.romario.ECommerce.entities.ProductEntity;
import com.romario.ECommerce.entities.ReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewsUtils {

    public ReviewEntity nuevoReview(CustomerEntity customerEntity, ProductEntity productEntity, Long rating, String review){
        ReviewEntity nuevo = new ReviewEntity();
        nuevo.setIdCustomer(customerEntity);
        nuevo.setIdProduct(productEntity);
        nuevo.setRating(rating);
        nuevo.setReview(review);
        return nuevo;
    }
}
