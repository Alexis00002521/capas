package com.ncapas.actividadl3capasv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.ncapas.actividadl3capasv.Domain.Entities"})
@EnableJpaRepositories(basePackages = {"com.ncapas.actividadl3capasv.repository"})
public class ActividadL3CapasVApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActividadL3CapasVApplication.class, args);
    }

}
