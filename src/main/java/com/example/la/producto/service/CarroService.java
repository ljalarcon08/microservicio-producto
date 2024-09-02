package com.example.la.producto.service;

import com.example.la.producto.document.Carro;

import reactor.core.publisher.Mono;

public interface CarroService extends GenericServ<Carro>{

	public Mono<Carro> getCarroByEmail(String email);
}
