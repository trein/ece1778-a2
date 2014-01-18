package com.ackbox.a2;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    protected void switchFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                .addToBackStack(null).commit();
    }

    protected void switchToPreviousFragment() {
        getFragmentManager().popBackStack();
    }
}
