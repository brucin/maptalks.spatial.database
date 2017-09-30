package org.maptalks.sample.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 项目名：seegoo-jquery-spring-archetype
 * 类   名：SampleRowMapper.java
 * 作   者：wangjun
 * 日   期：2013-6-30
 * 说   明：将数据库结果集包装成sample对象
 */
public class SampleRowMapper implements RowMapper<Sample> {
	
	public Sample mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Sample sample = new Sample();
		sample.setId(resultSet.getLong(Sample.FIELD_ID));
		sample.setName(resultSet.getString(Sample.FIELD_NAME));
		sample.setStatus(resultSet.getInt(Sample.FIELD_STATUS));
		sample.setCreateDate(resultSet.getLong(Sample.FIELD_CREATEDATE));
		sample.setMoney(resultSet.getBigDecimal(Sample.FIELD_MONEY));
		return sample;
	}

}
