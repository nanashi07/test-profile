package testprofile.testprofile.config.unit;

import lombok.extern.log4j.Log4j2;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@Profile({"unit"})
@Log4j2
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return Mockito.mock(DataSource.class);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Mockito.mock(EntityManagerFactory.class);
    }

}
