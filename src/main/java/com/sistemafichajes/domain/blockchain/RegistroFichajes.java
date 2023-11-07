package com.sistemafichajes.domain.blockchain;

import org.apache.commons.codec.binary.Base64;

import java.util.Enumeration;
import java.util.Hashtable;

public class RegistroFichajes {
    // Registro de saldos <encoded public key, saldo>
    private Hashtable<String, Double> fichajes = new Hashtable<String, Double>();

    public RegistroFichajes() {
    }

    public Hashtable<String, Double> getSaldos() {
        return fichajes;
    }

    public void setSaldos(Hashtable<String, Double> saldos) {
        this.fichajes = saldos;
    }

    public Double getSaldoCuenta(byte[] clavePublica) {

        return (this.fichajes.containsKey(convertirClaveAString(clavePublica)) ? this.fichajes.get(convertirClaveAString(clavePublica)) : 0.0);
    }

    public void setSaldoCuenta(byte[] clavePublica, Double saldo) {
        this.fichajes.put(convertirClaveAString(clavePublica), saldo);
    }

    public void añadeSaldoACuenta(byte[] clavePublica, Double saldo) {
        this.fichajes.put(convertirClaveAString(clavePublica), getSaldoCuenta(clavePublica) + saldo);
    }

    public void liquidarTransaccion(Transaccion transaccion) throws Exception {
        if(transaccion.getEsCoinbase()) {
            this.añadeSaldoACuenta(transaccion.getDestinatario(), transaccion.getCantidad());
        }
        else {
            if (getSaldoCuenta(transaccion.getEmisor()) >= transaccion.getCantidad()) {
                this.añadeSaldoACuenta(transaccion.getEmisor(), - transaccion.getCantidad());
                this.añadeSaldoACuenta(transaccion.getDestinatario(), transaccion.getCantidad());
            } else {
                throw new Exception("No hay suficiente saldo en cuenta emisor.");
            }
        }
    }

    public boolean existeCuenta(byte[] cuenta) {
        return this.fichajes.containsKey(convertirClaveAString(cuenta));
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        Enumeration<String> cuentas = fichajes.keys();
        buf.append("CLAVE PUBLICA | SALDO\n");
        buf.append("---------------------\n");
        while(cuentas.hasMoreElements()) {
            String cuenta = cuentas.nextElement();
            buf.append(cuenta.substring(0, 10) + "...");
            buf.append(" | ");
            buf.append(fichajes.get(cuenta));
            if(cuentas.hasMoreElements()) buf.append("\n");
        }
        return buf.toString();
    }

    private String convertirClaveAString(byte[] clave) {
        return Base64.encodeBase64String(clave);
    }
}
