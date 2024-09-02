package com.example.la.producto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.la.producto.document.Carro;
import com.example.la.producto.repository.CarroRepository;
import com.example.la.producto.repository.GenericRepo;
import com.example.la.producto.service.CarroService;

import reactor.core.publisher.Mono;

@Service
public class CarroServiceImpl extends GenericServImpl<Carro> implements CarroService{

	@Autowired
	private CarroRepository repository;
	
	@Override
	public GenericRepo<Carro> getRepository() {
		return repository;
	}

	@Override
	public Mono<Carro> getCarroByEmail(String email) {
		return repository.findByEmail(email);
	}

}
