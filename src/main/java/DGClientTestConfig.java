import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DGClientTestConfig {
    @Bean
    public DGClient bookClient (){
      return new DGClient();
    }
}
