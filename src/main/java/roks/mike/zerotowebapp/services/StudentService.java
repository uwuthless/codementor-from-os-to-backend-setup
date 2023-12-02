package roks.mike.zerotowebapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roks.mike.zerotowebapp.model.Student;
import roks.mike.zerotowebapp.postgres.PostgresRepository;
import roks.mike.zerotowebapp.redis.StudentRedisRepository;

import java.util.Optional;

@RestController
public class StudentService {
    Logger LOG = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private PostgresRepository postgresRepository;
    @Autowired
    public StudentRedisRepository studentRedisRepository;

    @PostMapping(value = "/student", consumes = "application/json")
    public int createStudent(
            @RequestBody Student student
    ) {
        return postgresRepository.createStudent(student);
    }

    @GetMapping("/student/{login}")
    public String getStudent(
            @PathVariable(value = "login") String login
    ) {
        final Optional<Student> studentFromRedis = studentRedisRepository.findById(login);
        if(studentFromRedis.isPresent()) {
            LOG.info("Student {} found in Redis", login);
            return studentFromRedis.get().toString();
        }

        LOG.info("Student {} not found in Redis, will try finding them in Postgres", login);
        final Student studentFromPg = postgresRepository.getStudent(login);
        if(studentFromPg == null) {
            LOG.info("Student {} not found in Postgres", login);
            return null;
        }

        LOG.info("Student {} found in Postgres!", login);
        return studentFromPg.toString();
    }
}
