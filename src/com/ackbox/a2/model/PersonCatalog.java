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
    private String date = DATE_FORMATTER.format(new Date());
    private String fileName = DEFAULT_FILE_NAME;

    public PersonCatalog() {
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

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public List<Displayable> getPersons() {
        return ImmutableList.<Displayable> copyOf(this.persons);
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String filename) {
        this.fileName = filename;
        this.date = DATE_FORMATTER.format(new Date());
    }

    public boolean hasChanges() {
        return !this.fileName.equals(DEFAULT_FILE_NAME) || !this.persons.isEmpty();
    }

    public String toJSON() {
        return new Gson().toJson(this);
    }

    public static PersonCatalog fromJSON(String json) {
        return new Gson().fromJson(json, PersonCatalog.class);
    }
}
