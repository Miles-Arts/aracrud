package com.ara.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<Object> newProduct(Product product) {
        Optional<Product> respuesta = productRepository.findProductByName(product.getName());
        HashMap<String, Object> datosObjeto = new HashMap<>();


        if(respuesta.isPresent() && product.getId() == null) {
            datosObjeto.put("Error", true);
            datosObjeto.put("Ya existe un producto con ese", " Nombre");
            return new ResponseEntity<>(
                    datosObjeto,
                    HttpStatus.CONFLICT

            );
//            throw new IllegalStateException("Ya existe el producto");
        }

        datosObjeto.put("Mensaje", "Se guardó con éxito el producto");
        if(product.getId() != null) {
            datosObjeto.put("Mensaje", "Se actualizó con éxito el producto");
        }
        productRepository.save(product);

        datosObjeto.put("Datos Producto", product);
        return new ResponseEntity<>(
                datosObjeto,
//                product,
                HttpStatus.CREATED
        );
    }
}

