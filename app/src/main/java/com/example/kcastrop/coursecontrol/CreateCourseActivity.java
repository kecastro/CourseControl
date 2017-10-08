package com.example.kcastrop.coursecontrol;

/**
 * Created by Edwin on 07/10/17.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import entities.User;
import entities.Course;

public class CreateCourseActivity extends BaseActivity{

    private EditText nameCourse;
    private EditText course_reference;
    private EditText course_location;
    private EditText course_max_students;
    private Button createCourse;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.mainContent);
        getLayoutInflater().inflate(R.layout.activity_create_course, contentFrameLayout);

        final EditText nameCourse = (EditText) findViewById(R.id.name_course);
        final EditText course_reference = (EditText) findViewById(R.id.course_reference);
        final EditText course_location = (EditText) findViewById(R.id.course_location);
        final EditText course_max_students = (EditText) findViewById(R.id.course_max_students);
        final Button createCourse = (Button) findViewById(R.id.btn_create_course);
        createCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarCourse();
            }
        });

    }

    public void registrarCourse(){
        String name = nameCourse.getText().toString();
        String reference = course_reference.getText().toString();
        String location = course_location.getText().toString();
        int max_students = 0;
        try {
            max_students = Integer.parseInt(course_max_students.getText().toString());
        } catch(NumberFormatException nfe) {
            // Handle parse error.
        }
        firebaseAuth = FirebaseAuth.getInstance();
        User u = new User(firebaseAuth.getCurrentUser().getUid());
        database = FirebaseDatabase.getInstance().getReference();
        Course course = new Course(name,reference,location,u,max_students);
        database.child("Courses").child(course.getCourseId()).setValue(course);
        finish();
        Toast.makeText(CreateCourseActivity.this,"Curso creado",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

}
