package com.minsait.model;

import com.minsait.exceptions.DineroInsuficienteException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode

public class Cuenta {

    private String persona;
    private BigDecimal saldo;
    private Banco banco;

    //ProjectLombok.com podemos revisar las anotaciones

    public Cuenta(String persona, BigDecimal saldo){
        this.saldo=saldo;
        this.persona=persona;
    }

    public void retirar(BigDecimal monto){
        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO)<0){
            throw new DineroInsuficienteException("DineroInsuficiente");
        }

        this.saldo = nuevoSaldo;
    }

    public void depositar(BigDecimal monto){
        this.saldo = this.saldo.add(monto);
    }
}
