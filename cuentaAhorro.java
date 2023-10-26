package com.mycompany.unidad3.BANCO_AZTECA;

import java.time.LocalDate;

public class cuentaAhorro extends Cuenta{
    private double cuotaMantenimiento;
    
    
    int num1; 
    String nombre1; 
    double saldo1;

    
    
    public cuentaAhorro(int numCuenta, String nombre_cliente, double saldo, double cuotaMantenimiento){
        super(numCuenta, nombre_cliente,saldo);
        num1=numCuenta;
        nombre1=nombre_cliente;
        saldo1=saldo;
        this.cuotaMantenimiento=cuotaMantenimiento;
    }

    public double getCuotaMantenimiento() {
        return cuotaMantenimiento;
    }

    public void setCuotaMantenimiento(double cuotaMantenimiento) {
        this.cuotaMantenimiento = cuotaMantenimiento;
    }
    
    
    
    @Override
    public void comisiones() {
        if (LocalDate.now().getDayOfMonth()==1){
            super.setSaldo(super.getSaldo()-cuotaMantenimiento);
        }
    }
    @Override
    public void intereses() {
        if (LocalDate.now().getDayOfMonth()==1){
            super.setSaldo(super.getSaldo()-cuotaMantenimiento);
        }
    }

    @Override
    public String toString() {
        return ""+super.toString()+ "Cuota de mantenimiento= " + cuotaMantenimiento;
    }
    
    @Override
    public String formatoParaGuar() {
        return "A,"+super.getNumCuenta()+","+super.getNombre_cliente()+","+super.getSaldo()+","+cuotaMantenimiento;
    }
    
    
    
}
