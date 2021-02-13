package br.com.abinbev.ecommerce.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.abinbev.ecommerce.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

	public List<Product> findByNameContainingIgnoreCase(String text);
	
}
