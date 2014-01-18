package com.ackbox.a2;

import android.content.Context;

import com.google.common.base.Strings;

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

    public void addPerson(Context context, Person person) throws CatalogException {
        if (!person.isValid()) {
            throw new CatalogException(context.getString(R.string.invalid_person_message));
        }
    }

    public void saveEntryList(Context context, String filename) throws CatalogException {
        if (Strings.isNullOrEmpty(filename)) {
            throw new CatalogException(context.getString(R.string.invalid_file_name_message));
        }
    }
}
