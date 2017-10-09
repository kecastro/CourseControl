package entities;

/**
 * Created by AnDresLoPz on 10/9/2017.
 */

public class EnrollCourse {

    private String idEnrollCourse;
    private String idCourse;
    private String idStudent;
    private String State;

    public EnrollCourse(){

    }

    public EnrollCourse (String idCourse, String idStudent){
        this.setIdEnrollCourse(idCourse+idStudent);
        this.setIdCourse(idCourse);
        this.setIdStudent(idStudent);
        this.setState("PENDIENTE");
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
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
