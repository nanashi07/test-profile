package testprofile.testprofile.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import testprofile.testprofile.entity.User;

@Mapper
public interface UserMapper {

    @Select("select * from user where email = #{email}")
    User getUserByEmail(@Param("email") String email);

}
