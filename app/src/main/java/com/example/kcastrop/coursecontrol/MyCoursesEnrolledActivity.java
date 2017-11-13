package com.example.kcastrop.coursecontrol;

import android.os.Bundle;
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
 * Created by kevincastro on 11/13/17.
 */

public class MyCoursesEnrolledActivity extends BaseActivity{


    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;
    private TextView information;
    private ArrayList<Course> courses;
    private ListView listViewCourses;
    private BaseActivity active;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.mainContent);
        getLayoutInflater().inflate(R.layout.activity_mycoursesenrolled, contentFrameLayout);
        setTitle("Mis Cursos Inscritos");

        listViewCourses = (ListView) findViewById(R.id.list_course);

    }

}