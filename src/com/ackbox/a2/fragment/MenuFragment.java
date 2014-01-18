package com.ackbox.a2.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ackbox.a2.R;
import com.ackbox.a2.model.CatalogService;

public class MenuFragment extends BaseFragment {

    private final CatalogService mService = CatalogService.INSTANCE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        setupHeader();
        setupEnterNamesButton();
        setupViewButton();
        setupStoreButton();
        setupLoadButton();
        setupExitButton();
    }

    private void setupHeader() {
        TextView header = (TextView) getActivity().findViewById(R.id.welcome);
        header.setText(String.format(getResources().getString(R.string.welcome_message),
                this.mService.currentCatalogName()));
    }

    private void setupEnterNamesButton() {
        Button button = (Button) getActivity().findViewById(R.id.button_enter_names);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switchFragment(new EnterNamesFragment());
            }
        });
    }

    private void setupStoreButton() {
        Button button = (Button) getActivity().findViewById(R.id.button_store);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switchFragment(new StoreFragment());
            }
        });
    }

    private void setupViewButton() {
        Button button = (Button) getActivity().findViewById(R.id.button_view);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switchFragment(new ViewFragment());
            }
        });
    }

    private void setupLoadButton() {
        Button button = (Button) getActivity().findViewById(R.id.button_load);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switchFragment(new LoadFragment());
            }
        });
    }

    private void setupExitButton() {
        Button button = (Button) getActivity().findViewById(R.id.button_exit);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!MenuFragment.this.mService.hasUnsavedChanges()) {
                    getActivity().finish();
                } else {
                    new AlertDialog.Builder(getActivity()).setMessage(R.string.unsaved_changes_message)
                            .setCancelable(false).setPositiveButton(R.string.ok_label, exitListener())
                            .setNegativeButton(R.string.cancel_label, emptyListener()).create().show();
                }
            }

            private OnClickListener emptyListener() {
                return new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                };
            }

            private OnClickListener exitListener() {
                return new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
                    }
                };
            }
        });

    }
}