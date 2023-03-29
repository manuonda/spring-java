package org.appmockito.ejemplos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.appmockito.ejemplos.Datos;
import org.appmockito.ejemplos.models.Cuenta;
import org.appmockito.ejemplos.repository.IBancoRepository;
import org.appmockito.ejemplos.repository.ICuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CuentaServiceImplTest {

	@Mock
	private ICuentaRepository iCuentaRepository;
	
	@Mock
	private IBancoRepository iBancoRepository;
	
	@InjectMocks
	private CuentaServiceImpl cuentaService;
	
	@BeforeEach
	public void init() {
		
	}
	
	@Test
	@DisplayName("Test findById")
	public void testGetObjectExamen_whenFindById_thenReturnObject() {
		//Given
		Cuenta cuenta = Cuenta.builder().persona("Persona numero 1").saldo(new BigDecimal(1000)).build();
		Cuenta cuentarResultado = this.iCuentaRepository.save(cuenta);
		//when 
		Optional<Cuenta> optional =  this.iCuentaRepository.findById(cuenta.getId());
		Cuenta cuentaFindById = null;
		if (optional.isPresent()) {
			cuentaFindById = optional.get();
		}
		//when(this.iCuentaRepository.findById(cuenta.getId())).thenReturn(cuentaFindById);
		
		when(this.iCuentaRepository.getById(1L)).thenReturn(Datos.CUENTA_001);
		when(this.iCuentaRepository.getById(2L)).thenReturn(Datos.CUENTA_002);
		when(this.iBancoRepository.getById(1L)).thenReturn(Datos.BANCO);
		
		BigDecimal saldoOrigen = this.cuentaService.revisarSaldo(1L);
		BigDecimal saldoDestino = this.cuentaService.revisarSaldo(2L);
		assertEquals(1000, saldoOrigen);
		assertEquals(2000, saldoDestino);
		
	} 
}
