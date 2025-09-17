package com.SpringBoot.Supply_Chain.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.Supply_Chain.DTO.ResponseStructure;
import com.SpringBoot.Supply_Chain.Entity.Product;
import com.SpringBoot.Supply_Chain.Service.ProductService;


@RequestMapping("/api/product")
@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product){
		return productService.saveProduct(product);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> getProductById(@PathVariable Integer id){
		return productService.getProductById(id);
	}
	@GetMapping
    public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(){
       return productService.getAllProduct();
       
    }
    
    @PutMapping
    public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable Integer id){
    	return productService.deleteProduct(id);
    }
    
    @GetMapping("/supplierId/{id}")
	public ResponseEntity<ResponseStructure<Product>> getProductBySupplierId(@PathVariable Integer id){
		return productService.getProductBySupplierId(id);
	}
    @GetMapping("/stockQuantity/{stockQuantity}")
    public ResponseEntity<ResponseStructure<List<Product>>> getProductByStockQuantity(@PathVariable Integer stockQuantity){
       return productService.getProductByStockQuantity(stockQuantity);
       
    }
    
    @GetMapping("/page/{pageNumber}/{pageSize}/sort/{field}")
    public ResponseEntity<ResponseStructure<Page<Product>>> getProductPaginationAndSort(
            @PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field) {
        return productService.getProductByPaginationAndSorting(pageNumber, pageSize, field);
    }
}
