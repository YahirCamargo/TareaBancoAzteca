
package com.mycompany.unidad3.BANCO_AZTECA;

public abstract class Cuenta {
    // cambiar estos atributos a privados
    private int numCuenta;
    private String nombre_cliente;
    private double saldo;

    public Cuenta(int numCuenta, String nombre_cliente, double saldo) {
        this.numCuenta = numCuenta;
        this.nombre_cliente = nombre_cliente;
        this.saldo = saldo;
    }
    
    

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
     
    
    
    
    //metodos
    public void abonarSaldo(double monto){
        saldo+=monto;
        
    }
    
    public void cargarSaldo(double monto){
        if(saldo>=monto){
            saldo-=monto;
        }
        /*
            else{
                //regresar algo que diga que el saldo no es suficiente
            }
        */
        
    }
    
    
    public abstract void comisiones();
        
    
    public abstract void intereses();
    
    public abstract String formatoParaGuar();

    @Override
    public String toString() {
        return "# de cuenta=" + numCuenta 
                + ", Nombre del cliente=" + nombre_cliente 
                + ", Saldo=" + saldo+", ";
    }
      
}
