package testprofile.testprofile.unit.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testprofile.testprofile.UniteBaseTest;
import testprofile.testprofile.service.TaskService;

import javax.annotation.Resource;

@Log4j2
public class TaskServiceTest extends UniteBaseTest {

    @Resource
    private TaskService taskService;

    @Test
    public void plus() {
        log.info("test unit task service");
        int result = taskService.plus(1, 2);
        Assertions.assertEquals(3, result);

    }

}