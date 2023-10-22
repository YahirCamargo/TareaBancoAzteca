
package com.mycompany.unidad3.BANCO_AZTECA;

import java.time.LocalDate;

public class cuentaCorriente extends Cuenta{
    private int transacciones;
    private double importeTransaccion;

    public cuentaCorriente(int numCuenta, String nombre_cliente, double saldo, int transacciones, double importeTransaccion) {
        super(numCuenta, nombre_cliente, saldo);
        this.transacciones=0;
        this.importeTransaccion = importeTransaccion; 
    }

    

    @Override
    public void comisiones() {
        if (LocalDate.now().getDayOfMonth() == 1) {
            double importeComisiones = transacciones * importeTransaccion;
            saldo -= importeComisiones;
        }
    }

    @Override
    public void intereses() {
        if (LocalDate.now().getDayOfMonth() == 1) {
            if (saldo > 20000) {
                saldo*= 1.1;
            } else if (saldo > 5000) {
                saldo*=1.05;
            }
        }
    }

    public int getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(int transacciones) {
        this.transacciones = transacciones;
    }

    public double getImporteTransaccion() {
        return importeTransaccion;
    }

    public void setImporteTransaccion(double importeTransaccion) {
        this.importeTransaccion = importeTransaccion;
    }

    @Override
    public String toString() {
        return ""+super.toString()
                + "Transacciones=" + transacciones 
                + ", Importe por transaccion=" + importeTransaccion;
    }
    
    
    
    
}
