package entities;

import java.util.ArrayList;

/**
 * Created by kcastrop on 11/08/17.
 */


public class User {
    private String email;
    private String firebaseId;
    private ArrayList<Course> enrolledCourses;
    private ArrayList<Course> createdCourses;

    public User(){}

    public User(String firebaseId, String email){
        this.firebaseId = firebaseId;
        this.email = email;
        this.enrolledCourses = null;
        this.createdCourses = null;
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

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(ArrayList<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public ArrayList<Course> getCreatedCourses() {
        return createdCourses;
    }

    public void setCreatedCourses(ArrayList<Course> createdCourses) {
        this.createdCourses = createdCourses;
    }
}
