package com.ackbox.a2.model;

import java.util.Arrays;
import java.util.List;

import android.content.Context;

import com.ackbox.a2.R;
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

    public List<Displayable> getCurrentEntryList(Context context) throws CatalogException {
        List<Displayable> persons = Arrays.<Displayable> asList(
                Person.withName("John Doe").andAge(36).andFood("Chinese Food"), Person.withName("John Doe").andAge(36)
                        .andFood("Chinese Food"), Person.withName("John Doe").andAge(36).andFood("Chinese Food"),
                Person.withName("John Doe").andAge(36).andFood("Chinese Food"), Person.withName("John Doe").andAge(36)
                        .andFood("Chinese Food"), Person.withName("John Doe").andAge(36).andFood("Chinese Food"),
                Person.withName("John Doe").andAge(36).andFood("Chinese Food"), Person.withName("John Doe").andAge(36)
                        .andFood("Chinese Food"), Person.withName("Karen Smith").andAge(26).andFood("Tai Food"), Person
                        .withName("Peeta Preat").andAge(31).andFood("Veggie"),
                Person.withName("Carmen Thied").andAge(16).andFood("Chinese Food"));

        // throw new
        // CatalogException(context.getString(R.string.invalid_file_name_message));
        return persons;
    }
}
