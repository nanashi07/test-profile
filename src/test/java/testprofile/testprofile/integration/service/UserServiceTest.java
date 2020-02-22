package testprofile.testprofile.integration.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testprofile.testprofile.IntegrationBaseTest;
import testprofile.testprofile.entity.User;
import testprofile.testprofile.service.UserService;

import javax.annotation.Resource;

@Log4j2
public class UserServiceTest extends IntegrationBaseTest {

    @Resource
    private UserService userService;

    @SneakyThrows
    @Test
    public void testGetUserByEmail() {
        String email = "bruce.tsai@email.com";
        User user = userService.getUserByEmail(email);
        log.info("user: {}", new ObjectMapper().writeValueAsString(user));
        Assertions.assertNotNull(user);
        Assertions.assertEquals("bruce.tsai", user.getUsername());
    }

}
