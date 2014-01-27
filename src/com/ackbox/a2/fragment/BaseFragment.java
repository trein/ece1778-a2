package com.ackbox.a2.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
        showNotification(getResources().getString(stringId));
    }

    protected void showNotification(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected void showDecisionAlertMessage(OnClickListener okAction) {
        new AlertDialog.Builder(getActivity()).setMessage(R.string.unsaved_changes_message).setCancelable(false)
                .setPositiveButton(R.string.ok_label, okAction)
                .setNegativeButton(R.string.cancel_label, emptyListener()).create().show();
    }

    protected OnClickListener emptyListener() {
        return new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        };
    }
}
