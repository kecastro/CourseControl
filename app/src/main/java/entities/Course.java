package entities;

import java.util.ArrayList;

/**
 * Created by kcastrop on 11/08/17.
 */

public class Course {
    private String courseId;
    private String name;
    private String reference;
    private String location;
    private User creator;
    private int max_num_students;
    private ArrayList<User> enrolledUsers;

    public Course(){}

    public Course(String name, String reference, User creator) {
        this.courseId = creator.getFirebaseId() + name;
        this.name = name;
        this.reference = reference;
        this.creator = creator;
    }

    public Course(String name, String reference, String location, User creator, int max_num_students) {
        this.courseId = creator.getFirebaseId() + name;
        this.name = name;
        this.reference = reference;
        this.location = location;
        this.creator = creator;
        this.max_num_students = max_num_students;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ArrayList<User> getEnrolledUsers() {
        return enrolledUsers;
    }

    public void setEnrolledUsers(ArrayList<User> enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }

    public int getMax_num_students() {
        return max_num_students;
    }

    public void setMax_num_students(int max_num_students) {
        this.max_num_students = max_num_students;
    }

}
