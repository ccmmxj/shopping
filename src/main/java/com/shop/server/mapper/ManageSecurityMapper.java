package com.shop.server.mapper;

import com.shop.server.dto.ManageSecurityDto;
import com.shop.server.model.ManageSecurity;
import com.shop.server.tkmapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ManageSecurityMapper extends BaseMapper<ManageSecurity> {
    @Select("select * from manage_security where is_deleted = 0")
    @ResultMap("BaseResultDtoMap")
    List<ManageSecurityDto> selectEnableAll();

    @Select("select * from manage_security where is_deleted = 0 order by oid")
    @ResultMap("BaseResultMap")
    List<ManageSecurity> selectAll();
//    int deleteByPrimaryKey(Long id);
//
//    int insert(ManageSecurity record);
//
//    int insertSelective(ManageSecurity record);
//
//    ManageSecurity selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(ManageSecurity record);
//
//    int updateByPrimaryKey(ManageSecurity record);
}