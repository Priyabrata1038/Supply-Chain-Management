package com.SpringBoot.Supply_Chain.Entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@CreationTimestamp
	private LocalDateTime date;
	private double totalAmount;
	private String status;
	private long trackingNumber;
	
	@JoinColumn
	@ManyToOne
	private Customer customer;
	@ManyToMany
	private List<Product> product;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(long trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", totalAmount=" + totalAmount + ", status=" + status
				+ ", trackingNumber=" + trackingNumber + "]";
	}
	
	
	
}
