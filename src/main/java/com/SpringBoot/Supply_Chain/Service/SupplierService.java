package com.SpringBoot.Supply_Chain.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SpringBoot.Supply_Chain.DAO.SupplierDao;
import com.SpringBoot.Supply_Chain.DTO.ResponseStructure;
import com.SpringBoot.Supply_Chain.Entity.Supplier;
import com.SpringBoot.Supply_Chain.Exception.IdNotFoundException;
import com.SpringBoot.Supply_Chain.Exception.NoRecordAvailableException;


@Service
public class SupplierService {
	@Autowired
	private SupplierDao supplierDao;

	public ResponseEntity<ResponseStructure<Supplier>> saveSupplier(Supplier supplier) {
		ResponseStructure<Supplier> res = new ResponseStructure<Supplier>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Supplier record is saved");
		res.setData(supplierDao.saveSupplier(supplier));
		
		return new ResponseEntity<ResponseStructure<Supplier>>(res,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Supplier>> getSupplierById(Integer id) {
		 ResponseStructure<Supplier> res = new ResponseStructure<>();
	        Optional<Supplier> opt = supplierDao.findById(id);
	        if(opt.isPresent()) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Supplier record retrieved");
	            res.setData(opt.get());
	            
	            return new ResponseEntity<ResponseStructure<Supplier>>(res, HttpStatus.OK);
	        } else {
	        	throw new IdNotFoundException("Supplier not found since id is invalid");
	        }
	    
	}

	public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSupplier() {
		ResponseStructure<List<Supplier>> res = new ResponseStructure<>();
        List<Supplier> supplier = supplierDao.getAllSupplier();
        if (!supplier.isEmpty()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("All Supplier records retrieved");
            res.setData(supplier);
            
            
            return new ResponseEntity<ResponseStructure<List<Supplier>>>(res, HttpStatus.OK);
        } else {
        	throw new NoRecordAvailableException("Supplier not found since no supplier present in database");
        }
	}

	public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(Supplier supplier) {
		ResponseStructure<Supplier> res = new ResponseStructure<>();
        Optional<Supplier> opt = supplierDao.findById(supplier.getId());
        if(opt.isPresent()) {
            supplierDao.saveSupplier(supplier);
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Successfully updated");
            res.setData(supplier);
           
            
            return new ResponseEntity<ResponseStructure<Supplier>>(res, HttpStatus.OK);
        } else {
        	throw new IdNotFoundException("Supplier not updated since id is invalid");
        }
	}

	public ResponseEntity<ResponseStructure<String>> deleteSupplier(Integer id) {
		ResponseStructure<String> res = new ResponseStructure<>();
        Optional<Supplier> opt = supplierDao.findById(id);
        if(opt.isPresent()) {
            supplierDao.deleteSupplier(id);
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

	public ResponseEntity<ResponseStructure<Supplier>> getSpplierByProductId(Integer id) {
		 ResponseStructure<Supplier> res = new ResponseStructure<>();
	     Supplier supplier = supplierDao.findByProductId(id);
	        if (supplier != null) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Supplier found for product id");
	            res.setData(supplier);
	            return new ResponseEntity<ResponseStructure<Supplier>>(res, HttpStatus.OK);
	        } else {
	            throw new IdNotFoundException("No supplier found for product id: " + id);
	        }
	}

	public ResponseEntity<ResponseStructure<Page<Supplier>>> getSupplierByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    Page<Supplier> suppliers = supplierDao.getSupplierByPaginationAndSorting(pageNumber, pageSize, field);
	    ResponseStructure<Page<Supplier>> response = new ResponseStructure<>();
	    if (!suppliers.isEmpty()) {
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Supplier retrieved by Pagination");
	        response.setData(suppliers);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        throw new NoRecordAvailableException("supplier not found since no supplier present in database");
	    }
	}

}
