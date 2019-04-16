package com.cmc.demo.exception;

public enum BusinessErrorCodeE {
    UnHandledException (0),
  
    ValidationInvalidParameter(40000001),
    
    InternalServerError(10100001),
    DBConcurrency(20100001),
    VMSModuleDoorOpen(16100001),
    
    BookNotFound(90900001),
    BookExsited(90900002);
    
    private final int id;
    BusinessErrorCodeE (int id) { this.id = id; }
    public int getValue() { return id; }
}