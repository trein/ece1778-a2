package com.ackbox.a2;

/**
 * Service responsible for handling save/load of entries. It is a thread-safe
 * implementation of the Singleton pattern using enums.
 * 
 * @author trein
 * 
 */
public enum CatalogService {
    INSTANCE;

    private CatalogService() {

    }

    public void addPerson(Person person) throws CatalogException {
        if (!person.isValid()) {
            throw new CatalogException("Invalid person to be saved");
        }

    }

}
