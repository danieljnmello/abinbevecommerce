package br.com.abinbev.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.abinbev.ecommerce.domain.Product;
import br.com.abinbev.ecommerce.dto.ProductDTO;
import br.com.abinbev.ecommerce.repository.ProductRepository;
import br.com.abinbev.ecommerce.services.exception.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	public List<Product> findAll() {
		return repo.findAll();
	}

	public Product findById(String id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto nao encontrado!"));
	}

	public Product insert(Product obj) {
		return repo.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	public Product update(Product obj) {
		Product product = findById(obj.getId());
		this.updateData(product, obj);
		return repo.save(product);
	}

	private void updateData(Product product, Product obj) {

		if (obj.getName() != null) {
			product.setName(obj.getName());
		}
		if (obj.getDescription() != null) {
			product.setDescription(obj.getDescription());
		}
		product.setPrice(obj.getPrice());
		if (obj.getBrand() != null) {
			product.setBrand(obj.getBrand());
		}
	}

	public Product fromDTO(ProductDTO objDto) {
		return new Product(objDto.getId(), objDto.getName(), objDto.getDescription(), objDto.getPrice(),
				objDto.getBrand());
	}

	public List<Product> findByName(String nameProduct) {
		return repo.findByNameContainingIgnoreCase(nameProduct);
	}

	public List<Product> findAllOrder() {
		return repo.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
	
}
