package com.SpringBoot.Supply_Chain.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.SpringBoot.Supply_Chain.Entity.Product;
import com.SpringBoot.Supply_Chain.Repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
		
	}

	public Product getProductBySupplierId(Integer id) {
		return productRepository.getProductBySupplierId(id);
	}

	public List<Product> getProductByStockQuantity(Integer stockQuantity) {
		return productRepository.getProductByStockQuantity(stockQuantity);
	}

	public Page<Product> getProductByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    return productRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
	}

}
