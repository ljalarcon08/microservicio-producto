package com.example.la.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.la.producto.document.Catalogo;
import com.example.la.producto.domain.ImagenRequest;
import com.example.la.producto.service.CatalogoService;
import com.example.la.producto.service.GenericServ;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="/catalogo")
public class CatalogoController extends GenericController<Catalogo>{

	@Autowired
	private CatalogoService service;

	@Override
	public GenericServ<Catalogo> getService() {
		return service;
	}
	
	@PutMapping(path="/imagen/{id}")
	public Mono<Catalogo> actualizaImagenCatalogo(@PathVariable String id,@RequestBody ImagenRequest imagenRequest){
		return service.actualizaImagenCatalogo(id, imagenRequest.getImg());
	}
}
