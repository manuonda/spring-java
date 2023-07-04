package com.springboot.reactor.app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.reactor.app.models.Comentarios;
import com.springboot.reactor.app.models.Usuario;
import com.springboot.reactor.app.models.UsuarioComentarios;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class SpringWebfluxSeccionApplication implements CommandLineRunner{

	
	private static final Logger log = LoggerFactory.getLogger(SpringWebfluxSeccionApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxSeccionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Ejemplo aqui que onda");
		//ejemploFlatMap();
		//ejemploCollectList();
		// ejemploZipWithRangos();
		
	}
	
	
	public void ejemploZipWithRangos() {
		Flux<Integer> rangos =  Flux.range(0,4);
		
		Flux.just(1,2,3,4).map( i -> (i*2))
		.zipWith(rangos,(uno,dos) -> String.format("Prime Flux %d , Segundo Flux :%d", uno,dos))
		.subscribe( texto -> log.info(texto));
	}
	
	public void ejemploUsuarioComentarioZipWithFormat2() {
		Mono<Usuario> usuarioMono = Mono.fromCallable(() -> {
			return new Usuario("David","Garcia");
		});
		
		Mono<Comentarios> comentariosUsuarioMono = Mono.fromCallable(() -> {
			 Comentarios comentarios = new Comentarios();
			 comentarios.addComentario("Que onda numero 1");
			 comentarios.addComentario("Comentarios numero 2");
			 return comentarios;
		});
		
		Mono<UsuarioComentarios> usuarioComentarios =  usuarioMono.zipWith(comentariosUsuarioMono, (usuario, comentarioUsuario) -> new UsuarioComentarios(usuario, comentarioUsuario))
		;
		usuarioComentarios.subscribe( uc -> log.info(uc.toString()));
	}
	
	public void ejemploUsuarioComentarioZipWith() {
		Mono<Usuario> usuarioMono = Mono.fromCallable(() -> {
			return new Usuario("David","Garcia");
		});
		
		Mono<Comentarios> comentariosUsuarioMono = Mono.fromCallable(() -> {
			 Comentarios comentarios = new Comentarios();
			 comentarios.addComentario("Que onda numero 1");
			 comentarios.addComentario("Comentarios numero 2");
			 return comentarios;
		});
		
		Mono<UsuarioComentarios> usuarioComentarios =  usuarioMono
				.zipWith(comentariosUsuarioMono)
				.map(tuple -> {
					Usuario u = tuple.getT1();
					Comentarios c = tuple.getT2();
					return new UsuarioComentarios(u, c);
					
				})
		;
		usuarioComentarios.subscribe( uc -> log.info(uc.toString()));
	}
	
	public void ejemploUsuarioComentarioFlatMap() {
		Mono<Usuario> usuarioMono = Mono.fromCallable(() -> {
			return new Usuario("David","Garcia");
		});
		
		Mono<Comentarios> comentariosUsuarioMono = Mono.fromCallable(() -> {
			 Comentarios comentarios = new Comentarios();
			 comentarios.addComentario("Que onda numero 1");
			 comentarios.addComentario("Comentarios numero 2");
			 return comentarios;
		});
		
		usuarioMono.flatMap( u -> comentariosUsuarioMono.map( c -> new UsuarioComentarios(u,c)))
		.subscribe( uc -> log.info(uc.toString()));
	}
	
	
	public void ejemploCollectList() {
		//Flux<String> nombres=  Flux.just("Andres Garcia","David Garcia");
	    List<Usuario> usuariosList = new ArrayList<>();
	    usuariosList.add(new Usuario("Andres","Garcia"));
	    usuariosList.add(new Usuario("David", "Garcia"));
	    usuariosList.add(new Usuario("Raquel","Garcia"));
	    
	    Flux.fromIterable(usuariosList)
	    .collectList()
	    .subscribe(list ->  {
	    	list.forEach(item -> log.info(item.toString()));
	      });
	}
	
	
	public void ejemploToString() {
		//Flux<String> nombres=  Flux.just("Andres Garcia","David Garcia");
	    List<Usuario> usuariosList = new ArrayList<>();
	    usuariosList.add(new Usuario("Andres","Garcia"));
	    usuariosList.add(new Usuario("David", "Garcia"));
	    usuariosList.add(new Usuario("Raquel","Garcia"));
	    
	    Flux.fromIterable(usuariosList)
	    		.map(usuario -> usuario.getNombre().toUpperCase().concat(" ").concat(usuario.getApellido().toUpperCase()))
				.flatMap(nombre -> {
					//log.info(nombre);
					if(nombre.contains("ANDRES")) {
						return Mono.just(nombre);
					} else {
						return Mono.empty();
					}
				})
				
				.map( nombre ->  {
					return nombre.toLowerCase();
				})
				.subscribe(e -> log.info(e.toString()));
	}
	
	public void ejemploFlatMap() {
		//Flux<String> nombres=  Flux.just("Andres Garcia","David Garcia");
	    List<String> usuariosList = new ArrayList<>();
	    usuariosList.add("Andres Garcia");
	    usuariosList.add("David Garcia");
	    
	    Flux.fromIterable(usuariosList)
	    		.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(),nombre.split(" ")[1].toUpperCase()))
				.flatMap(usuario -> {
					log.info(usuario.getNombre());
					if(usuario.getNombre().equals("ANDRES")) {
						return Mono.just(usuario);
					} else {
						return Mono.empty();
					}
				})
				
				.map( usuario ->  {
					String nombre = usuario.getNombre().toLowerCase();
			        usuario.setNombre(nombre);
			        return usuario;
				})
				.subscribe(e -> log.info(e.toString()));
	}
	
	public void ejemploIterable() {
		//Flux<String> nombres=  Flux.just("Andres Garcia","David Garcia");
	    List<String> usuariosList = new ArrayList<>();
	    usuariosList.add("Andres Garcia");
	    usuariosList.add("David Garcia");
	    
	    Flux<String> nombres = Flux.fromIterable(usuariosList);
	    
		Flux<Usuario> usuarios = nombres.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(),nombre.split(" ")[1].toUpperCase()))
				.filter(usuario -> usuario.getNombre().toLowerCase().equals("andres"))
				.doOnNext( elemento -> log.info("Antes {} ",elemento))
				.map( usuario ->  {
					String nombre = usuario.getNombre().toLowerCase();
			        usuario.setNombre(nombre);
			        return usuario;
				});
		
		usuarios.subscribe(e -> log.info(e.toString()),
				error -> log.error(error.getMessage()),
			new Runnable() {
				
				@Override
				public void run() {
					log.info("Ha finlizado la ejecucion del Observable con exito");
					
				}
			});
	}

}
