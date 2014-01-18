package com.ackbox.a2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EnterNamesFragment extends BaseFragment {

    private static final String TAG = EnterNamesFragment.class.getSimpleName();

    private EditText nameEditText;
    private EditText ageEditText;
    private Spinner foodsSpinner;

    private final CatalogService service = CatalogService.INSTANCE;

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
        this.nameEditText = (EditText) getActivity().findViewById(R.id.text_name);
        this.ageEditText = (EditText) getActivity().findViewById(R.id.text_age);
        this.foodsSpinner = (Spinner) getActivity().findViewById(R.id.choice_food_type);

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
            Person person = getPerson();

            Log.d(TAG, String.format("Preparing to save entry {0}.", person));
            this.service.addPerson(getActivity(), person);

            showNotification(R.string.entry_added_message);
            cleanUpFields();
        } catch (CatalogException e) {
            Log.e(TAG, "Entry not saved.", e);
            showNotification(R.string.entry_not_added_message);
        }
    }

    private Person getPerson() {
        String name = this.nameEditText.getText().toString();
        String ageText = this.ageEditText.getText().toString();
        String food = (String) this.foodsSpinner.getSelectedItem();
        Integer age = (Utils.isNumeric(ageText)) ? Integer.valueOf(ageText) : 0;

        return Person.withName(name).andAge(age).andFood(food);
    }

    private void cleanUpFields() {
        this.nameEditText.setText("");
        this.ageEditText.setText("");
        this.foodsSpinner.setSelection(0);
    }
}
