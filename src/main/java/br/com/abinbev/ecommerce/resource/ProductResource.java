package br.com.abinbev.ecommerce.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.abinbev.ecommerce.domain.Product;
import br.com.abinbev.ecommerce.dto.ProductDTO;
import br.com.abinbev.ecommerce.resource.util.URL;
import br.com.abinbev.ecommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<Product> list = service.findAll();
		List<ProductDTO> listDto = list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductDTO> findById(@PathVariable String id) {
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(new ProductDTO(obj));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ProductDTO objDto) {
		Product obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ProductDTO objDto, @PathVariable String id) {
		Product obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/findname", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> findByName(@RequestParam(value = "text", defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List<Product> productList = service.findByName(text);
		return ResponseEntity.ok().body(productList); 
		
	}

	// busca ordenada da lista
	@RequestMapping(value = "/findordered",method = RequestMethod.GET)
	public ResponseEntity<List<ProductDTO>> findAllOrder() {
		List<Product> list = service.findAllOrder();
		List<ProductDTO> listDto = list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
}
