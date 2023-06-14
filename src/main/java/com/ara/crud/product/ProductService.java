package com.ara.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    //@GetMapping//(path = "/")
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {

        return this.productRepository.findAll();
//        return List.of(
//                new Product(
//
//                        2541L,
//                        "Laptop MAC",
//                        500,
//                        LocalDate.of(2023, Month.MARCH,5),
//                        2
//
//                ));
    }

}
