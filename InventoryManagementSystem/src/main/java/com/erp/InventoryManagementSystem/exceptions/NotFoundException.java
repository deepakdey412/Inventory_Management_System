package com.erp.InventoryManagementSystem.exceptions;

public class NotFoundException extends  RuntimeException{
    NotFoundException(String message){
        super(message);
    }
}
