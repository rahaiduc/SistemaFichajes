package com.sistemafichajes.domain.blockchain;

import java.util.Arrays;
import java.util.Date;

import com.sistemafichajes.Configuracion;
import com.sistemafichajes.domain.Fichaje;
import com.sistemafichajes.domain.blockchain.utilidades.UtilidadesFirma;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;


import com.google.common.primitives.Longs;

/*
 * La información principal en una transacción incluye:
 * 	- Hash de la transacción
 * 	- El emisor
 * 	- El destinatario
 * 	- La cantidad a ser transferida
 * 	- El timestamp de cuando fue creada
 * 	- La firma con la clave privada del emisor
 * */

public class Transaccion {

	// Hash de la transacción e identificador único de esta
	private byte[] hash;

	// Clave pública del emisor de la transacción
	private byte[] emisor;

	// Valor a ser transferido
	private Fichaje fichaje;

	// Firma con la clave privada para verificar que la transacción fue realmente
	// enviada por el emisor
	private byte[] firma;

	// Timestamp de la creación de la transacción en milisegundos desde el 1/1/1970
	private long timestamp;

	private boolean esCoinbase;

	public Transaccion() {
	}

	public Transaccion(byte[] emisor, Fichaje fichaje, byte[] firma) {
		this.esCoinbase = false;
		this.emisor = emisor;
		this.fichaje = fichaje;
		this.firma = firma;
		this.timestamp = System.currentTimeMillis();
		this.hash = calcularHashTransaccion();
	}

	// coinbase
	public Transaccion(byte[] receptor) {
		this.esCoinbase = true;
		this.fichaje = new Fichaje();
		this.timestamp = System.currentTimeMillis();
		this.hash = calcularHashTransaccion();
	}

	public byte[] getHash() {
		return hash;
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	public byte[] getEmisor() {
		return emisor;
	}

	public void setEmisor(byte[] emisor) {
		this.emisor = emisor;
	}

	/*public byte[] getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(byte[] destinatario) {
		this.destinatario = destinatario;
	}*/

	public Fichaje getFichaje() {
		return fichaje;
	}

	public void setFichaje(Fichaje fichaje) {
		this.fichaje = fichaje;
	}

	public byte[] getFirma() {
		return firma;
	}

	public void setFirma(byte[] firma) {
		this.firma = firma;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean getEsCoinbase() {
		return esCoinbase;
	}

	public void setEsCoinbase(boolean esCoinbase) {
		this.esCoinbase = esCoinbase;
	}

	/**
	 * El contenido de la transaccion y que es firmado por el emisor con su clave
	 * pública
	 * 
	 * @return byte[] Array de bytes representando el contenido de la transaccion
	 */
	public byte[] getContenidoTransaccion() {
		byte[] contenido = ArrayUtils.addAll(String.valueOf(fichaje).getBytes());
		contenido = ArrayUtils.addAll(contenido, emisor);
		contenido = ArrayUtils.addAll(contenido, Longs.toByteArray(timestamp));
		return contenido;
	}

	/**
	 * Calcular el hash del contenido de la transacción y que pasa a ser el
	 * identificar de la transacción
	 * 
	 * @return Hash SHA256
	 */
	public byte[] calcularHashTransaccion() {
		return DigestUtils.sha256(getContenidoTransaccion());
	}

	/**
	 * Comprobar si una transaccion es válida
	 * 
	 * @return true si tiene un hash válido y la firma es válida
	 */
	public boolean esValida() {

		if (this.fichaje ==null) {
			System.out.println("Fichaje inválido");
			return false;
		}

		if (this.firma == null) {
			System.out.println("Firma inválida");
			return false;
		}
		// verificar hash
		if (!Arrays.equals(getHash(), calcularHashTransaccion())) {
			System.out.println("Hash de transacción inválido");
			return false;
		}

		// no coinbase tx
		/*if (!this.esCoinbase) {
			if (this.emisor == null) {
				System.out.println("Emisor inválido");
				return false;
			}

			// verificar firma
			if (!UtilidadesFirma.validarFirma(getContenidoTransaccion(), getFirma(), emisor)) {
				System.out.println("Firma de transacción inválida");
				return false;
			}
		}*/
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Transaccion tr = (Transaccion) o;

		return Arrays.equals(hash, tr.hash);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(hash);
	}

	@Override
	public String toString() {
		return "{\nHash: " + Base64.encodeBase64String(hash) + ",\nEmisor: " + Base64.encodeBase64String(emisor) + ",\nFichaje: " + fichaje + ",\nFirma: " + Base64.encodeBase64String(firma)
				+ ",\nTimestamp: " + new Date(timestamp) + "\n}";
	}
}