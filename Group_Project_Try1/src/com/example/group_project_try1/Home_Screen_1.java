package com.example.group_project_try1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Home_Screen_1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen_1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_home__screen_1, menu);
        return true;
    }
    
}
