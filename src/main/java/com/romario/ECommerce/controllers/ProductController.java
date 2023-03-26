package com.romario.ECommerce.controllers;

import com.romario.ECommerce.entities.ProductEntity;
import com.romario.ECommerce.repositories.ProductRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<ProductEntity> newProduct(@RequestBody ProductEntity productEntity){
        try {
            ProductEntity product = productRepository
                    .save(new ProductEntity(productEntity.getProductName(), productEntity.getDescription(),
                            productEntity.getPrice(), productEntity.getUrlImage(), productEntity.getStock()));
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable("id") long id, @RequestBody ProductEntity product){
        Optional<ProductEntity> Data = productRepository.findById(id);

        if (Data.isPresent()) {
            ProductEntity product1 = Data.get();
            product1.setProductName(product.getProductName());
            product1.setDescription(product.getDescription());
            product1.setPrice(product.getPrice());
            product1.setUrlImage(product.getUrlImage());
            product1.setStock(product.getStock());
            return new ResponseEntity<>(productRepository.save(product1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
