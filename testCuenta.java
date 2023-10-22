
package com.mycompany.unidad3.BANCO_AZTECA;

import java.util.Scanner;

public class testCuenta {
    public static void main(String[] args) {
        
        
        Scanner obj= new Scanner(System.in);
        controlCuenta cc =new controlCuenta();
        
        cc.agregar(new cuentaAhorro(1122, "Emiliano",20000, 100));
        cc.agregar(new cuentaCorriente(9432, "Yo", 20000, 0, 100));
        cc.agregar(new cuentaCorriente(1112, "Fulano", 15000, 100, 10));
        cc.agregar(new cuentaAhorro(112, "Lalo", 1200, 10));
        
    }
}
