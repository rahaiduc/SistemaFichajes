package com.sistemafichajes.domain.blockchain;

import com.sistemafichajes.domain.Fichaje;
import org.apache.commons.codec.binary.Base64;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class RegistroFichajes {
    // Registro de saldos <encoded public key, saldo>
    private Hashtable<String, List<Fichaje>> fichajes = new Hashtable<String, List<Fichaje>>();

    public RegistroFichajes() {
    }

    public Hashtable<String, List<Fichaje>> getSaldos() {
        return fichajes;
    }

    public void setFichajes(Hashtable<String, List<Fichaje>> fichajes) {
        this.fichajes = fichajes;
    }

    public List<Fichaje> getFichajeCuenta(byte[] clavePublica) {

        return (this.fichajes.containsKey(convertirClaveAString(clavePublica)) ? this.fichajes.get(convertirClaveAString(clavePublica)) : new ArrayList<Fichaje>());
    }

    public void setFichajeCuenta(byte[] clavePublica, List<Fichaje> fichaje) {
        this.fichajes.put(convertirClaveAString(clavePublica), fichaje);
    }

    public void añadeFichajeACuenta(byte[] clavePublica, Fichaje fichaje) {
        this.getFichajeCuenta(clavePublica).add(fichaje);
        this.fichajes.put(convertirClaveAString(clavePublica), this.getFichajeCuenta(clavePublica));
    }

    public void liquidarTransaccion(Transaccion transaccion) {
            this.añadeFichajeACuenta(transaccion.getEmisor(), transaccion.getFichaje());
    }

    public boolean existeCuenta(byte[] cuenta) {
        return this.fichajes.containsKey(convertirClaveAString(cuenta));
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        Enumeration<String> cuentas = fichajes.keys();
        buf.append("CLAVE PUBLICA | FICHAJES\n");
        buf.append("---------------------\n");
        while(cuentas.hasMoreElements()) {
            String cuenta = cuentas.nextElement();
            buf.append(cuenta.substring(0, 10) + "...");
            buf.append(" | ");
            buf.append(fichajes.get(cuenta).toString());
            if(cuentas.hasMoreElements()) buf.append("\n");
        }
        return buf.toString();
    }

    private String convertirClaveAString(byte[] clave) {
        return Base64.encodeBase64String(clave);
    }
}
