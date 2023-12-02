package roks.mike.zerotowebapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;

@RedisHash("Student")
public class Student implements Serializable {

    @Id
    private String login;
    private String name;
    private String school;
    private int age;


    public Student(String login, String name, String school, int age) {
        this.login = login;
        this.name = name;
        this.school = school;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
            rs.getString("login"),
            rs.getString("name"),
            rs.getString("school"),
            rs.getInt("age")
    );

    @Override
    public String toString() {
        return "Student{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", school='" + school + '\'' +
                ", age=" + age +
                '}';
    }
}