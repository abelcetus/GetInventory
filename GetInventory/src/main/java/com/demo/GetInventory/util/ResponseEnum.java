package com.demo.GetInventory.util;

public enum ResponseEnum {
    RESPONSE_OK("Datos obtenidos Correctamente"),
    RESPONSE_ERROR("Ocurrio un error interno en el servidor"),

    RESPONSE_NULL_PARAM("El parametro enviado esta vacio"),

    RESPONSE_INCOPATIBLE_PARAM("El parametro es incompatible"),

    RESPONSE_ERROR_CREDENTIAL("Error de credenciales"),

    RESPONSE_NOT_FOUND("Inventario no encontrado");
    private String message;
    ResponseEnum(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}