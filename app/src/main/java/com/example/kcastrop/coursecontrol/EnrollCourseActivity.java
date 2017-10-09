package com.example.kcastrop.coursecontrol;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import entities.Course;
import entities.EnrollCourse;

public class EnrollCourseActivity extends BaseActivity {

    private TextView nameCourse;
    private TextView course_reference;
    private TextView course_location;
    private TextView info;
    private Button subscribe;
    private String idCreator;
    private String idCourse;
    private String idUser;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.mainContent);
        getLayoutInflater().inflate(R.layout.activity_enroll_course, contentFrameLayout);

        nameCourse = (TextView) findViewById(R.id.name_course);
        course_reference = (TextView) findViewById(R.id.course_reference);
        course_location = (TextView) findViewById(R.id.course_location);
        info = (TextView) findViewById(R.id.textInfo);
        subscribe = (Button) findViewById(R.id.btn_subscribe_course);

        idCreator = getIntent().getStringExtra("variable_idCreator_course");
        idCourse = getIntent().getStringExtra("variable_id_course");
        nameCourse.setText(getIntent().getStringExtra("variable_name_course"));
        course_reference.setText(getIntent().getStringExtra("variable_reference_course"));
        course_location.setText(getIntent().getStringExtra("variable_location_course"));

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        idUser = firebaseAuth.getCurrentUser().getUid();
        info.setText("");

        if (idCreator.equals(idUser)) {
            subscribe.setEnabled(false);
            info.setText("No puedes inscribirte en un curso en el que eres dueño");
            //Toast.makeText(this,"No puedes inscribirte en un curso en el que eres dueño",Toast.LENGTH_LONG).show();
        }else {
            Query queryCourses = database.child("EnrollCourse").orderByChild("idCourse").endAt(idCourse);
            queryCourses.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Toast.makeText(EnrollCourseActivity.this, "Ya has enviado una solicitud", Toast.LENGTH_LONG).show();

                    for (DataSnapshot enrollCourseSnapshot : dataSnapshot.getChildren()) {
                        EnrollCourse enrollCourse = enrollCourseSnapshot.getValue(EnrollCourse.class);
                        if (enrollCourse.getIdStudent().equals(idUser) && enrollCourse.getIdCourse().equals(idCourse)) {
                            subscribe.setEnabled(false);
                            info.setText("Ya has enviado una solicitud");
                            //Toast.makeText(EnrollCourseActivity.this, "Ya has enviado una solicitud", Toast.LENGTH_LONG).show();
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        subscribe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                firebaseAuth = FirebaseAuth.getInstance();
                database = FirebaseDatabase.getInstance().getReference();
                EnrollCourse enrollCourse = new EnrollCourse(idCourse,idUser);
                database.child("EnrollCourse").child(enrollCourse.getIdEnrollCourse()).setValue(enrollCourse);
                finish();
                Toast.makeText(EnrollCourseActivity.this,"Tu solicitud ha sido enviada",Toast.LENGTH_LONG).show();
            }
        });

    }

}
