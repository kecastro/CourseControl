package com.example.kcastrop.coursecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by kevincastro on 10/7/17.
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton imgButton_createCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.mainContent);
        getLayoutInflater().inflate(R.layout.activity_home, contentFrameLayout);

        imgButton_createCourse = (ImageButton) findViewById(R.id.imgbutton_createcourse);
        imgButton_createCourse.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == imgButton_createCourse) {
            startActivity(new Intent(getApplicationContext(), CreateCourseActivity.class));
            //Toast.makeText(HomeActivity.this, "Crear", Toast.LENGTH_LONG).show();
        }
    }

}