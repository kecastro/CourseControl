package entities;

import java.util.ArrayList;

/**
 * Created by kcastrop on 11/08/17.
 */

public class Course {
    private String courseId;
    private String name;
    private String photo;
    private String description;
    private String labelLocation;
    private String courseTime;
    private String location;
    private User creator;
    private Integer maxNumStudents;
    //private String reference;

    public Course(){}

    public Course(String name, String description, User creator) {
        this.courseId = creator.getFirebaseId() + name;
        this.name = name;
        this.description = description;
        this.creator = creator;
    }

    public Course(String name, String description, String location, User creator, int maxNumStudents) {
        this.courseId = creator.getFirebaseId() + name;
        this.name = name;
        this.description = description;
        this.location = location;
        this.creator = creator;
        this.maxNumStudents = maxNumStudents;
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
/*
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
*/
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

    public int getMaxNumStudents() {
        return maxNumStudents;
    }

    public void setMaxNumStudents(int max_num_students) {
        this.maxNumStudents = max_num_students;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabelLocation() {
        return labelLocation;
    }

    public void setLabelLocation(String labelLocation) {
        this.labelLocation = labelLocation;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }
}
