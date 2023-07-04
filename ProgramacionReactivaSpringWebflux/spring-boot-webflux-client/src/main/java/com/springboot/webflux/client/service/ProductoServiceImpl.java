package com.springboot.webflux.client.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.webflux.client.models.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private WebClient.Builder webClient;
	
	
	
	@Override
	public Flux<Producto> findAll() {
        return webClient.build().get()
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .flatMapMany(response -> response.bodyToFlux(Producto.class));
		
	}

	@Override
	public Flux<Producto> findAllConNombreUpperCase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Producto> findAllConNombreUpperCaseRepeat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Producto> findById(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.webClient.build().get().uri("/ver/{id}",params)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Producto.class);
//				.exchange()
//				.flatMap(response ->response.bodyToMono(Producto.class));
	}

	@Override
	public Mono<Producto> save(Producto producto) {
		return this.webClient.build().post()
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(producto)
				.retrieve()
				.bodyToMono(Producto.class);
	}

	

	@Override
	public Mono<Producto> findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Mono<Void> delete(String id) {
		return 
				this.webClient.build().delete()
				.uri("/{id}", Collections.singletonMap("id", id))
				.retrieve()
				.bodyToMono(Void.class);
				
	}

	@Override
	public Mono<Producto> update(Producto producto, String id) {
		
	    return this.webClient.build().put()
	    		.uri("/{id}",Collections.singletonMap("id", id))
	    		.accept(MediaType.APPLICATION_JSON)
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.bodyValue(producto)
	    		.retrieve()
	    		.bodyToMono(Producto.class);
	    		
		
	}

	@Override
	public Mono<Producto> upload(FilePart file, String id) {
		MultipartBodyBuilder parts = new MultipartBodyBuilder();
		parts.asyncPart("file", file.content(), DataBuffer.class)
		.headers( h -> {
			h.setContentDispositionFormData("file", file.filename());
		});
		
		return this.webClient.build().post()
				.uri("/uploadFile/{id}", Collections.singletonMap("id", id))
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.bodyValue(parts.build())
				.retrieve()
				.bodyToMono(Producto.class);

	}



	
}
