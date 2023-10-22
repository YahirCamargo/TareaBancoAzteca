package com.mycompany.unidad3.BANCO_AZTECA;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class cuentaAhorro extends Cuenta{
    public double cuotaMantenimiento;
    
     public List<Cuenta> lista = new ArrayList<>();

    
    
    public cuentaAhorro(int numCuenta, String nombre_cliente, double saldo, double cuotaMantenimiento){
        super(numCuenta, nombre_cliente, saldo);
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
            saldo-=cuotaMantenimiento;
        }
    }
    @Override
    public void intereses() {
        if (LocalDate.now().getDayOfMonth()==1){
            saldo*=1.15;
        }
    }

    @Override
    public String toString() {
        return ""+super.toString()+ "Cuota de mantenimiento= " + cuotaMantenimiento;
    }
    
    
    public void cambiosCuotaMantenimiento(double nuevaCuotaMantenimiento){
        this.cuotaMantenimiento=nuevaCuotaMantenimiento;
    }
}
