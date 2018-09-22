package start;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableBatchProcessing
@SpringBootApplication
@ComponentScan(basePackages="com")
public class DatabaseToKafkaSpringBatchApplApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseToKafkaSpringBatchApplApplication.class, args);
	}
}
