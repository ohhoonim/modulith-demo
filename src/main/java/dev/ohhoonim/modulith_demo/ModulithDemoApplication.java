package dev.ohhoonim.modulith_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.moments.HourHasPassed;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ModulithDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModulithDemoApplication.class, args);
	}

	

}
