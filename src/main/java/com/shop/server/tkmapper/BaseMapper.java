package com.shop.server.tkmapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BaseMapper<T> extends MySqlMapper<T>,Mapper<T> {
}
