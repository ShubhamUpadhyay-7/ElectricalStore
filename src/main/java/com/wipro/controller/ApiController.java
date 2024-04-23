package com.wipro.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.model.DealerProduct;
import com.wipro.model.Product;
import com.wipro.service.DealerProductService;
import com.wipro.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ProductService productService;
    private final DealerProductService dealerProductService;

    @Autowired
    public ApiController(ProductService productService, DealerProductService dealerProductService) {
        this.productService = productService;
        this.dealerProductService = dealerProductService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/dealer-products")
    public ResponseEntity<List<DealerProduct>> getDealerProductByStateAndCity(
            @RequestParam String stateName,
            @RequestParam String cityName
    ) {
        List<DealerProduct> dealerProducts = dealerProductService.getDealerProductByStateAndCity(stateName, cityName);
        return new ResponseEntity<>(dealerProducts, HttpStatus.OK);
    }

    @GetMapping("/products-by-dealer")
    public ResponseEntity<List<Product>> getProductByDealerName(@RequestParam String dealerName) {
        List<Product> products = productService.getProductByDealerName(dealerName);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/dealer-products-by-state")
    public ResponseEntity<List<DealerProduct>> getDealersProductByState(@RequestParam String stateName) {
        List<DealerProduct> dealerProducts = dealerProductService.getDealersProductByState(stateName);
        return new ResponseEntity<>(dealerProducts, HttpStatus.OK);
    }
}
