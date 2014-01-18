package com.ackbox.a2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends BaseFragment {

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
        setupEnterNamesButton();
        // setupViewButton();
        // setupStoreButton();
        // setupLoadButton();
        setupExitButton();
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

    // private void setupLoadButton() {
    // Button button = (Button) getActivity().findViewById(R.id.button_load);
    // button.setOnClickListener(new View.OnClickListener() {
    //
    // @Override
    // public void onClick(View v) {
    // switchFragment(new LoadFragment());
    // }
    // });
    // }
    //
    // private void setupStoreButton() {
    // Button button = (Button) getActivity().findViewById(R.id.button_store);
    // button.setOnClickListener(new View.OnClickListener() {
    //
    // @Override
    // public void onClick(View v) {
    // switchFragment(new StoreFragment());
    // }
    // });
    // }
    //
    // private void setupViewButton() {
    // Button button = (Button) getActivity().findViewById(R.id.button_view);
    // button.setOnClickListener(new View.OnClickListener() {
    //
    // @Override
    // public void onClick(View v) {
    // switchFragment(new ViewFragment());
    // }
    // });
    // }
    //

    private void setupExitButton() {
        Button button = (Button) getActivity().findViewById(R.id.button_exit);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }
}
