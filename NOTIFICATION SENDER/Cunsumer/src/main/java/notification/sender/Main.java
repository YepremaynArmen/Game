package notification.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
/*@SpringBootApplication(scanBasePackages={"user.remote"})*/
/*@OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.0", description = "Employees Information"))*/
/*@EnableSwagger2*/
/*@CrossOrigin(origins = "*")*/
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }
}



