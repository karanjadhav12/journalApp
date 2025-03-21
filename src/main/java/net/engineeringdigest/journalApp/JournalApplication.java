package net.engineeringdigest.journalApp;


import com.mongodb.client.MongoDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionException;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class JournalApplication {
    public static void main(String[] args) {
//        System.out.println( System.getenv("JAVAMAIL_HOST"));
//        System.out.println( System.getenv("JAVAMAIL_USERNAME"));
//        System.out.println( System.getenv("JAVAMAIL_PASSWORD"));
        ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
//        System.out.println(context.getEnvironment().getActiveProfiles()[0]);
    }
    @Bean
    public PlatformTransactionManager falana(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
