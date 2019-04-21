package lc.alg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlgApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgApplication.class, args);
	}

}
