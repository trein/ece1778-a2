package com.ackbox.a2.fragment;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.ackbox.a2.R;
import com.ackbox.a2.adapter.TitleDetailAdapter;
import com.ackbox.a2.model.CatalogException;
import com.ackbox.a2.model.CatalogService;
import com.ackbox.a2.model.Displayable;
import com.ackbox.a2.model.PersonCatalog;

/**
 * Fragment enabling the user to load previously stored catalogs.
 * 
 * @author trein
 * 
 */
public class LoadFragment extends BaseFragment {

    private static final String TAG = LoadFragment.class.getSimpleName();

    private final CatalogService mService = CatalogService.INSTANCE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_load, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        loadStoredEntryLists();

        Button doneButton = (Button) getActivity().findViewById(R.id.button_done);
        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switchToPreviousFragment();
            }
        });
    }

    private void loadStoredEntryLists() {
        try {
            Log.d(TAG, "Preparing to load stored catalog file names.");
            List<Displayable> list = this.mService.getStoredCatalogFiles(getActivity());

            final ListView listView = (ListView) getActivity().findViewById(R.id.list);
            TitleDetailAdapter adapter = new TitleDetailAdapter(getActivity(), list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    loadSelectedCatalog(listView, position);
                }
            });
        } catch (CatalogException e) {
            Log.e(TAG, "Catalog file names not loaded.", e);
            String pattern = getResources().getString(R.string.stored_catalogs_not_loaded_message_pattern);
            showNotification(String.format(pattern, e.getMessage()));
        }
    }

    private void loadSelectedCatalog(final ListView listView, int position) {
        PersonCatalog catalog = (PersonCatalog) listView.getItemAtPosition(position);
        Log.d(TAG, String.format("Selected catalog {0}.", catalog));

        try {
            this.mService.loadCurrentCatalog(getActivity(), catalog.getFileName());
            String pattern = getResources().getString(R.string.stored_catalogs_loaded_message_pattern);
            showNotification(String.format(pattern, catalog.getFileName()));
            switchToPreviousFragment();
        } catch (CatalogException e) {
            Log.e(TAG, "Catalog not loaded.", e);
            String pattern = getResources().getString(R.string.stored_catalogs_not_loaded_message_pattern);
            showNotification(String.format(pattern, e.getMessage()));
        }
    }
}
