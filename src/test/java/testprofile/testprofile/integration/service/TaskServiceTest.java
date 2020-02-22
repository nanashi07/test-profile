package testprofile.testprofile.integration.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testprofile.testprofile.IntegrationBaseTest;
import testprofile.testprofile.service.TaskService;

import javax.annotation.Resource;

@Log4j2
public class TaskServiceTest extends IntegrationBaseTest {

    @Resource
    private TaskService taskService;

    @Test
    public void plus() {
        log.info("test integration task service");
        int result = taskService.plus(1, 2);
        Assertions.assertEquals(3, result);
    }
}
