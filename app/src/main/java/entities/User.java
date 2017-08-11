package entities;

import java.util.ArrayList;

/**
 * Created by kcastrop on 11/08/17.
 */


public class User {
    private String username;
    private String firebaseId;
    private ArrayList<Course> enrolledCourses;
    private ArrayList<Course> createdCourses;

    public User(){}

    public User(String firebaseId){
        this.firebaseId = firebaseId;
        this.username = null;
        this.enrolledCourses = null;
        this.createdCourses = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
