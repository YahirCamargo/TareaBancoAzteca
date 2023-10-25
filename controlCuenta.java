
package com.mycompany.unidad3.BANCO_AZTECA;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;

public class controlCuenta {
    //cambios
    
    
    public ArrayList<Cuenta> cuentas=new ArrayList<Cuenta>();
    
    
    
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
        Collections.sort(cuentas, Collections.reverseOrder(new compararNumCuenta()));
        
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
        Collections.sort(cuentas, Collections.reverseOrder(new compararNumCuenta()));
        
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
        cuentaCorriente co;
        if (cuenta == null) {
            return "Cuenta no encontrada";
        }
        
           
        
        cuenta.abonarSaldo(abono);
        return "Saldo abonado, ahora tu saldo es: "+Double.toString(cuenta.getSaldo());
    }
    
    
    public String carSal(int nCuenta, double cargo){
        Cuenta cuenta = buscarCuentaNum(nCuenta);
        
        if (cuenta == null) {
            return "Cuenta no encontrada";
        }
        if (cuenta instanceof cuentaAhorro){
            if(cuenta.getSaldo()<cargo){
                return "No hay saldo suficiente para realizar esta operacion";
            }else{
                   cuenta.cargarSaldo(cargo);
                   return "Saldo cargado, ahora tu saldo es: "+Double.toString(cuenta.getSaldo()); 
                    }
        }else if(cuenta instanceof cuentaCorriente)
            if(cuenta.getSaldo()<cargo+(cargo*((cuentaCorriente)cuenta).getImporteTransaccion())){
                return "No hay saldo suficiente para realizar esta operacion";
            }else{
                ((cuentaCorriente) cuenta).setTransacciones(cargo);
                cuenta.cargarSaldo(cargo);
                return "Saldo cargado, ahora tu saldo es: "+Double.toString(cuenta.getSaldo());
        }
        
        
        return "Nada";
    }
    
    //debe de mostrar el saldo para que sepa cual es su nuevo saldo
    public Cuenta buscarCuentaNum(int nCuenta){
        
        for (Cuenta cuenta:cuentas){
            
            if (cuenta.getNumCuenta()== nCuenta){
                return cuenta;
            }
        }
        return null;
    }
    
    public String busCuenNumAho(int nCuenta){
        cuentaAhorro ca;
        
        Cuenta cuenta = buscarCuentaNum(nCuenta);
        if (cuenta == null) {
            return "Cuenta no encontrada";
        }
        ca=(cuentaAhorro)cuenta;
        return ca.toString();
    }
    
    public String busCuenNumCor(int nCuenta){
        cuentaCorriente cc;
        
        Cuenta cuenta = buscarCuentaNum(nCuenta);
        if (cuenta == null) {
            return "Cuenta no encontrada";
        }
        cc=(cuentaCorriente)cuenta;
        return cc.toString();
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
    public String cambiosCuentaAhorro(int nCuenta, int NewNCuenta, String NewNombre, double NewSaldo, double NewCuota) {
        
        cuentaAhorro ca;    
        
        Cuenta cuenta = buscarCuentaNum(nCuenta);
        if (cuenta == null) {
            return "Cuenta no encontrada";
        }
        ca=(cuentaAhorro)cuenta;
        ca.setNumCuenta(NewNCuenta);
        ca.setNombre_cliente(NewNombre);
        ca.setSaldo(NewSaldo);
        ca.setCuotaMantenimiento(NewCuota);
        // Actualizamos la cuenta en la lista
        cuentas.set(cuentas.indexOf(cuenta), cuenta);
        cuentas.set(cuentas.indexOf(cuenta), ca);

        return "Cuenta cambiada";
        
        
    
    }
    
    
    public String cambiosCuentaCorriente(int nCuenta, int NewNCuenta, String NewNombre, double NewSaldo, double NewImporte) {
        cuentaCorriente co;    
        Cuenta cuenta = buscarCuentaNum(nCuenta);

        if (cuenta == null) {
            return "Cuenta no encontrada";
        }
        co=(cuentaCorriente) cuenta;
        co.setNumCuenta(NewNCuenta);
        co.setNombre_cliente(NewNombre);
        co.setSaldo(NewSaldo);
        co.setImporteTransaccion(NewImporte);
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
