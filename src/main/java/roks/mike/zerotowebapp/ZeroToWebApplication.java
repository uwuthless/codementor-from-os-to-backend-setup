package roks.mike.zerotowebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import roks.mike.zerotowebapp.load_cache.CacheLoader;

@SpringBootApplication
@RestController
public class ZeroToWebApplication {


    public static void main(String[] args) {

        final ConfigurableApplicationContext ctx = SpringApplication.run(ZeroToWebApplication.class, args);


        final CacheLoader cacheLoader = ctx.getBean(CacheLoader.class);
        cacheLoader.loadRedisCaches();
    }

    @GetMapping("/repeat/{str}/{times}")
    public String repeat(@PathVariable("str") String str, @PathVariable("times") int times) {
        return str.repeat(times);
    }

}
