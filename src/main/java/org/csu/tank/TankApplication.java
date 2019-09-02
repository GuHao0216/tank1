package org.csu.tank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.csu.tank.persistence")
@SpringBootApplication
public class TankApplication {

    public static void main(String[] args) {
        SpringApplication.run(TankApplication.class, args);
    }

}
