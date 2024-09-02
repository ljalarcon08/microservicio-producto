package com.example.la.producto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.la.producto.document.Producto;
import com.example.la.producto.domain.PaginaProducto;
import com.example.la.producto.repository.GenericRepo;
import com.example.la.producto.repository.ProductoRepository;
import com.example.la.producto.service.ProductoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoServiceImpl extends GenericServImpl<Producto> implements ProductoService{

	
	@Autowired
	private ProductoRepository repository;
	
	@Override
	public GenericRepo<Producto> getRepository() {
		return repository;
	}

	@Override
	public Flux<Producto> findProductoByCatalogo(String idCatalogo,Pageable pageable) {
		
		return repository.findProductoByCatalogo(idCatalogo,pageable);
	}
	
	@Override
	public Mono<PaginaProducto> getPaginaProducto(Pageable pageable) {
		return repository.findAllBy(pageable).collectList()
				.zipWith(this.repository.count())
				.map(p->new PaginaProducto(p.getT1(),p.getT2()));
	}

	@Override
	public Mono<Producto> actualizaImagenProducto(String id, String img) {
		return repository.findById(id).map(prod->{
			prod.setImg(img);
			return prod;
		}).flatMap(repository::save);
	}

	@Override
	public Flux<Producto> findProductoByName(String name) {
		return repository.encuentraProductoPorNombre(name);
	}
	
}
