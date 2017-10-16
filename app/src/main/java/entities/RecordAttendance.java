package entities;

import java.sql.Date;

/**
 * Created by AnDresLoPz on 10/14/2017.
 */

public class RecordAttendance {
    private Date time;
    private Course course;
    private User student;
    private User recorder;

    public RecordAttendance(Date time, Course course, User student, User recorder ){
        this.time = time;
        this.course = course;
        this.student = student;
        this.recorder = recorder;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getRecorder() {
        return recorder;
    }

    public void setRecorder(User recorder) {
        this.recorder = recorder;
    }
}
