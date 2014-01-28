package com.ackbox.a2.service;

/**
 * Exception for catalog related operations.
 * 
 * @author trein
 * 
 */
public class CatalogException extends Exception {

    private static final long serialVersionUID = -6783946716741122352L;

    public CatalogException(String message) {
        super(message);
    }

    public CatalogException(String message, Throwable e) {
        super(message, e);
    }
}
