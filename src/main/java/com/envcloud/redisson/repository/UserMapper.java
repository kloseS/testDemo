package com.envcloud.redisson.repository;

import com.envcloud.redisson.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

public interface UserMapper extends BaseMapper<User> {

    @Insert({
            "INSERT INTO `t_user` ( `name`, `age`, `createTime`) VALUES (#{name}, #{age}, now())"
    })
    @Options(useGeneratedKeys = true, keyProperty="id", keyColumn="id")
    Integer addUser(User user);

}
