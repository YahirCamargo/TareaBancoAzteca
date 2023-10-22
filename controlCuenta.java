
package com.mycompany.unidad3.BANCO_AZTECA;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;

public class controlCuenta {
    //cambios
    
    //estructura dinamica para guardar cuentas ArrayList
    
    // investigar el instanceOff para el reporte por categoria 
    //y con eso separar cuenta ahorro o cuenta corriente
    private ArrayList<Cuenta> cuentas=new ArrayList<Cuenta>();
    
    public String agregar(Cuenta cuenta){
        
       
        cuentas.add(cuenta);
        return "Cuenta agregada";
    }
    
    
    public String reporte(){
        String cadena="";
        for(Cuenta c: cuentas){
            cadena+=c.toString()+"\n";
        }
        return cadena;
    }
    
    
    public String reporteXNombre(){
        String cadena="";
        Collections.sort(cuentas, new compararNames());
        
        for(Cuenta c: cuentas){
            cadena+=c.toString()+"\n";
        }
        return cadena;
    }
    
    
    public String reporteXNombreInverso(){
        String cadena="";
        Collections.sort(cuentas, Collections.reverseOrder(new compararNames()));
        
        for(Cuenta c: cuentas){
            cadena+=c.toString()+"\n";
        }
        return cadena;
    }
    
    
    public String reporteXCuenta(){
        String cadena="";
        
        Collections.sort(cuentas, new compararNumCuenta());
        for(Cuenta c: cuentas){
            cadena+=c.toString()+"\n";
        }
        return cadena;
    }
    
    
    public void lista(){
        for (Cuenta c : cuentas) {
            if (c instanceof cuentaAhorro) {
                System.out.println(c.toString());
            }
        }
    }
    
    
    public String reporteCategorias(){
        cuentaAhorro ca;
        cuentaCorriente co;
        
        String cAhorro="Cuentas ahorro:\n";
        String cCorriente="Cuentas corrientes:\n";
        Collections.sort(cuentas, new compararNumCuenta());
        for(int i=0; i<cuentas.size();i++){
            if(cuentas.get(i) instanceof cuentaAhorro){
                ca=(cuentaAhorro)cuentas.get(i);
                cAhorro+=ca.toString()+"\n";
            }else if(cuentas.get(i) instanceof cuentaCorriente){
                co=(cuentaCorriente)cuentas.get(i);
                cCorriente+=co.toString()+"\n";
            }
        }
        return cAhorro+"\n\n"+cCorriente;
    }
    
    
    
    public String reporteCategoriasInverso(){
        cuentaAhorro ca;
        cuentaCorriente co;
        
        String cAhorro="Cuentas ahorro:\n";
        String cCorriente="Cuentas corrientes:\n";
        Collections.sort(cuentas, Collections.reverseOrder(new compararNames()));
        
        for(int i=0; i<cuentas.size();i++){
            if(cuentas.get(i) instanceof cuentaAhorro){
                ca=(cuentaAhorro)cuentas.get(i);
                cAhorro+=ca.toString()+"\n";
            }else if(cuentas.get(i) instanceof cuentaCorriente){
                co=(cuentaCorriente)cuentas.get(i);
                cCorriente+=co.toString()+"\n";
            }
        }
        return cAhorro+"\n\n"+cCorriente;
    }
    
    
    public String abonSal(int nCuenta, double abono){
        Cuenta cuenta = buscarCuentaNum(nCuenta);
        
        if (cuenta == null) {
            return "Cuenta no encontrada";
        }
        
        cuenta.abonarSaldo(abono);
        return "Saldo abonado";
    }
    
    
    public String carSal(int nCuenta, double cargo){
        Cuenta cuenta = buscarCuentaNum(nCuenta);
        
        if (cuenta == null) {
            return "Cuenta no encontrada";
        }
        cuenta.cargarSaldo(cargo);
        return "Saldo cargado";
        
    }
    
    
    public Cuenta buscarCuentaNum(int nCuenta){
        
        for (Cuenta cuenta:cuentas){
            
            if (cuenta.getNumCuenta()== nCuenta){
                return cuenta;
            }
        }
        return null;
    }
    
    
    public Cuenta buscarCuentaNom(String nomCliente){
        
        for (Cuenta cuenta:cuentas){
            if(cuenta.getNombre_cliente().equals(nomCliente)){
                return cuenta;
            }
        }
        return null;
    }
    
    
    
    
    //corregir este metodo, no hace ningun cambio
    public String cambiosCuentaAhorro(int nCuenta, double nCuota) {
        cuentaAhorro ca;    

    //esta inconcluso ya que no se como obtener el atributo faltante encontrado en la
        //clase cuentaAhorro
        Cuenta cuenta = buscarCuentaNum(nCuenta);
        
        
        
        if (cuenta == null) {
            return "Cuenta no encontrada";
        }
        ca=(cuentaAhorro)cuenta;
        ca.setCuotaMantenimiento(nCuota);
        // Actualizamos la cuenta en la lista
        cuentas.set(cuentas.indexOf(cuenta), cuenta);
        cuentas.set(cuentas.indexOf(cuenta), ca);

        return "Cuenta cambiada";
        
        
    
    }
    
    //corregir este metodo, no hace ningun cambio
    public String cambiosCuentaCorriente(int nCuenta, double nImporte) {
        cuentaCorriente co;    

    //esta inconcluso ya que no se como obtener el atributo faltante encontrado en la
        //clase cuentaAhorro
        Cuenta cuenta = buscarCuentaNum(nCuenta);

        if (cuenta == null) {
            return "Cuenta no encontrada";
        }
        co=(cuentaCorriente) cuenta;
        co.setImporteTransaccion(nImporte);
        // Actualizamos la cuenta en la lista
        cuentas.set(cuentas.indexOf(cuenta), cuenta);
        cuentas.set(cuentas.indexOf(cuenta), co);

        return "Cuenta cambiada";
    }
    
    
    
    public String eliminarCuenta(int numCuenta) {
        Cuenta cuenta = buscarCuentaNum(numCuenta);

        if (cuenta == null) {
            return "No hay cuenta";
        }

        cuentas.remove(cuenta);
        return "Cuenta removida";
    }
    
    
    
    public DefaultTableModel reporteNormalTablaGeneral() {
        Cuenta cuenta;
        cuentaAhorro ca;
        cuentaCorriente co;

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("TIPO");
        dtm.addColumn("#CUENTA");
        dtm.addColumn("NAME");
        dtm.addColumn("SALDO");
        dtm.addColumn("CUOTA");
        dtm.addColumn("TRANSACCIONES");
        dtm.addColumn("IMPORTE");
        for (int i = 0; i < cuentas.size(); i++) {
            cuenta = cuentas.get(i);

            if (cuenta instanceof cuentaAhorro) {
                ca=(cuentaAhorro)cuenta;
                dtm.addRow(new Object[]{
                    "Ahorro",
                    cuenta.getNumCuenta(),
                    cuenta.getNombre_cliente(),
                    cuenta.getSaldo(),
                    ca.getCuotaMantenimiento(),
                    null,
                    null
                });
            } else if (cuenta instanceof cuentaCorriente) {
                co = (cuentaCorriente) cuenta;
                dtm.addRow(new Object[]{
                    "Corriente",
                    cuenta.getNumCuenta(),
                    cuenta.getNombre_cliente(),
                    cuenta.getSaldo(),
                    null,
                    co.getTransacciones(),
                    co.getImporteTransaccion()
                });
            }
        }

        return dtm;
    }
    
    public DefaultTableModel reporteNormalTablaOrdenada() {
        Cuenta cuenta;
        cuentaAhorro ca;
        cuentaCorriente co;

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("TIPO");
        dtm.addColumn("#CUENTA");
        dtm.addColumn("NAME");
        dtm.addColumn("SALDO");
        dtm.addColumn("CUOTA");
        dtm.addColumn("TRANSACCIONES");
        dtm.addColumn("IMPORTE");
        //Diagoan diagonal Ordenar
        Collections.sort(cuentas, new compararNames());
        for (int i = 0; i < cuentas.size(); i++) {
            cuenta = cuentas.get(i);

            if (cuenta instanceof cuentaAhorro) {
                ca=(cuentaAhorro)cuenta;
                dtm.addRow(new Object[]{
                    "Ahorro",
                    cuenta.getNumCuenta(),
                    cuenta.getNombre_cliente(),
                    cuenta.getSaldo(),
                    ca.getCuotaMantenimiento(),
                    null,
                    null
                });
            } else if (cuenta instanceof cuentaCorriente) {
                co = (cuentaCorriente) cuenta;
                dtm.addRow(new Object[]{
                    "Corriente",
                    cuenta.getNumCuenta(),
                    cuenta.getNombre_cliente(),
                    cuenta.getSaldo(),
                    null,
                    co.getTransacciones(),
                    co.getImporteTransaccion()
                });
            }
        }

        return dtm;
    }
    
    
    public DefaultTableModel reporteNormalTablaOrdenadaInversa() {
        Cuenta cuenta;
        cuentaAhorro ca;
        cuentaCorriente co;

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("TIPO");
        dtm.addColumn("#CUENTA");
        dtm.addColumn("NAME");
        dtm.addColumn("SALDO");
        dtm.addColumn("CUOTA");
        dtm.addColumn("TRANSACCIONES");
        dtm.addColumn("IMPORTE");
        //Diagoan diagonal Ordenar
        Collections.sort(cuentas, Collections.reverseOrder(new compararNames()));
        for (int i = 0; i < cuentas.size(); i++) {
            cuenta = cuentas.get(i);

            if (cuenta instanceof cuentaAhorro) {
                ca=(cuentaAhorro)cuenta;
                dtm.addRow(new Object[]{
                    "Ahorro",
                    cuenta.getNumCuenta(),
                    cuenta.getNombre_cliente(),
                    cuenta.getSaldo(),
                    ca.getCuotaMantenimiento(),
                    null,
                    null
                });
            } else if (cuenta instanceof cuentaCorriente) {
                co = (cuentaCorriente) cuenta;
                dtm.addRow(new Object[]{
                    "Corriente",
                    cuenta.getNumCuenta(),
                    cuenta.getNombre_cliente(),
                    cuenta.getSaldo(),
                    null,
                    co.getTransacciones(),
                    co.getImporteTransaccion()
                });
            }
        }

        return dtm;
    }
    
    
    
    public DefaultTableModel reporteCategoriaTabla() {
        Cuenta cuenta;
        cuentaAhorro ca;
        cuentaCorriente co;

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("TIPO");
        dtm.addColumn("#CUENTA");
        dtm.addColumn("NAME");
        dtm.addColumn("SALDO");
        dtm.addColumn("CUOTA");
        dtm.addColumn("TRANSACCIONES");
        dtm.addColumn("IMPORTE");
        //Diagoan diagonal Ordenar
        Collections.sort(cuentas, new compararNumCuenta());
        for (int i = 0; i < cuentas.size(); i++) {
            cuenta = cuentas.get(i);

            if (cuenta instanceof cuentaAhorro) {
                ca=(cuentaAhorro)cuenta;
                dtm.addRow(new Object[]{
                    "Ahorro",
                    cuenta.getNumCuenta(),
                    cuenta.getNombre_cliente(),
                    cuenta.getSaldo(),
                    ca.getCuotaMantenimiento(),
                    null,
                    null
                });
            } 
        }
        Collections.sort(cuentas, new compararNumCuenta());
        for (int i = 0; i < cuentas.size(); i++) {
            cuenta = cuentas.get(i);
            
            if (cuenta instanceof cuentaCorriente) {
                co = (cuentaCorriente) cuenta;
                dtm.addRow(new Object[]{
                    "Corriente",
                    cuenta.getNumCuenta(),
                    cuenta.getNombre_cliente(),
                    cuenta.getSaldo(),
                    null,
                    co.getTransacciones(),
                    co.getImporteTransaccion()
                });
            }
        }
        

        return dtm;
    }
    
    
    public DefaultTableModel reporteCategoriaTablaInversa() {
        Cuenta cuenta;
        cuentaAhorro ca;
        cuentaCorriente co;

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("TIPO");
        dtm.addColumn("#CUENTA");
        dtm.addColumn("NAME");
        dtm.addColumn("SALDO");
        dtm.addColumn("CUOTA");
        dtm.addColumn("TRANSACCIONES");
        dtm.addColumn("IMPORTE");
        //Diagoan diagonal Ordenar
        Collections.sort(cuentas, Collections.reverseOrder(new compararNames()));
        for (int i = 0; i < cuentas.size(); i++) {
            cuenta = cuentas.get(i);

            if (cuenta instanceof cuentaAhorro) {
                ca=(cuentaAhorro)cuenta;
                dtm.addRow(new Object[]{
                    "Ahorro",
                    cuenta.getNumCuenta(),
                    cuenta.getNombre_cliente(),
                    cuenta.getSaldo(),
                    ca.getCuotaMantenimiento(),
                    null,
                    null
                });
            } 
        }
        Collections.sort(cuentas, Collections.reverseOrder(new compararNames()));
        for (int i = 0; i < cuentas.size(); i++) {
            cuenta = cuentas.get(i);
            
            if (cuenta instanceof cuentaCorriente) {
                co = (cuentaCorriente) cuenta;
                dtm.addRow(new Object[]{
                    "Corriente",
                    cuenta.getNumCuenta(),
                    cuenta.getNombre_cliente(),
                    cuenta.getSaldo(),
                    null,
                    co.getTransacciones(),
                    co.getImporteTransaccion()
                });
            }
        }
        

        return dtm;
    }
    
}
