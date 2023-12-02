package roks.mike.zerotowebapp.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import roks.mike.zerotowebapp.model.Student;

@Repository
public interface StudentRedisRepository extends CrudRepository<Student, String> {}