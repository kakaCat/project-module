package project.inverntory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("project.dal.inventory.time.repository.db")
public class TimeApplication {



    public static void main(String[] args) {
        SpringApplication.run(TimeApplication.class, args);
    }
    
}
