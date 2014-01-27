package com.ackbox.a2.fragment;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.ackbox.a2.R;
import com.ackbox.a2.adapter.TitleDetailAdapter;
import com.ackbox.a2.model.CatalogService;
import com.ackbox.a2.model.Displayable;

/**
 * Fragment displaying all stored catalogs.
 * 
 * @author trein
 * 
 */
public class ViewFragment extends BaseFragment {

    private static final String TAG = ViewFragment.class.getSimpleName();

    private final CatalogService mService = CatalogService.INSTANCE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        loadCurrentEntryList();

        Button doneButton = (Button) getActivity().findViewById(R.id.button_done);
        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switchToPreviousFragment();
            }
        });
    }

    private void loadCurrentEntryList() {
        Log.d(TAG, "Preparing to load current entry list.");
        List<Displayable> list = this.mService.getCurrentCatalogPersons();

        ListView listView = (ListView) getActivity().findViewById(R.id.list);
        TitleDetailAdapter adapter = new TitleDetailAdapter(getActivity(), list);
        listView.setAdapter(adapter);
    }

}
