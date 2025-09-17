package com.SpringBoot.Supply_Chain.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SpringBoot.Supply_Chain.Entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query("select p from Product p where p.supplier.id=?1")
	Product getProductBySupplierId(int id);
	
	@Query("select p from Product p where p.stockQuantity=?1")
	List<Product> getProductByStockQuantity(int stockQuantity);
	
	Page<Product> findAll(Pageable pageable);
}
