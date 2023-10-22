
package com.mycompany.unidad3.BANCO_AZTECA;

import java.util.Comparator;

public class compararNames implements Comparator<Cuenta>{

    

    @Override
    public int compare(Cuenta cuenta1, Cuenta cuenta2) {
        int compararNames=cuenta1.getNombre_cliente().compareTo(cuenta2.getNombre_cliente());
        return compararNames;
        
    }
    
}
