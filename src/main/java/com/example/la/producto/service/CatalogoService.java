package com.example.la.producto.service;

import com.example.la.producto.document.Catalogo;

import reactor.core.publisher.Mono;

public interface CatalogoService extends GenericServ<Catalogo>{

	
	public Mono<Catalogo> actualizaImagenCatalogo(String id,String url);
}
