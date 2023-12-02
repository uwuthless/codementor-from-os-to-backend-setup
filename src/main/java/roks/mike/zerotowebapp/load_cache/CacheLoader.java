package roks.mike.zerotowebapp.load_cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import roks.mike.zerotowebapp.model.School;
import roks.mike.zerotowebapp.model.Student;
import roks.mike.zerotowebapp.postgres.PostgresRepository;
import roks.mike.zerotowebapp.redis.SchoolRedisRepository;
import roks.mike.zerotowebapp.redis.StudentRedisRepository;
import roks.mike.zerotowebapp.services.StudentService;

import java.util.List;

@Component
public class CacheLoader {

    Logger LOG = LoggerFactory.getLogger(CacheLoader.class);

    @Autowired
    public PostgresRepository postgresRepository;
    @Autowired
    public SchoolRedisRepository schoolRedisRepository;
    @Autowired
    public StudentRedisRepository studentRedisRepository;

    public void loadRedisCaches() {
        final List<Student> studentList = postgresRepository.getAllStudents();
        final List<School> schoolList = postgresRepository.getAllSchools();


        LOG.info("Loading to cache schools: " + schoolList);
        LOG.info("Loading to cache students: " + studentList);
        schoolRedisRepository.saveAll(schoolList);
        studentRedisRepository.saveAll(studentList);
    }
}
