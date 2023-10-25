
package com.mycompany.unidad3.BANCO_AZTECA;

import java.time.LocalDate;

public class cuentaCorriente extends Cuenta{
    private double transacciones;
    private double importeTransaccion;
    
    int num1; 
    String nombre1; 
    double saldo1;
    
    Cuenta cuenta;
    
    

    //quitar el int transaccion del constructor
    public cuentaCorriente(int numCuenta, String nombre_cliente, double saldo, double importeTransaccion) {
        super(numCuenta, nombre_cliente, saldo);
        num1=numCuenta;
        nombre1=nombre_cliente;
        saldo1=saldo;
        this.importeTransaccion = importeTransaccion; 
    }

    

    @Override
    public void comisiones() {
        if (LocalDate.now().getDayOfMonth() == 1) {
            double importeComisiones = transacciones * importeTransaccion;
            cuenta.setSaldo(cuenta.getSaldo()-importeComisiones);
        }
    }

    @Override
    public void intereses() {
        if (LocalDate.now().getDayOfMonth() == 1) {
            if (cuenta.getSaldo() > 20000) {
                cuenta.setSaldo(cuenta.getSaldo()*1.1);
                //leer bien esta parte ya que dice que si el saldo esta entre 5,000 y 10,000
            } else if (cuenta.getSaldo()>=5000&&cuenta.getSaldo()<=10000) {
                cuenta.setSaldo(cuenta.getSaldo()*1.05);
            }
            this.transacciones=0;
        }
    }

    public double getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(double transacciones) {
        this.transacciones = transacciones;
    }

    public double getImporteTransaccion() {
        return importeTransaccion;
    }

    public void setImporteTransaccion(double importeTransaccion) {
        this.importeTransaccion = importeTransaccion;
    }

    
    
    //agreagar para camabiar transacciones, y reiniciar cada mes las coutas, es decir que no se acumulen todas de una vez
    //sino reiniciar cada mes
    @Override
    public String formatoParaGuar() {
        return "C,"+num1+","+nombre1+","+saldo1+","+transacciones+","+importeTransaccion;
    }
    @Override
    public String toString() {
        return ""+super.toString()
                + "Transacciones=" + transacciones 
                + ", Importe por transaccion=" + importeTransaccion;
    }
    
    
    
    
}
