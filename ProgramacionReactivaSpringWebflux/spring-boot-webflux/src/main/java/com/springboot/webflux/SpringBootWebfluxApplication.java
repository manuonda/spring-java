package com.springboot.webflux;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.springboot.webflux.dao.ICategoriaDAO;
import com.springboot.webflux.dao.IProductoDAO;
import com.springboot.webflux.models.Categoria;
import com.springboot.webflux.models.Producto;
import com.springboot.webflux.services.CategoriaService;
import com.springboot.webflux.services.ProductoService;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApplication  implements CommandLineRunner{

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	@Autowired
	private ReactiveMongoTemplate reactiveMongoTemplate;
	
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// delete documents
		reactiveMongoTemplate.dropCollection("productos").subscribe();
		reactiveMongoTemplate.dropCollection("categorias").subscribe();
		
		Categoria electronico = new Categoria("ELectronico");
		Categoria deporte = new Categoria("Deporte");
		Categoria computacion = new Categoria("Computacion");
		Flux.just(
				electronico,
				deporte,
				computacion
				).flatMap( c ->  {
					return categoriaService.save(c);
				}).doOnNext( c -> log.info("Catgoria Save  :" + c.getId() + " nombre : "+c.getNombre()))
		  .thenMany(
					// lo aplana es decir lo convierte al Object Producto en vez de Map devuelve un Observable Mono<Producto>
					Flux.just(
							new Producto("TV Panasonic Pantalla LCD", 456.98 ,electronico),
							new Producto("Sony Camara HD Digital", 2500.89,electronico),
							new Producto("Apple TV", 2589.89,electronico),
							new Producto("Mica Comoda 5 Cajones", 150.989,computacion),
							new Producto("Apple Watch", 2352.23, computacion)
							
							)
					.flatMap(producto ->  { 
						 producto.setCreateAt(new Date());
						return  productoService.save(producto); 
						})  
					)
			.subscribe( producto -> log.info("Producto Save" + producto.getId() +  " nombre : " + producto.getNombre()));

		
		
	
		
	
	}

}
