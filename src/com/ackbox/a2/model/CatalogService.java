package com.ackbox.a2.model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.content.Context;

import com.ackbox.a2.R;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * Service responsible for handling save/load of entries. It is a thread-safe
 * implementation of the Singleton pattern using enums.
 * 
 * @author trein
 * 
 */
public enum CatalogService {
    INSTANCE;

    private PersonCatalog loadedCatalog;

    private CatalogService() {
        this.loadedCatalog = new PersonCatalog();
    }

    public List<Displayable> getCurrentCatalogPersons() {
        return this.loadedCatalog.getPersons();
    }

    public void addPerson(Context context, Person person) throws CatalogException {
        if (!person.isValid()) {
            throw new CatalogException(context.getString(R.string.invalid_person_message));
        }
        this.loadedCatalog.addPerson(person);
    }

    public void saveCurrentCatalog(Context context, String fileName) throws CatalogException {
        if (Strings.isNullOrEmpty(fileName)) {
            throw new CatalogException(context.getString(R.string.invalid_file_name_message));
        }
        try {
            this.loadedCatalog.setFileName(fileName);
            Utils.writeToFile(context, fileName, this.loadedCatalog.toJSON());
        } catch (IOException e) {
            new CatalogException(context.getResources().getString(R.string.error_writing_file), e);
        }
    }

    public void loadCurrentCatalog(Context context, String fileName) throws CatalogException {
        if (Strings.isNullOrEmpty(fileName)) {
            throw new CatalogException(context.getString(R.string.invalid_file_name_message));
        }

        try {
            this.loadedCatalog = PersonCatalog.fromJSON(Utils.readFromFile(context, fileName));
        } catch (IOException e) {
            new CatalogException(context.getResources().getString(R.string.error_reading_file_message), e);
        }
    }

    public List<Displayable> getStoredCatalogFiles(Context context) throws CatalogException {
        File filesDir = context.getFilesDir();
        List<Displayable> containers = Lists.newArrayList();

        for (String fileName : filesDir.list()) {
            try {
                containers.add(PersonCatalog.fromJSON(Utils.readFromFile(context, fileName)));
            } catch (IOException e) {
                new CatalogException(context.getResources().getString(R.string.error_reading_file_message), e);
            }
        }
        return containers;
    }

    public String currentCatalogName() {
        return this.loadedCatalog.getFileName();
    }

    public boolean hasUnsavedChanges() {
        // TODO Auto-generated method stub
        return true;
    }
}
