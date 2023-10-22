
package com.mycompany.unidad3.BANCO_AZTECA;

import java.util.Comparator;

public class compararNumCuenta implements Comparator<Cuenta>{

    @Override
    public int compare(Cuenta cuenta1, Cuenta cuenta2) {
        int compararNumCuenta=cuenta1.getNumCuenta()-cuenta2.getNumCuenta();
        return compararNumCuenta;
    }
    
}
