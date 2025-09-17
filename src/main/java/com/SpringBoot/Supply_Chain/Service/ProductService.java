package com.SpringBoot.Supply_Chain.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SpringBoot.Supply_Chain.DAO.ProductDao;
import com.SpringBoot.Supply_Chain.DTO.ResponseStructure;
import com.SpringBoot.Supply_Chain.Entity.Product;
import com.SpringBoot.Supply_Chain.Exception.IdNotFoundException;
import com.SpringBoot.Supply_Chain.Exception.NoRecordAvailableException;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
		ResponseStructure<Product> res = new ResponseStructure<Product>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Product record is saved");
		res.setData(productDao.saveProduct(product));
		
		return new ResponseEntity<ResponseStructure<Product>>(res,HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Product>> getProductById(Integer id) {
		ResponseStructure<Product> res = new ResponseStructure<>();
        Optional<Product> opt = productDao.findById(id);
        if(opt.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Product record retrieved");
            res.setData(opt.get());
            
            return new ResponseEntity<ResponseStructure<Product>>(res, HttpStatus.OK);
        } else {
        	throw new IdNotFoundException("Product not found since id is invalid id");
        }
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct() {
		ResponseStructure<List<Product>> res = new ResponseStructure<>();
        List<Product> products = productDao.getAllProduct();
        if (!products.isEmpty()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("All Product records retrieved");
            res.setData(products);
            
            
            return new ResponseEntity<ResponseStructure<List<Product>>>(res, HttpStatus.OK);
        } else {
        	throw new NoRecordAvailableException("Product not found since no product present in database");
        }
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		ResponseStructure<Product> res = new ResponseStructure<>();
        Optional<Product> opt = productDao.findById(product.getId());
        if(opt.isPresent()) {
            productDao.saveProduct(product);
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Successfully updated");
            res.setData(product);
           
            
            return new ResponseEntity<ResponseStructure<Product>>(res, HttpStatus.OK);
        } else {
        	throw new IdNotFoundException("Product not updated since id is invalid");
        }
	}

	public ResponseEntity<ResponseStructure<String>> deleteProduct(Integer id) {
		ResponseStructure<String> res = new ResponseStructure<>();
        Optional<Product> opt = productDao.findById(id);
        if(opt.isPresent()) {
            productDao.deleteProduct(id);
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Successfully deleted");
            res.setData("Record with id: " + id + " has been deleted");
            
            return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.OK);
        } else {
            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("Failed to delete");
            res.setData("Record with id: " + id + " is not available");
            
            return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.NOT_FOUND);
        } 
	}

	public ResponseEntity<ResponseStructure<Product>> getProductBySupplierId(Integer id) {
		ResponseStructure<Product> res = new ResponseStructure<>();
	    Product product = productDao.getProductBySupplierId(id);
	        if (product != null) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Supplier found for product id");
	            res.setData(product);
	            return new ResponseEntity<ResponseStructure<Product>>(res, HttpStatus.OK);
	        } else {
	            throw new IdNotFoundException("No product found for product id: " + id);
	        }
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getProductByStockQuantity(Integer stockQuantity) {
		ResponseStructure<List<Product>> res = new ResponseStructure<>();
        List<Product> products = productDao.getProductByStockQuantity(stockQuantity);
        if (!products.isEmpty()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("All Product records retrieved");
            res.setData(products);
            
            
            return new ResponseEntity<ResponseStructure<List<Product>>>(res, HttpStatus.OK);
        } else {
        	throw new NoRecordAvailableException("Product not found since no product present in database");
        }
	}

	public ResponseEntity<ResponseStructure<Page<Product>>> getProductByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    Page<Product> products = productDao.getProductByPaginationAndSorting(pageNumber, pageSize, field);
	    ResponseStructure<Page<Product>> response = new ResponseStructure<>();
	    if (!products.isEmpty()) {
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Product retrieved by Pagination");
	        response.setData(products);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        throw new NoRecordAvailableException("Product not found since no product present in database");
	    }
	}
}
