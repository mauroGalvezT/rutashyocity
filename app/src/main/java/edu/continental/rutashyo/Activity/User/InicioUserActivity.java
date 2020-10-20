package edu.continental.rutashyo.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import edu.continental.rutashyo.Activity.Fragments.HomeFragment;
import edu.continental.rutashyo.Activity.Fragments.ProfileFragment;
import edu.continental.rutashyo.R;
import edu.continental.rutashyo.settings.AppConst;
import edu.continental.rutashyo.settings.PrefManager;

public class InicioUserActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    //String emailPref;
    AlertDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_user);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(new HomeFragment());
      //  emailPref = PrefManager.getSomeStringValue(AppConst.PREF_EMAIL);

    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.itemHome:
                            openFragment(new HomeFragment());
                            return true;
                        case R.id.itemProfile:
                            openFragment(new ProfileFragment());
                            return true;
                    }
                    return false;
                }
            };
}