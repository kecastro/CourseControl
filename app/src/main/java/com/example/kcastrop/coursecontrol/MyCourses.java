package com.example.kcastrop.coursecontrol;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class MyCourses extends BaseActivity{


    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;
    private TextView information;
    private ArrayList<Course> courses;
    private ListView listViewCourses;
    private BaseActivity active;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        synchronized (this) {
            database = FirebaseDatabase.getInstance().getReference();
            firebaseAuth = FirebaseAuth.getInstance();

            FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.mainContent);
            getLayoutInflater().inflate(R.layout.activity_home, contentFrameLayout);
            //setContentView(R.layout.activity_home);

            information = (TextView) findViewById(R.id.info);
            listViewCourses = (ListView) findViewById(R.id.list_course);
            active = this;
            courses = new ArrayList<>();
            loadCourses();
        }
    }

    private void loadCourses(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Query queryCourses = database.child("Courses").orderByChild("creator/firebaseId").endAt(user.getUid());
        //information.setText(user.getEmail());
        queryCourses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot coursesSnapshot: dataSnapshot.getChildren()){
                    Course course = coursesSnapshot.getValue(Course.class);
                    courses.add(course);
                }
                if (dataSnapshot != null)
                    information.setText("Tus cursos");
                else
                    information.setText("No has creado ningún curso");
                CourseAdapter adapter = new CourseAdapter(active, courses);
                listViewCourses.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}