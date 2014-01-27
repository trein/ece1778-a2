package com.ackbox.a2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.ackbox.a2.fragment.MenuFragment;

/**
 * Entry point activity. It is the fragment container.
 * 
 * @author trein
 * 
 */
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            addFragment(new MenuFragment());
        }
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

}
