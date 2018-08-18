package com.shop.server.mapper;

import com.shop.server.model.WxProduct;
import com.shop.server.tkmapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WxProductMapper extends BaseMapper<WxProduct> {
    @Select("select distinct type from wx_product where is_deleted = 0 and company_id = #{companyId}")
    @ResultType(String.class)
    List<String> selectType(Long companyId);
}