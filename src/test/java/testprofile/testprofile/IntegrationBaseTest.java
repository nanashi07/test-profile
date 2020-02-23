package testprofile.testprofile;

import lombok.extern.log4j.Log4j2;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootTest
@Log4j2
@Profile("integration")
@ActiveProfiles("integration")
public class IntegrationBaseTest {

    @Resource
    Environment environment;
    @Resource
    private Flyway flyway;

    @BeforeEach
    public void cleanupDatabase() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void contextLoads() {
        log.info("Active profile: {}", Arrays.toString(environment.getActiveProfiles()));
    }

}
