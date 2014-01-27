package com.ackbox.a2.model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.content.Context;

import com.ackbox.a2.R;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
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

    /**
     * Retrieve the list of {@link Displayable} persons stored in the loaded
     * catalog. It is worth to notice that returned list is immutable.
     * 
     * @return Immutable list of persons of current catalog.
     */
    public List<Displayable> getCurrentCatalogPersons() {
        return this.loadedCatalog.getPersons();
    }

    /**
     * Add a new person to the current loaded catalog.
     * 
     * @param context Current application context.
     * @param person New person instance to be added.
     * @throws CatalogException In case of invalid person instances.
     */
    public void addPerson(Context context, Person person) throws CatalogException {
        if (!person.isValid()) {
            throw new CatalogException(context.getString(R.string.invalid_person_message));
        }
        this.loadedCatalog.addPerson(person);
    }

    /**
     * Store current loaded catalog in the File System.
     * 
     * @param context Current application context.
     * @param fileName Name that will be used when persisting the catalog.
     * @throws CatalogException In case of invalid file name.
     */
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

    /**
     * Load catalog from File System to the memory.
     * 
     * @param context Current application context.
     * @param fileName File name that will be loaded.
     * @throws CatalogException In case of invalid file name.
     */
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

    /**
     * Retrieve the list of {@link Displayable} catalogs stored in the File
     * System.
     * 
     * @param context Current application context.
     * @return Immutable list containing all catalogs stored in the File System.
     * @throws CatalogException In case of errors during catalog loading.
     */
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
        return ImmutableList.copyOf(containers);
    }

    /**
     * Current catalog's name.
     * 
     * @return String representing current loaded catalog's name.
     */
    public String currentCatalogName() {
        return this.loadedCatalog.getFileName();
    }

    /**
     * Check is there is any unsaved changes in current loaded catalog.
     * 
     * @return {@code true} if current loaded catalog was modified and not
     *         saved, {@code false} otherwise.
     */
    public boolean hasUnsavedChanges() {
        // TODO Auto-generated method stub
        return true;
    }
}
