package com.example.la.producto.repository;

import org.springframework.stereotype.Repository;

import com.example.la.producto.document.Carro;

import reactor.core.publisher.Mono;

@Repository
public interface CarroRepository extends GenericRepo<Carro>{

	public Mono<Carro> findByEmail(String email);
	
}
