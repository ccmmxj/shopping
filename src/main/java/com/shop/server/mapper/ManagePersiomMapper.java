package com.shop.server.mapper;

import com.shop.server.model.ManagePersiom;
import com.shop.server.tkmapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ManagePersiomMapper extends BaseMapper<ManagePersiom> {

    @Select("select ep.* from manage_role_persiom_related erpr join manage_persiom ep on ep.id = erpr.persiom_id and ep.is_deleted = 0 join manage_role er on erpr.role_id = er.id and er.is_deleted = 0 join manage_role_user_related erur on erur.role_id = er.id and erur.is_deleted = 0 join manage_user eu on eu.id = erur.user_id and eu.is_deleted = 0 where eu.id = #{userId} and erpr.is_deleted = 0")
    @ResultMap("BaseResultMap")
    List<ManagePersiom> selectByUserId(@Param("userId") Long userId);

    @Select("select ep.* from manage_persiom ep join manage_persiom_security_related epsr on epsr.persiom_id = ep.id and epsr.is_deleted = 0 where ep.is_deleted = 0 and epsr.security_id = #{securityId}")
    @ResultMap("BaseResultMap")
    List<ManagePersiom> selectBySecurityId(@Param("securityId") Long securityId);
}