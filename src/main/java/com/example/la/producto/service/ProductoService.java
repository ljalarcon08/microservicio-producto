package com.example.la.producto.service;

import org.springframework.data.domain.Pageable;

import com.example.la.producto.document.Producto;
import com.example.la.producto.domain.PaginaProducto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductoService extends GenericServ<Producto>{
	
	
	public Flux<Producto> findProductoByCatalogo(String idCatalogo,Pageable pageable);
	
	public Flux<Producto> findProductoByName(String name);
	
	public Mono<PaginaProducto> getPaginaProducto(Pageable pageable);
	
	public Mono<Producto> actualizaImagenProducto(String id,String img);
}
