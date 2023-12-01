package com.sistemafichajes.application.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sistemafichajes.Configuracion;
import com.sistemafichajes.domain.blockchain.Bloque;
import com.sistemafichajes.domain.blockchain.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ServiceTransacciones {

	// Pool de transacciones con transacciones pendientes de ser incluidas en un bloque
	private List<Transaccion> poolTransacciones = new ArrayList<>();
	/*Autowired
	private ServiceBloques serviceBloques;*/



	public List<Transaccion> getPoolTransacciones() {
		return poolTransacciones;
	}

	/**
	 * A�adir transaccion al pool
	 * 
	 * @param transaccion Transaccion a ser añadida
	 * @throws Exception 
	 */
	public synchronized void añadirTransaccion(Transaccion transaccion) throws Exception {
		if (transaccion.esValida()) {
			/*if (poolTransacciones.size() < Configuracion.getInstancia().getMaxNumeroTransaccionesEnBloque() - 1) {
				poolTransacciones.add(transaccion);
			} else {
				poolTransacciones.add(transaccion);
				Bloque ultimoBloque = serviceBloques.getCadenaDeBloques().getUltimoBloque();
				byte[] hashUltimoBloque =  ultimoBloque!= null
						? ultimoBloque.getHash()
						: null;
				for(Transaccion t:poolTransacciones){
					this.serviceBloques.getCadenaDeBloques().getFichajes().liquidarTransaccion(t);
				}
				Bloque bloque = new Bloque(hashUltimoBloque, poolTransacciones, 10);
				serviceBloques.añadirBloque(bloque);
				serviceNodo.emitirPeticionPostNodosVecinos("bloque", bloque);
			}*/
			poolTransacciones.add(transaccion);
		}else {
			throw new Exception("Transacción inválida");
		}
	}

	/**
	 * Eliminar una transacci�n del pool
	 * 
	 * @param transaccion Transaccion a ser eliminada
	 */
	public void eliminarTransaccion(Transaccion transaccion) {
		poolTransacciones.remove(transaccion);
	}

	/**
	 * Comprobar si el pool contiene una lista de transacciones
	 * 
	 * @param transacciones Transacciones a ser verificadas
	 * @return true si todas las transacciones est�n en el pool
	 */
	public boolean contieneTransacciones(Collection<Transaccion> transacciones) {
		return poolTransacciones.containsAll(transacciones);
	}
	
	/**
	 * Descargar pool de transacciones desde otro nodo
	 * 
	 * @param urlNodo Nodo al que pedir las transacciones
	 * @param restTemplate RestTemplate a usar
	 */
	public void obtenerPoolTransacciones(URL urlNodo, RestTemplate restTemplate) {
		List<Transaccion> poolTransacciones = restTemplate.getForObject(urlNodo + "/transaccion", List.class);
		this.poolTransacciones = poolTransacciones;
		System.out.println("Obtenido pool de transacciones de nodo " + urlNodo + ".\n");
	}
	public boolean estaVacio() {
		return this.poolTransacciones == null || this.poolTransacciones.isEmpty();
	}
}