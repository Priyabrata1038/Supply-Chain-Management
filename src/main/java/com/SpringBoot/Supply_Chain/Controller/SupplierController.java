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
import com.SpringBoot.Supply_Chain.Entity.Supplier;
import com.SpringBoot.Supply_Chain.Service.SupplierService;


@RequestMapping("/api/supplier")
@RestController
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Supplier>> saveSupplier(@RequestBody Supplier supplier){
		return supplierService.saveSupplier(supplier);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> getSupplierById(@PathVariable Integer id){
		return supplierService.getSupplierById(id);
	}
	@GetMapping
    public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSupplier(){
       return supplierService.getAllSupplier();
       
    }
    
    @PutMapping
    public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(@RequestBody Supplier supplier) {
        return supplierService.updateSupplier(supplier);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteSupplier(@PathVariable Integer id){
    	return supplierService.deleteSupplier(id);
    }
    @GetMapping("/productId/{id}")
    public ResponseEntity<ResponseStructure<Supplier>> getSupplierByProductId(@PathVariable Integer id) {
       return supplierService.getSpplierByProductId(id);
    }
    @GetMapping("/page/{pageNumber}/{pageSize}/sort/{field}")
    public ResponseEntity<ResponseStructure<Page<Supplier>>> getSupplierPaginationAndSort(
            @PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field) {
        return supplierService.getSupplierByPaginationAndSorting(pageNumber, pageSize, field);
    }

    
}
