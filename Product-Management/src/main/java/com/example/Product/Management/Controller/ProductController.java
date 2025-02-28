package com.example.Product.Management.Controller;

import com.example.Product.Management.Dto.ProductDto;
import com.example.Product.Management.Model.Product;
import com.example.Product.Management.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pms")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    //Show productList
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    //Create product
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(
            @RequestBody ProductDto productDto) {

        Product product = new Product();

        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setCreatedAt(new Date());
        productRepository.save(product);
        return ResponseEntity.ok().body("product added successfully");
    }

    //Delete product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        Optional<Product>OptionalProduct = productRepository.findById(id);
        if(OptionalProduct.isPresent()){
            productRepository.deleteById(id);
            return ResponseEntity.ok().body("Product deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    //Edit product
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateProduct(
            @RequestBody ProductDto productDto,
            @PathVariable Integer id){
        Optional<Product>OptionalProduct = productRepository.findById(id);
        if(!OptionalProduct.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Product product = OptionalProduct.get();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        return ResponseEntity.ok().body(productRepository.save(product));
    }

}
