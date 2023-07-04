package com.springboot.webflux.controllers;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema.ConflictResolutionFunction.Path;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import com.springboot.webflux.dao.IProductoDAO;
import com.springboot.webflux.models.Categoria;
import com.springboot.webflux.models.Producto;
import com.springboot.webflux.services.CategoriaService;
import com.springboot.webflux.services.ProductoService;
import com.springboot.webflux.services.ProductoServiceImpl;

import lombok.Getter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ProductoController {

	@Autowired 
	private ProductoService service;
	
	@Autowired
	private CategoriaService categoriaService;
	
	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

	/*
	@Value("${config.uploads.path}")
	private String path;
	*/
	
	private String path = "/home/manuonda/Documents/";
	
	@GetMapping({"/","/listar"})
	public Mono<String> listar(Model model) {
		Flux<Producto> productos =  service.findAllConNombreUpperCase();
		productos.subscribe(producto  -> log.info(producto.toString()));
		
	     IReactiveDataDriverContextVariable listaReactiva =
                 new ReactiveDataDriverContextVariable(productos, 1);
		model.addAttribute("productos", listaReactiva);
		model.addAttribute("title", "Listado de Productos");
		return Mono.just("listar");
	}
	
	@ModelAttribute("categorias")
	public Flux<Categoria> getCategorias(){
		return this.categoriaService.findAll();
	}
	
	
	@GetMapping("/ver/{id}")
	public Mono<String> ver(Model model, @PathVariable String id) {
		return service.findById(id)
				.doOnNext( p -> {
					model.addAttribute("producto",p);
					model.addAttribute("titulo", "Detalle Producto");
				}).switchIfEmpty(Mono.just(new  Producto()))
				  .flatMap( p -> {
					 if (p.getId() == null) {
						 return Mono.error(new InterruptedException("No existe el producto"));
					 }	
					 return Mono.just(p);
				  }).then(Mono.just("ver"))
				  .onErrorResume(ex -> Mono.just("redirect:/listar?error=no+existe+el+producto"));	
	}
	
	@GetMapping("/uploads/img/{nombreFoto}")
	public Mono<ResponseEntity<Resource>> verFoto(@PathVariable String nombreFoto) throws MalformedURLException {
		java.nio.file.Path ruta = Paths.get(path).resolve(nombreFoto).toAbsolutePath();
		
		Resource imagen = new UrlResource(ruta.toUri());
		return Mono.just(
				 ResponseEntity.ok()
				 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \""+ imagen.getFilename() + "\"")
				 .body(imagen)
				);
	}
	
	
	@GetMapping("/form")
	public Mono<String> crear(Model model) {
		model.addAttribute("producto",new Producto());
		model.addAttribute("titule","Formulario de producto");
		return Mono.just("form");
	}
	
	
	
	@PostMapping("/form")
	public Mono<String> guardar(@Validated  Producto producto , BindingResult result , @RequestPart("file") FilePart file, Model model) {
		if (result.hasErrors() ) {
			model.addAttribute("titulo", "Errores en el formulario producto");
			model.addAttribute("boton", "Guardar");
			return Mono.just("form");
		} else {
			if ( producto.getCreateAt() == null ) {
				producto.setCreateAt(new Date());
			}
			
			Mono<Categoria> categoria = this.categoriaService.findById(producto.getCategoria().getId());
			if ( file.filename().isEmpty()) {
				producto.setFoto(UUID.randomUUID().toString() + "-"+ file.filename()
				.replace(" ", "")
				.replace(":", "")
				.replace("", ""));
			}
			categoria
			.doOnNext( c -> {
				 producto.setCategoria(c);
			});
		return service.save(producto)
			.doOnNext( p -> {
		        log.info("Save to guardado " + p.getNombre() + " Id : " + producto.getId());	
		        if (!file.filename().isEmpty()) {
		  	    	   file.transferTo(new File( path + p.getFoto()));
		  	       }	
	  	    })
			.flatMap( p -> {
				 if (!file.filename().isEmpty()) {
		  	    	   return  file.transferTo(new File(path + p.getFoto()));
		  	       }	
	  	       return Mono.empty();
				
			}).thenReturn("redirect:/listar");
		}
	}
	
	
	
	@GetMapping("/form/{id}")
	public Mono<String> edit(@PathVariable String id, Model model) {
		Mono<Producto> producto  = this.service.findById(id).defaultIfEmpty(new Producto());
		model.addAttribute("producto",producto);
		model.addAttribute("titulo","Editar Producto");
		return Mono.just("form");
	}
	
	@GetMapping("/form-v2/{id}")
	public Mono<String> editV2(@PathVariable String id, Model model) {
		 return service.findById(id).doOnNext( p -> {
			 log.info("Producto : "+ p.getId());
			 model.addAttribute("producto", p);
			 model.addAttribute("titulo", "Editar Producto");
		 }).defaultIfEmpty(new Producto())
		   .flatMap( p -> {
			   if ( p.getId() == null) {
				   return Mono.error( new InterruptedException());
			   }
			   return Mono.just(p);
		   }).then(Mono.just("form"))
		   .onErrorResume( ex -> Mono.just("redirect:/listar?error=no+existe+el+producto"));
	}
	
	@GetMapping("/eliminar/{id}")
	public Mono<String> eliminar(@PathVariable String id, Model model) {
		return service.findById(id)
				.defaultIfEmpty(new Producto())
				.flatMap( p ->  {
					if ( p.getId() == null ) {
						return Mono.error( new InterruptedException());
					}
					return Mono.just(p);
				})
				.flatMap(p -> {
					log.info("Delete producto :" + p.getId());
					log.info("Nombre producto : " + p.getNombre());
					return this.service.delete(p);
				})
				.then(Mono.just("redirect:/listar?success=producto+eliminado+correctamente"))
				.onErrorResume( ex -> Mono.just("redirect:/listar?error=no+existe+el+producto"));
	} 
	
	/**
	 * Data Driver para manejar 
	 * la contrapresion
	 * @param model
	 * @return
	 */
	@GetMapping("/listar-datadriver")
	public String listarDataDriver(Model model) {
		Flux<Producto> productos =  service.findAllConNombreUpperCase().delayElements(Duration.ofSeconds(1));
		
		productos.subscribe(producto  -> log.info(producto.toString()));
		
	     IReactiveDataDriverContextVariable listaReactiva =
                 new ReactiveDataDriverContextVariable(productos, 2); //2 : buffer a mostrar en pantalla por 2 
		model.addAttribute("productos", listaReactiva);
		model.addAttribute("title", "Listado de Productos");
		return "listar";
	}
	
	@GetMapping({"/listar-full"})
	public String listarFull(Model model) {
		Flux<Producto> productos =  service.findAllConNombreUpperCaseRepeat();
		
	    model.addAttribute("productos", productos);
		model.addAttribute("title", "Listado de Productos");
		return "listar";
	}
	
	@GetMapping({"/listar-chunked"})
	public String listarChunked(Model model) {
		Flux<Producto> productos =  service.findAllConNombreUpperCaseRepeat();
		
	    model.addAttribute("productos", productos);
		model.addAttribute("title", "Listado de Productos");
		return "listar-chunked";
	}
}
