package roks.mike.zerotowebapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.jdbc.core.RowMapper;

@RedisHash("School")
public class School {

    @Id
    private String name;
    private String country;

    public School(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public static RowMapper<School> rowMapper = (rs, rowNum) -> new School(
            rs.getString("name"),
            rs.getString("country")
    );

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
