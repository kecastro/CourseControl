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
    private ArrayList<User> enrolledUsers;

    public Course(){}

    public Course(String name, String reference, User creator) {
        this.courseId = creator.getFirebaseId() + name;
        this.name = name;
        this.reference = reference;
        this.creator = creator;
    }

    public Course(String name, String reference, String location, User creator) {
        this.courseId = creator.getFirebaseId() + name;
        this.name = name;
        this.reference = reference;
        this.location = location;
        this.creator = creator;
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
}
