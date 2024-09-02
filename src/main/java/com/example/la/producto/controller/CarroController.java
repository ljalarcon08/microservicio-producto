package com.example.la.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.la.producto.document.Carro;
import com.example.la.producto.service.CarroService;
import com.example.la.producto.service.GenericServ;

import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(path = "/carro")
public class CarroController extends GenericController<Carro>{

	@Autowired
	private CarroService service;
	
	@Override
	public GenericServ<Carro> getService() {
		return service;
	}
	
	@GetMapping("/email/{email}")
	public Mono<Carro> getCarroByEmail(@PathVariable String email){
		return service.getCarroByEmail(email);
	}

}
