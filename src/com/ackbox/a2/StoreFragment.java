package com.ackbox.a2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class StoreFragment extends BaseFragment {

    private static final String TAG = StoreFragment.class.getSimpleName();

    private EditText nameEditText;

    private final CatalogService service = CatalogService.INSTANCE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        this.nameEditText = (EditText) getActivity().findViewById(R.id.text_file_name);

        Button storeButton = (Button) getActivity().findViewById(R.id.button_store);
        storeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                storeEntryList();
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

    private void storeEntryList() {
        String filename = this.nameEditText.getText().toString();

        try {
            Log.d(TAG, String.format("Preparing to save entry list into [{0}].", filename));
            this.service.saveEntryList(getActivity(), filename);

            showNotification(R.string.entry_list_stored_message);
        } catch (CatalogException e) {
            Log.e(TAG, "Entry list not stored.", e);
            showNotification(R.string.entry_list_not_stored_message);
        }
    }
}
