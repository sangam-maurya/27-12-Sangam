package com.main.excpection;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String msg){
        super(msg);
    }
}
