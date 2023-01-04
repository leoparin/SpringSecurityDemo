package com.leo.springsecurity;

import com.leo.springsecurity.Mapper.MenuMapper;
import com.leo.springsecurity.Mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName:MapperTest
 * PackageName:com.leo.springsecurity
 * Description:
 *
 * @Date:2022/12/31 10:24
 * @Auther: leo
 **/
@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Test
    void selectUserRoleById(){
        Long usrId = 1L;
        List<String> authorities  = menuMapper.selectPermsByUserId(usrId);

        System.out.println(authorities);
    }
}
