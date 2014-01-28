package com.ackbox.a2.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ackbox.a2.R;
import com.ackbox.a2.common.Utils;
import com.ackbox.a2.model.Person;
import com.ackbox.a2.service.CatalogException;
import com.ackbox.a2.service.CatalogService;

/**
 * Fragment for adding new persons to catalogs.
 * 
 * @author trein
 * 
 */
public class EnterNamesFragment extends BaseFragment {

    private static final String TAG = EnterNamesFragment.class.getSimpleName();

    private EditText mNameEditText;
    private EditText mAgeEditText;
    private Spinner mFoodSpinner;
    private final CatalogService mService = CatalogService.INSTANCE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_enter_name, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        this.mNameEditText = (EditText) getActivity().findViewById(R.id.text_name);
        this.mAgeEditText = (EditText) getActivity().findViewById(R.id.text_age);
        this.mFoodSpinner = (Spinner) getActivity().findViewById(R.id.choice_food_type);

        Button addButton = (Button) getActivity().findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addEntry();
            }
        });

        Button doneButton = (Button) getActivity().findViewById(R.id.button_done);
        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switchToPreviousFragment();
            }
        });
    }

    private void addEntry() {
        try {
            Person person = createPerson();

            Log.d(TAG, String.format("Preparing to save entry {0}.", person));
            this.mService.addPerson(getActivity(), person);

            showNotification(R.string.entry_added_message);
            cleanUpFields();
        } catch (CatalogException e) {
            Log.e(TAG, "Entry not saved.", e);
            String pattern = getResources().getString(R.string.entry_not_added_message_pattern);
            showNotification(String.format(pattern, e.getMessage()));
        }
    }

    private Person createPerson() {
        String name = this.mNameEditText.getText().toString();
        String ageText = this.mAgeEditText.getText().toString();
        String food = (String) this.mFoodSpinner.getSelectedItem();
        Integer age = (Utils.isNumeric(ageText)) ? Integer.valueOf(ageText) : 0;

        return Person.newPersonWithName(name).andAge(age).andFood(food);
    }

    private void cleanUpFields() {
        this.mNameEditText.setText("");
        this.mAgeEditText.setText("");
        this.mFoodSpinner.setSelection(0);
    }
}
