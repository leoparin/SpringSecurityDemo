package com.leo.springsecurity.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.springsecurity.Domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * ClassName:MenuMapper
 * PackageName:com.leo.springsecurity.Mapper
 * Description:
 *
 * @Date:2022/12/31 10:18
 * @Auther: leo
 **/
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
   public List<String> selectPermsByUserId(Long id);
}
