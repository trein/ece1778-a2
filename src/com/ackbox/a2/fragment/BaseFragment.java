package com.ackbox.a2.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.ackbox.a2.R;

public class BaseFragment extends Fragment {

    protected void switchFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                .addToBackStack(null).commit();
    }

    protected void switchToPreviousFragment() {
        getFragmentManager().popBackStack();
    }

    protected void showNotification(int stringId) {
        Toast.makeText(getActivity(), getResources().getString(stringId), Toast.LENGTH_SHORT).show();
    }
}
