package entities;

/**
 * Created by AnDresLoPz on 10/9/2017.
 */

public class EnrollCourse {

    private String idEnrollCourse;
    private Course course;
    private User student;
    private String State;

    public EnrollCourse(){

    }

    public EnrollCourse (Course course, User student){
        this.setIdEnrollCourse(course.getCourseId()+student.getFirebaseId());
        this.setCourse(course);
        this.setStudent(student);
        this.setState("PENDIENTE");
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

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getIdEnrollCourse() {
        return idEnrollCourse;
    }

    public void setIdEnrollCourse(String idEnrollCourse) {
        this.idEnrollCourse = idEnrollCourse;
    }
}
