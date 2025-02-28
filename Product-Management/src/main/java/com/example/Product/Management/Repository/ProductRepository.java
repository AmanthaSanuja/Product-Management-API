package com.example.Product.Management.Repository;

import com.example.Product.Management.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
