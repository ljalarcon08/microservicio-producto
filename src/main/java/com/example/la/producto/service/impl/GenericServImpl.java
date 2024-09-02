package com.example.la.producto.service.impl;

import com.example.la.producto.repository.GenericRepo;
import com.example.la.producto.service.GenericServ;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

abstract class GenericServImpl<T> implements GenericServ<T>{

	
	public abstract GenericRepo<T> getRepository();
	
	@Override
	public Flux<T> getElements() {
		return getRepository().findAll();
	}

	@Override
	public Mono<T> getElementById(String id) {
		return getRepository().findById(id);
	}

	@Override
	public Mono<T> crearElement(T element) {
		return getRepository().save(element);
	}

	@Override
	public Mono<T> actualizarElement(T element) {
		return getRepository().save(element);
	}

	@Override
	public Mono<Void> eliminaElement(String id) {
		return getRepository().deleteById(id);
	}

}
