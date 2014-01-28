package com.ackbox.a2.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;

import com.ackbox.a2.R;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

/**
 * Simple class representing a collection of persons.
 * 
 * @author trein
 * 
 */
public class PersonCatalog implements Displayable {

    private static final String DEFAULT_FILE_NAME = "new_entry_list.data";
    private static final DateFormat DATE_FORMATTER = SimpleDateFormat.getDateInstance();

    private final List<Person> persons = Lists.newArrayList();

    private boolean hasChanged = false;
    private String date = DATE_FORMATTER.format(new Date());
    private String fileName = DEFAULT_FILE_NAME;

    private PersonCatalog() {
    }

    /**
     * Creates an empty catalog.
     * 
     * @return Empty catalog.
     */
    public static PersonCatalog emptyCatalog() {
        return new PersonCatalog();
    }

    @Override
    public String getTitle(Context context) {
        return String.format(context.getResources().getString(R.string.catalog_title_pattern), this.fileName);
    }

    @Override
    public String getDetail(Context context) {
        String pattern = context.getResources().getString(R.string.catalog_detail_pattern);
        return String.format(pattern, this.date, this.persons.size());
    }

    /**
     * Add new person to catalog
     * 
     * @param person {@link Person} to be appended to catalog.
     */
    public void addPerson(Person person) {
        if (isStored()) {
            throw new IllegalStateException("Current catalog cannot be modified.");
        }
        this.hasChanged = true;
        this.persons.add(person);
    }

    /**
     * Retrieve the list of {@link Displayable} persons stored in the catalog.
     * It is worth to notice that returned list is immutable.
     * 
     * @return Immutable list of persons of current catalog.
     */
    public List<Displayable> getPersons() {
        return ImmutableList.<Displayable> copyOf(this.persons);
    }

    /**
     * Current catalog's name.
     * 
     * @return String representing current catalog's name.
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Set catalog's filename before save it.
     * 
     * @param filename File name that will be used to store the current catalog.
     */
    public void setCatalogAsStored(String filename) {
        if (isStored()) {
            throw new IllegalStateException("Current catalog cannot be modified.");
        }
        this.hasChanged = false;
        this.fileName = filename;
        this.date = DATE_FORMATTER.format(new Date());
    }

    /**
     * Return the size of current catalog (number of persons).
     * 
     * @return Number of persons currently stored in the catalog.
     */
    public int size() {
        return this.persons.size();
    }

    /**
     * Indicate if the current catalog has been modified but no saved yet.
     * 
     * @return {@code true} if current catalog has been modified and not saved,
     *         and {@code false} otherwise.
     */
    public boolean hasChanges() {
        return this.hasChanged;
    }

    /**
     * Indicate if current catalog has been stored.
     * 
     * @return {@code true} if current catalog has been stored, and
     *         {@code false} otherwise.
     */
    public boolean isStored() {
        return !this.fileName.equals(DEFAULT_FILE_NAME);
    }

    /**
     * Serialize current catalog to a JSON {@link String}.
     * 
     * @return JSON {@link String} representing the state of current catalog.
     */
    public String toJSON() {
        return new Gson().toJson(this);
    }

    /**
     * Create a new catalog from the content of a JSON {@link String}.
     * 
     * @param json JSON {@link String} representing the state of a catalog.
     * @return New catalog with restored state from JSON.
     */
    public static PersonCatalog fromJSON(String json) {
        return new Gson().fromJson(json, PersonCatalog.class);
    }

}
