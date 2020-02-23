package testprofile.testprofile;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootTest
@Log4j2
@Profile("unit")
@ActiveProfiles("unit")
public class UniteBaseTest {

    @Resource
    Environment environment;

    @Test
    void contextLoads() {
        log.info("Active profile: {}", Arrays.toString(environment.getActiveProfiles()));
    }

}
