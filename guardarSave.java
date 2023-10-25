
package com.mycompany.unidad3.BANCO_AZTECA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class guardarSave {
   private static final int NOMBRE=2, NCUENTA=1, SALDO=3, COMISIONTRAN=4, IMPORTETRAN=5, ULTIMOMES=6, COUTA=4, TRANS=4;
    ArrayList<Cuenta> db;
    
    public guardarSave(ArrayList<Cuenta> db){
        this.db=db;
    }
    
    public boolean recuperarCuentas(){
        try{
            File file= new File("cuentas.txt");
            FileReader fr= new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            
            String cadena;
            while((cadena=br.readLine())!=null){
                guardarFormatoDif(cadena);
            }
            br.close();
            fr.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean guardarFormatoDif(String cadena){
        String elements[]=cadena.split(",");
            if(elements[0].equals("A")){
                cuentaAhorro cuenAhorro= new cuentaAhorro( Integer.parseInt(elements[NCUENTA]), elements[NOMBRE], Float.parseFloat(elements[SALDO]),Float.parseFloat(elements[COUTA]));
                db.add(cuenAhorro);
                return true;
            }
            if(elements[0].equals("C")){
                cuentaCorriente cuenCorr= new cuentaCorriente( Integer.parseInt(elements[NCUENTA]),elements[NOMBRE], Float.parseFloat(elements[SALDO]), Double.parseDouble(elements[IMPORTETRAN]));
                //se supone que no va esto: ,Integer.parseInt(elements[TRANS])
                db.add(cuenCorr);
                return true;
            }
            return false;
    }
    
    public boolean guardarCuenta(){
        try{
            FileWriter fw= new FileWriter("cuentas.txt");
            PrintWriter pw= new PrintWriter(fw);
            for(Cuenta c:db){
                pw.println(c.formatoParaGuar());
            }
            pw.close();
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
}
