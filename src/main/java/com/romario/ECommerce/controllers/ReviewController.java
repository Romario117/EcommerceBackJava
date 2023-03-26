package com.romario.ECommerce.controllers;

import com.romario.ECommerce.entities.CustomerEntity;
import com.romario.ECommerce.entities.ProductEntity;
import com.romario.ECommerce.entities.ReviewEntity;
import com.romario.ECommerce.repositories.CustomerRepository;
import com.romario.ECommerce.repositories.ProductRepository;
import com.romario.ECommerce.repositories.ReviewRepository;
import com.romario.ECommerce.utils.ReviewsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/comentarios")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ReviewsUtils  reviewsUtils;

    @GetMapping("/product/{id}")
    public ResponseEntity<ReviewEntity> getReviewProduct(@PathVariable("id") Long id){
        try{
            ReviewEntity entity =  reviewRepository.getReferenceById(id);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }catch (Exception ex){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/product/{id}")
    public  ResponseEntity<ReviewEntity> addReviewProduct(@PathVariable("id") Long id,@RequestHeader("idCustomer") String idCustomer ,@RequestBody ReviewEntity entity){
        try {
            Long idC = Long.parseLong(idCustomer);
            Optional<CustomerEntity> dataCustomer = customerRepository.findById(idC);
            CustomerEntity customer = dataCustomer.get();
            Optional<ProductEntity> dataProduct = productRepository.findById(id);
            ProductEntity product = dataProduct.get();

            ReviewEntity review = reviewRepository
                    .save(new ReviewEntity(customer,product,entity.getRating(),entity.getReview()));
            return new ResponseEntity<>(review, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/review/{idReview}")
    public ResponseEntity<ReviewEntity> updateReview(@PathVariable("idReview") Long id, @RequestBody ReviewEntity reviewEntity){
        Optional<ReviewEntity> Data = reviewRepository.findById(id);

        if (Data.isPresent()) {
            ReviewEntity review = Data.get();
            review.setRating(reviewEntity.getRating());
            review.setReview(reviewEntity.getReview());

            return new ResponseEntity<>(reviewRepository.save(review), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/review/{idReview}")
    public ResponseEntity<ReviewEntity> deleteComment(@PathVariable("idReview")){
        try{
            reviewRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
