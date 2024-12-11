package uagrm.bo.workflow;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkflowApplication {

    private static final Dotenv dotenv = Dotenv.load();

    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);


    }

    public static String getEnv(String key) {
        return dotenv.get(key);
    }

}
