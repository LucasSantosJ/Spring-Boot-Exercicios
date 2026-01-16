package inicio.spring_boot_aula1.config;

import inicio.spring_boot_aula1.externalDependency.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConnectionConfiguration {

    @Bean
    //@Primary
    public Connection connection1() {
        return new Connection("localhost", "lucas1", "123");
    }

    @Bean
    public Connection connection2() {
        return new Connection("localhost", "lucas2", "1234");
    }
}
