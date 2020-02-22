package testprofile.testprofile.unit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import testprofile.testprofile.IntegrationBaseTest;
import testprofile.testprofile.entity.User;
import testprofile.testprofile.repository.UserMapper;
import testprofile.testprofile.service.UserService;

import javax.annotation.Resource;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Log4j2
public class UserServiceTest extends IntegrationBaseTest {

    @Resource
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @SneakyThrows
    @Test
    public void testGetUserByEmail() {
        when(userMapper.getUserByEmail(anyString()))
                .thenReturn(new User() {{
                    setUsername("linda.hsu");
                    setEmail("linda.hsu@email.com");
                    setId(10L);
                }});

        String email = "linda.hsu@email.com";
        User user = userService.getUserByEmail(email);
        log.info("user: {}", new ObjectMapper().writeValueAsString(user));
        Assertions.assertNotNull(user);
        Assertions.assertEquals("linda.hsu", user.getUsername());
    }

}
