package com.example.la.producto.domain;

import java.util.List;

import com.example.la.producto.document.Producto;

public class PaginaProducto {
	
	public PaginaProducto(List<Producto> productos,Long total) {
		this.productos=productos;
		this.totalRegistros=total;
	}

	private List<Producto> productos;
	private Long totalRegistros;
	
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public Long getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	
	
}
