package com.example.la.producto.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.la.producto.service.GenericServ;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

abstract class GenericController<T> {

	public abstract GenericServ<T> getService();
	
	@GetMapping
	public Flux<T> getAllElements(){
		return getService().getElements();
	}
	
	@GetMapping("/{id}")
	public Mono<T> getElementById(@PathVariable String id){
		return getService().getElementById(id);
	}
	
	@PostMapping("")
	public Mono<T> crearElement(@RequestBody T element){
		return getService().crearElement(element);
	}
	
	@PutMapping("/{id}")
	public Mono<T> actualizarElement(@PathVariable String id,@RequestBody T element){
		return getService().actualizarElement(element);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> eliminarElement(@PathVariable String id){
		return getService().eliminaElement(id);
	}
	
}
