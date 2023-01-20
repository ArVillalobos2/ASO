package com.minsait.exceptions;

public class DineroInsuficienteException extends RuntimeException{
    public DineroInsuficienteException(String mensaje){
        super(mensaje);
    }
}
