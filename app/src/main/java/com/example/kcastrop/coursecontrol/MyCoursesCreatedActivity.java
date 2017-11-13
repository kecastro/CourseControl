package com.example.kcastrop.coursecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import entities.Course;
import entities.User;

/**
 * Created by kevincastro on 10/7/17.
 */

public class MyCoursesCreatedActivity extends BaseActivity{


    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;
    private TextView information;
    private ArrayList<Course> courses;
    private ListView listViewCourses;
    private BaseActivity active;

    FloatingActionButton fabCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.mainContent);
        getLayoutInflater().inflate(R.layout.activity_mycoursescreated, contentFrameLayout);
        setTitle("Mis Cursos Creados");

        fabCreate = (FloatingActionButton) findViewById(R.id.fab_create);
        fabCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateCourseActivity.class);
                startActivity(intent);
            }
        });

        listViewCourses = (ListView) findViewById(R.id.list_course);
        active = this;
        FirebaseUser user = firebaseAuth.getCurrentUser();
        User ut = new User(user.getUid(),user.getEmail());
        ut.myCreatedCourses(active,listViewCourses);

    }

}