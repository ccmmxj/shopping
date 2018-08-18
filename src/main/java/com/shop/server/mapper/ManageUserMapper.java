package com.shop.server.mapper;

import com.shop.server.model.ManageUser;
import com.shop.server.tkmapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManageUserMapper extends BaseMapper<ManageUser>  {

    @Select("select * from manage_user where user_name = #{username} and is_deleted = 0")
    @ResultMap("BaseResultMap")
    ManageUser selectByUsername(@Param("username") String username);
}