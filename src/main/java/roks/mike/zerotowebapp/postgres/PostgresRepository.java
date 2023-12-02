package roks.mike.zerotowebapp.postgres;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import roks.mike.zerotowebapp.model.School;
import roks.mike.zerotowebapp.model.Student;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@Repository
public class PostgresRepository {
    private JdbcTemplate jdbcTemplate;
    final TransactionTemplate transactionTemplate;

    @Autowired
    public PostgresRepository(
            JdbcTemplate jdbcTemplate,
            PlatformTransactionManager transactionManager
    ) {
       this.jdbcTemplate = jdbcTemplate;
       this.transactionTemplate = new TransactionTemplate(transactionManager);
       transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
//       this.entityManager = entityManager;
    }


    public int createStudent(
            Student student
    ) {
        return jdbcTemplate.update("insert into public.student (login, name, school, age) values (?, ?, ?, ?)", student.getLogin(), student.getName(), student.getSchool(), student.getAge());
    }

    public int createSchool(
            String name,
            String country
    ) {
        return jdbcTemplate.update("insert into public.school (name, country) values (?, ?)", name, country);
    }

    public Student getStudent(
            String login
    ) {
        final List<Student> student = jdbcTemplate.query("select * from public.student where login = ?", Student.rowMapper, login);
         if(student.size() == 0) {
             throw new RuntimeException("Student with login " + login + " not found");
         }

        return student.get(0);
    }

    public School getSchool(
            String name
    ) {
        final List<School> schools = jdbcTemplate.query("select * from public.school where name = ?", School.rowMapper, name);
         if(schools.size() == 0) {
             throw new RuntimeException("School with name " + name + " not found");
         }

        return schools.get(0);
    }

    public List<School> getAllSchools() {
        return jdbcTemplate.query("select * from public.school", School.rowMapper);
    }

    public List<Student> getAllStudents() {
        return jdbcTemplate.query("select * from public.student", Student.rowMapper);
    }

}
