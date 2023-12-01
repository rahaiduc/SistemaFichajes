package com.sistemafichajes.domain.blockchain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * La cadena de bloques es esencialmente una lista de bloques enlazados ya que cada bloque tiene el identificador del bloque anterior.
 * */
public class CadenaDeBloques {

	// Lista de bloques en la cadena ordenados por altura
	private List<Bloque> bloques = new ArrayList<Bloque>();
	// Saldos actuales de las cuentas
	private RegistroFichajes fichajes = new RegistroFichajes();

	public CadenaDeBloques() {
	}

	public CadenaDeBloques(CadenaDeBloques cadena) throws Exception {
		this.setBloques(cadena.getBloques());
	}

	public List<Bloque> getBloques() {
		return bloques;
	}

	public void setBloques(List<Bloque> bloques) throws Exception {
		this.bloques = new ArrayList<Bloque>();
		for (Bloque bloque : bloques) {
			this.añadirBloque(bloque);
		}
	}

	public boolean estaVacia() {
		return this.bloques == null || this.bloques.isEmpty();
	}

	public int getNumeroBloques() {
		return (estaVacia() ? 0 : this.bloques.size());
	}

	public RegistroFichajes getFichajes() {
		return this.fichajes;
	}

	/**
	 * Obtener el ultimo bloque en la cadena
	 * 
	 * @return Ultimo bloque de la cadena
	 */
	public Bloque getUltimoBloque() {
		if (estaVacia()) {
			return null;
		}
		return this.bloques.get(this.bloques.size() - 1);
	}

	/**
	 * Añadir un bloque a la cadena
	 * @param bloque a ser añadido
	 * @throws Exception
	 */
	public void añadirBloque(Bloque bloque) throws Exception {

		this.bloques.add(bloque);

		System.out.println(fichajes.toString() + "\n");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CadenaDeBloques cadena = (CadenaDeBloques) o;

		if (bloques.size() != cadena.getBloques().size())
			return false;

		for (int i = 0; i < bloques.size(); i++) {
			if (bloques.get(i) != cadena.getBloques().get(i))
				return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return bloques.toString();
	}
}