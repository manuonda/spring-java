package com.springboot.webflux;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.springboot.webflux.models.Categoria;
import com.springboot.webflux.models.Producto;
import com.springboot.webflux.service.CategoriaService;
import com.springboot.webflux.service.ProductoService;
import com.springboot.webflux.service.ProductoServiceImpl;

import reactor.core.publisher.Mono;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootWebfluxApirestApplicationTests {

	
	@Autowired
	private WebTestClient client;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	

	
	@Value("${config.base.endpoint}")
	private String url;
	
	@Test
	void contextLoads() {
	}

	
	@Test
	public void listarTest() {
		client.get()
		.uri(url)
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(Producto.class)
		.consumeWith( response -> {
			List<Producto> productos = response.getResponseBody();
			productos.forEach(p -> {
				System.out.println(p.getNombre());
			});
			
			 // Assertions.assertThat(productos.size() == 8  );
			Assertions.assertThat(productos.size() > 0  );

			
		});
		//.hasSize(9)
		;
		
	
	}
	
	@Test
	public void verTest() {
		Producto producto = this.productoService.findByNombre("TV Panasonic Pantalla LCD").block();
		client.get()
		.uri("/api/v2/productos/ver/{id}", Collections.singletonMap("id", producto.getId()))
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody(Producto.class)
		.consumeWith(consumer -> {
		     	Producto p = consumer.getResponseBody();
		     	System.out.println(producto);
		     	Assertions.assertThat(p.getId()).isNotEmpty();
		     	Assertions.assertThat(p.getNombre()).isEqualTo("TV Panasonic Pantalla LCD");
		});
		/*.expectBody()
		.jsonPath("$.id").isNotEmpty()
		.jsonPath("$.nombre").isEqualTo("TV Panasonic Pantalla LCD")
		*/;
	}
	
	@Test 
	public void crearTest() {
		Categoria categoria = this.categoriaService.findByNombre("ELectronico").block();
		
		Producto producto = new Producto("Mesa Comedor", Double.valueOf(987),categoria );
		 client.post()
		.uri("/api/v2/productos")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(producto), Producto.class)
		.exchange()
		.expectStatus().isCreated()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody()
		.jsonPath("$.id").isNotEmpty()
		.jsonPath("$.nombre").isEqualTo("Mesa Comedor")
		.jsonPath("$.categoria.nombre").isEqualTo("ELectronico");
	}
	
	@Test 
	public void crearTest2() {
		Categoria categoria = this.categoriaService.findByNombre("ELectronico").block();
		
		Producto producto = new Producto("Mesa Comedor", Double.valueOf(987),categoria );
		 client.post()
		.uri("/api/v2/productos")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(producto), Producto.class)
		.exchange()
		.expectStatus().isCreated()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody(Producto.class)
		.consumeWith( response -> {
			    Producto p =  response.getResponseBody();
			   Assertions.assertThat(p.getId()).isNotEmpty();
			   Assertions.assertThat(p.getNombre()).isEqualTo("Mesa Comedor");
		});
	}
	
	@Test 
	public void editar() {
        Categoria categoria = this.categoriaService.findByNombre("ELectronico").block();
		
		Producto producto = this.productoService.findByNombre("TV Panasonic Pantalla LCD").block();
		
		Producto productoEditado =  new Producto("Asus Notebook",799.00, categoria);
		
		client.put()
		.uri("/api/v2/productos/{id}",Collections.singletonMap("id", producto.getId()))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(productoEditado),Producto.class)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody()
		.jsonPath("$.id").isNotEmpty()
		.jsonPath("$.nombre").isNotEmpty();
		
		
		
		
	}
	
	@Test 
	public void eliminar() {
		Producto producto = this.productoService.findByNombre("TV Panasonic Pantalla LCD").block();
		client.delete()
		.uri("/api/v2/productos/{id}", Collections.singletonMap("id", producto.getId()))
		.exchange()
		.expectStatus().isNoContent()
		.expectBody()
		.isEmpty();
		
		// Not Found
		client.delete()
		.uri("/api/v2/productos/{id}", Collections.singletonMap("id", producto.getId()))
		.exchange()
		.expectStatus().isNotFound()
		.expectBody()
		.isEmpty();
	}
}
