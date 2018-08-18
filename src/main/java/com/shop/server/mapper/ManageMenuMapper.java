package com.shop.server.mapper;

import com.shop.server.dto.ManageMenuDto;
import com.shop.server.model.ManageMenu;
import com.shop.server.tkmapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ManageMenuMapper extends BaseMapper<ManageMenu> {
    @Select("select em.* from manage_role_menu_related ermr join manage_role er on ermr.role_id = er.id and er.is_deleted = 0 join manage_menu em on em.id = ermr.menu_id and em.is_deleted = 0 join manage_role_user_related erur on erur.role_id = er.id and erur.is_deleted = 0 join manage_user eu on eu.id = erur.user_id and eu.is_deleted = 0 where eu.id = #{userId} and em.parent_id = 0 and ermr.is_deleted = 0")
    @ResultMap("BaseResultDtoMap")
    List<ManageMenuDto> selectByUserId(@Param("userId") Long userId);


    @Select("select em.* from manage_menu em where em.parent_id = #{pid}")
    @ResultMap("BaseResultDtoMap")
    List<ManageMenuDto> selectByPid(@Param("pid") Long pid);
}