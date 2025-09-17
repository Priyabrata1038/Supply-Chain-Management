package com.SpringBoot.Supply_Chain.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.SpringBoot.Supply_Chain.Entity.Supplier;


public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

	@Query("select p.supplier from Product p where p.id=?1")
	Supplier getSupplierByProductId(int id);

	Page<Supplier> findAll(Pageable pageable);
}
