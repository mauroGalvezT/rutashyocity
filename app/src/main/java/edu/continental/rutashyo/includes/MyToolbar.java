package edu.continental.rutashyo.includes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import edu.continental.rutashyo.R;

public class MyToolbar {
    public static void show(AppCompatActivity activity, String title, boolean upButton) {
        Toolbar toolbar = activity.findViewById(R.id.toolbars);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(title);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
