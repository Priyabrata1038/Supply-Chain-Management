package com.SpringBoot.Supply_Chain.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.SpringBoot.Supply_Chain.Entity.Supplier;
import com.SpringBoot.Supply_Chain.Repository.SupplierRepository;
@Repository
public class SupplierDao {

	@Autowired
	private SupplierRepository supplierRepository;
	
	public Supplier saveSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}
	public Optional<Supplier> findById(Integer id) {
		return supplierRepository.findById(id);
	}
	public List<Supplier> getAllSupplier() {
		return supplierRepository.findAll();
	}
	public void deleteSupplier(Integer id) {
		supplierRepository.deleteById(id);
	}
	public Supplier findByProductId(Integer id) {
		return supplierRepository.getSupplierByProductId(id);
	}
	public Page<Supplier> getSupplierByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    return supplierRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
	}

}
