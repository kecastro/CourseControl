package com.example.kcastrop.coursecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import entities.Course;

/**
 * Created by kevincastro on 10/7/17.
 */

public class HomeActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;
    private TextView information;
    private ArrayList<Course> courses;
    private ListView listViewCourses;
    private BaseActivity active;
    private CourseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        synchronized (this) {
            database = FirebaseDatabase.getInstance().getReference();
            firebaseAuth = FirebaseAuth.getInstance();

            FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.mainContent);
            getLayoutInflater().inflate(R.layout.activity_home, contentFrameLayout);

            information = (TextView) findViewById(R.id.info);
            listViewCourses = (ListView) findViewById(R.id.list_course);
            active = this;
            loadCourses();
            listViewCourses.setOnItemClickListener(this);
        }
    }

    private void loadCourses(){
        Query queryCourses = database.child("Courses");
        queryCourses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courses = new ArrayList<Course>();
                for(DataSnapshot coursesSnapshot: dataSnapshot.getChildren()){
                    Course course = coursesSnapshot.getValue(Course.class);
                    courses.add(course);
                }
                if (dataSnapshot != null)
                    information.setText("Cursos diponibles");
                else
                    information.setText("No hay cursos disponibles");
                adapter= new CourseAdapter(active, courses);
                listViewCourses.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Course course = (Course)adapter.getItem(i);
        //Send variables to other activity
        Intent intent = new Intent(active,EnrollCourseActivity.class);
        intent.putExtra("variable_id_course",course.getCourseId());
        intent.putExtra("variable_name_course",course.getName());
        //intent.putExtra("variable_reference_course",course.getReference());
        intent.putExtra("variable_location_course",course.getLocation());
        intent.putExtra("variable_idCreator_course",course.getCreator().getFirebaseId());
        startActivity(intent);
        //startActivity(new Intent(getApplicationContext(), EnrollCourseActivity.class));
    }
}