package com.shop.server.mapper;

import com.shop.server.model.ManageRole;
import com.shop.server.tkmapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ManageRoleMapper extends BaseMapper<ManageRole> {
    @Select("SELECT er.* FROM manage_role_user_related erur JOIN manage_role er ON erur.role_id = er.id AND er.is_deleted = 0 JOIN manage_user eu ON eu.id = erur.user_id AND eu.is_deleted = 0 WHERE eu.id = #{userId} AND erur.is_deleted = 0" )
    @ResultMap("BaseResultMap")
    List<ManageRole> selectByUserId(@Param("userId") Long userId);
}