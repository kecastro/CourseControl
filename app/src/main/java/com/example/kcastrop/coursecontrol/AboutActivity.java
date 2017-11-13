package com.example.kcastrop.coursecontrol;

import android.os.Bundle;
import android.widget.FrameLayout;

/**
 * Created by kevincastro on 10/7/17.
 */

public class AboutActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.mainContent);
        getLayoutInflater().inflate(R.layout.activity_about, contentFrameLayout);
        setTitle("Acerca");
    }
}

