package com.ackbox.a2.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.content.Context;

import com.ackbox.a2.R;
import com.ackbox.a2.common.Utils;
import com.ackbox.a2.model.Displayable;
import com.ackbox.a2.model.Person;
import com.ackbox.a2.model.PersonCatalog;
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

    private PersonCatalog sessionCatalog;

    private CatalogService() {
        this.sessionCatalog = PersonCatalog.emptyCatalog();
    }

    /**
     * Return current session catalog.
     * 
     * @return Current session catalog.
     */
    public PersonCatalog getCurrentCatalog() {
        return this.sessionCatalog;
    }

    /**
     * Check is there is any unsaved changes in current loaded catalog.
     * 
     * @return {@code true} if current loaded catalog was modified and not
     *         saved, {@code false} otherwise.
     */
    public boolean hasUnsavedChanges() {
        return this.sessionCatalog.hasChanges();
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
        this.sessionCatalog.addPerson(person);
    }

    /**
     * Store current loaded catalog in the File System and reset it so that new
     * catalogs can be created.
     * 
     * @param context Current application context.
     * @param fileName Name that will be used when persisting the catalog.
     * @throws CatalogException In case of invalid file name.
     */
    public void saveCurrentAndCreateNewCatalog(Context context, String fileName) throws CatalogException {
        saveCatalog(context, fileName, this.sessionCatalog);
        this.sessionCatalog = PersonCatalog.emptyCatalog();
    }

    /**
     * Store a catalog in the File System.
     * 
     * @param context Current application context.
     * @param fileName Name that will be used when persisting the catalog.
     * @param catalog {@link PersonCatalog} to be saved.
     * @throws CatalogException In case of invalid file name.
     */
    public void saveCatalog(Context context, String fileName, PersonCatalog catalog) throws CatalogException {
        if (Strings.isNullOrEmpty(fileName)) {
            throw new CatalogException(context.getString(R.string.invalid_file_name_message));
        }
        try {
            catalog.setCatalogAsStored(fileName);
            Utils.writeToFile(context, fileName, catalog.toJSON());
        } catch (IOException e) {
            throw new CatalogException(context.getResources().getString(R.string.error_writing_file), e);
        }
    }

    /**
     * Load catalog from File System to the memory.
     * 
     * @param context Current application context.
     * @param fileName File name that will be loaded.
     * @return loaded {@link PersonCatalog} from File System.
     * @throws CatalogException In case of invalid file name.
     */
    public PersonCatalog loadCatalog(Context context, String fileName) throws CatalogException {
        PersonCatalog catalog = null;

        if (Strings.isNullOrEmpty(fileName)) {
            throw new CatalogException(context.getString(R.string.invalid_file_name_message));
        }

        try {
            catalog = PersonCatalog.fromJSON(Utils.readFromFile(context, fileName));
        } catch (IOException e) {
            throw new CatalogException(context.getResources().getString(R.string.error_reading_file_message), e);
        }
        return catalog;
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
                throw new CatalogException(context.getResources().getString(R.string.error_reading_file_message), e);
            }
        }
        return ImmutableList.copyOf(containers);
    }

}
