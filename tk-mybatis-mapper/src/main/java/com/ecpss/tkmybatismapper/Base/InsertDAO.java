package com.ecpss.tkmybatismapper.Base;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;

public interface InsertDAO<T> extends Marker, InsertMapper<T>, InsertSelectiveMapper, MySqlMapper {
}
