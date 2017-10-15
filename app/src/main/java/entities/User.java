package entities;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kcastrop.coursecontrol.BaseActivity;
import com.example.kcastrop.coursecontrol.CourseAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by kcastrop on 11/08/17.
 */


public class User {

    private String firebaseId;
    private String email;
    private String photo;
    private String username;
    private String phone;
    private String name;
    private Boolean premium;
    ArrayList<Course> courses;

    public User(){}

    public User(String firebaseId, String email){
        this.setFirebaseId(firebaseId);
        this.setEmail(email);
    }

    public ArrayList<Course> getMyCourses(){return this.courses;}

    public void myCreatedCourses(final BaseActivity activity, final ListView listView){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        Query queryCourses = ref.child("Courses").orderByChild("creator/firebaseId").equalTo(getFirebaseId());
        queryCourses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courses = new ArrayList<Course>();
                for(DataSnapshot coursesSnapshot: dataSnapshot.getChildren()){
                    Course course = coursesSnapshot.getValue(Course.class);
                    courses.add(course);
                    Log.d("Insert Course",course.getName());
                }
                CourseAdapter adapter = new CourseAdapter(activity, courses);
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("User>>createdCourse",databaseError.getMessage()+" details:"+databaseError.getDetails());
            }
        });
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }
}
