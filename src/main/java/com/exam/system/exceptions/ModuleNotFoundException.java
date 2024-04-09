package com.exam.system.exceptions;

public class ModuleNotFoundException extends RuntimeException{
    public ModuleNotFoundException() {
        super("Module didn't exist");
    }
}
