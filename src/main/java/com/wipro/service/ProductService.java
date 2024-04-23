package com.wipro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.model.DealerProduct;
import com.wipro.model.Product;
import com.wipro.model.ProductCategory;
import com.wipro.repository.DealerProductRepository;
import com.wipro.repository.ProductCategoryRepository;
import com.wipro.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DealerProductRepository dealerProductRepository;
    private final ProductCategoryRepository productCategoryRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, DealerProductRepository dealerProductRepository,ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
		this.dealerProductRepository = dealerProductRepository;
		this.productCategoryRepository = productCategoryRepository;
    }

    public Product addProduct(Product product) {
        // Retrieve DealerProduct and ProductCategory from the repository using their IDs
        DealerProduct dealer = dealerProductRepository.findById(product.getDealer().getId())
                .orElseThrow(() -> new EntityNotFoundException("Dealer not found with id: " + product.getDealer().getId()));

        ProductCategory productCategory = productCategoryRepository.findById(product.getProductCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("ProductCategory not found with id: " + product.getProductCategory().getId()));

        // Set the retrieved DealerProduct and ProductCategory to the product
        product.setDealer(dealer);
        product.setProductCategory(productCategory);

        // Save the product
        return productRepository.save(product);
    }

    public List<Product> getProductByDealerName(String dealerName) {
        return productRepository.findByDealer_DealerName(dealerName);
    }
}

