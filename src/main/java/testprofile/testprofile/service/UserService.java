package testprofile.testprofile.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import testprofile.testprofile.entity.User;
import testprofile.testprofile.repository.UserMapper;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User getUserByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return null;
        }
        return userMapper.getUserByEmail(email);
    }
}
