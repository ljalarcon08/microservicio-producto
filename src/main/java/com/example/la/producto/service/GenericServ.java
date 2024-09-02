package com.example.la.producto.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericServ<T> {

	public Flux<T> getElements();
	
	public Mono<T> getElementById(String id);
	
	public Mono<T> crearElement(T element);
	
	public Mono<T> actualizarElement(T element);
	
	public Mono<Void> eliminaElement(String id);
	
}
