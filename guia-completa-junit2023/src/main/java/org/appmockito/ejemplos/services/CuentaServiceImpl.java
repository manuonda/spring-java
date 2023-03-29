package org.appmockito.ejemplos.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.appmockito.ejemplos.models.Banco;
import org.appmockito.ejemplos.models.Cuenta;
import org.appmockito.ejemplos.repository.IBancoRepository;
import org.appmockito.ejemplos.repository.ICuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaServiceImpl implements CuentaService{
	
	@Autowired
    private ICuentaRepository cuentaRepository;
	
	
    @Autowired
	private IBancoRepository bancoRepository;



    @Override
    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id).get();
    }

    @Override
    public int revisarTotalTransferencias(Long bancoId) {
        Banco banco = bancoRepository.findById(bancoId).get();
        return banco.getTotalTransferencias();
    }

    @Override
    public BigDecimal revisarSaldo(Long cuentaId) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(cuentaId);
        if ( cuenta.isPresent()) {
        	return cuenta.get().getSaldo();
        }
        return new BigDecimal(0);
    }

    @Override
    public void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto,
                           Long bancoId) {

        Optional<Cuenta> cuentaOrigen = cuentaRepository.findById(numCuentaOrigen);
        cuentaOrigen.get().debito(monto);
        cuentaRepository.save(cuentaOrigen.get());

        Optional<Cuenta> cuentaDestino = cuentaRepository.findById(numCuentaDestino);
        cuentaDestino.get().credito(monto);
        cuentaRepository.save(cuentaDestino.get());

        Banco banco = bancoRepository.findById(bancoId).get();
        int totalTransferencias = banco.getTotalTransferencias();
        banco.setTotalTransferencias(++totalTransferencias);
        bancoRepository.save(banco);
    }
}