import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookClientTestConfig {
    @Bean
    public  BookClient bookClient (){
      return new BookClient();
    }
}
