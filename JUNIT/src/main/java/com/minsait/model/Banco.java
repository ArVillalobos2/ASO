package com.minsait.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class Banco {
    private String nombre;
    private List<Cuenta> cuentas;

    public Banco(){
        this.cuentas = new ArrayList<>();
    }

    public void addCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
        cuenta.setBanco(this);
    }

    public void transferir(Cuenta origen, Cuenta destino, BigDecimal monto){
        origen.retirar(monto);
        destino.depositar(monto);
    }
}
