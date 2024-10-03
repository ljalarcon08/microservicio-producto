package com.example.la.producto.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.la.producto.document.Producto;

import reactor.core.publisher.Flux;

@Repository
public interface ProductoRepository extends GenericRepo<Producto>{

	
	@Query("{idCatalogo:?0}")
	public Flux<Producto> findProductoByCatalogo(String idCatalogo,Pageable pageable);
	
	public Flux<Producto> findByName(String name);
	
	public Flux<Producto> findByNameStartingWith(String name);
	
	@Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
	public Flux<Producto> encuentraProductoPorNombre(String name);
	
	public Flux<Producto> findAllBy(Pageable pageable);
}
