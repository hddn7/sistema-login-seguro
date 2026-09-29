package br.com.sistemalogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

@SpringBootApplication
@EnableMongoHttpSession
public class sistemalogin {

    public static void main(String[] args) {
        SpringApplication.run(sistemalogin.class, args);
    }
}
