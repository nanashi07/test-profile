package testprofile.testprofile.config.integration;

import org.flywaydb.core.Flyway;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Profile({"integration"})
    @Bean("flyway")
    public Flyway flyway1(@Autowired DataSource dataSource) {
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();
        return flyway;
    }

    @Profile({"unit"})
    @Bean("flyway")
    public Flyway flyway2() {
        return Mockito.mock(Flyway.class);
    }

}
