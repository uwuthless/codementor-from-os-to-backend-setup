package roks.mike.zerotowebapp.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import roks.mike.zerotowebapp.model.School;
import roks.mike.zerotowebapp.model.Student;

@Repository
public interface SchoolRedisRepository extends CrudRepository<School, String> {}
