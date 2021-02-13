package br.com.abinbev.ecommerce.dto;

import java.io.Serializable;

import br.com.abinbev.ecommerce.domain.Product;

public class ProductDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String description;
	private double price;
	private String brand;

	public ProductDTO() {

	}

	public ProductDTO(Product obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.description = obj.getDescription();
		this.price = obj.getPrice();
		this.brand = obj.getBrand();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
}
