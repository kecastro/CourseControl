package entities;

/**
 * Created by AnDresLoPz on 10/14/2017.
 */

public class RolUserCourse {
    private Integer id;
    private Course course;
    private User user;
    private String Rol;

    public RolUserCourse(Course course, User user, String rol){
        this.setCourse(course);
        this.setUser(user);
        this.setRol(rol);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Course getourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }
}
