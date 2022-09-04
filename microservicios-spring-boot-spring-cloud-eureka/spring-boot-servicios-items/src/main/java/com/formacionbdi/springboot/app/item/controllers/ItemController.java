package com.formacionbdi.springboot.app.item.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.services.ItemService;
import com.spring.app.commons.models.entity.Producto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.vavr.collection.Map;


//@RefreshScope
@RestController
@RequestMapping("/api/items")
public class ItemController {
	
	private Logger logger= LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private CircuitBreakerFactory cbFactory;
	
	@Autowired 
	Environment env;
	
    //@Value("${configuracion.texto}")
	//private String texto;

	@Autowired
	// con el qualifier me permite indicar que servicio trabajar
	@Qualifier("serviceRestTemplate")
	private ItemService itemService;
	

 
	
	
	@GetMapping("/")
	public String home() {
		return "hola mundo";
	}
//	@GetMapping("/listar")
//	public List<Item> listar(
//			@RequestParam(name="nombre" , required=false) String nombre, 
//			@RequestHeader(name="token-request", required =  false) String token
//	 ){
//		System.out.println("Nombre : " + nombre);
//		System.out.println("token-request: " +token);
//		return itemService.findAll();
//	}
	
	@GetMapping("/listar")
	public List<Item> listar(
			
	 ){
		return itemService.findAll();
	}
	
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item itemDetalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		// cbFactory : el 2do parametro llamara al error y le pasa como argumento un metodo alternativo
		return cbFactory.create("items")
				.run(() -> itemService.findById(id, cantidad), e ->metodoAlternativo(id, cantidad, e ));
	}
	

	/**
	 * Annotations solamente usamos 
	 * application.yaml o properties es la configuraicon y lo toma desde alli
	 * @param id
	 * @param cantidad
	 * @return
	 */
	
	@CircuitBreaker(name="items",fallbackMethod = "metodoAlternativo") //application.yaml 
	@GetMapping("/ver2/{id}/cantidad/{cantidad}")
	public Item itemDetalle2(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
	@CircuitBreaker(name="items", fallbackMethod = "metodoAlternativo2") //application.yaml 
	@TimeLimiter(name="items") //application.yaml 
	@GetMapping("/ver3/{id}/cantidad/{cantidad}")
	public CompletableFuture<Item> itemDetalle3(@PathVariable Long id, @PathVariable Integer cantidad) {
		return CompletableFuture.supplyAsync(() -> itemService.findById(id, cantidad));
	}
	
	

	public Item metodoAlternativo(Long id, Integer cantidad, Throwable e ) {
		logger.info(e.getMessage());
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Camara Sony");
		producto.setPrecio(500.00);
		item.setProducto(producto);
		return item;
	}
	
	
	public CompletableFuture<Item> metodoAlternativo2(Long id, Integer cantidad, Throwable e ) {
		logger.info(e.getMessage());
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Camara Sony");
		producto.setPrecio(500.00);
		item.setProducto(producto);
		return CompletableFuture.supplyAsync( ()-> item);
	}
	
	@GetMapping("/get-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto) {
		//logger.info(texto);
	    HashMap<String, String> json = new HashMap<>();
		//json.put("texto",texto);
		json.put("puerto", puerto);
		//json.put("configuracionAutor",configuracionAutorNombre);
		
		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
		   	json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
		   	json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		
		return new ResponseEntity<HashMap<String,String>>(json, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto create(@RequestBody Producto producto) {
		Producto newProduct = this.itemService.save(producto);
		return newProduct;
	}
	
	@PutMapping("/update/{idProducto}")
	public ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable("id") Long idProducto) {
		Producto updateProducto = this.itemService.update(producto,  idProducto);
		return ResponseEntity.status(HttpStatus.CREATED).body(updateProducto);
	}
	
	@DeleteMapping("/delete/{idProducto}")
	public BodyBuilder delete(@PathVariable("idProducto") Long idProducto){
		this.itemService.delete(idProducto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT);
	}
}
