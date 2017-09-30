package org.maptalks.sample.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.maptalks.sample.bean.Sample;
import org.maptalks.sample.bean.SampleRowMapper;
import org.maptalks.sample.dao.SampleDao;

/**
 * 项目名：seegoo-jquery-spring-archetype
 * 类   名：SampleDaoImpl.java
 * 作   者：wangjun
 * 日   期：2013-6-30
 * 说   明：sample的数据库持久化实现类
 */
public class SampleDaoImpl implements SampleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean addSample(Sample newSample) {
		boolean bool = false;
		String sql = "INSERT INTO "
				+ Sample.TABLE_NAME
				+ "(" 
				+ Sample.FIELD_ID +","
				+ Sample.FIELD_NAME +","
				+ Sample.FIELD_STATUS +","
				+ Sample.FIELD_CREATEDATE +","
				+ Sample.FIELD_MONEY
				+") VALUES ("
				+ newSample.getId() +","
				+ "'" + newSample.getName() +"',"
				+ newSample.getStatus() +","
				+ newSample.getCreateDate() +","
				+ newSample.getMoney()
				+")";
		int count = jdbcTemplate.update(sql);
		if (count > 0) {
			bool = true;
		}
		return bool;
	}

	public boolean removeSample(long sampleId) {
		String sql = "DELETE FROM"
				+" "+ Sample.TABLE_NAME
				+" WHERE "
				+" "+ Sample.FIELD_ID + " = "+ sampleId;
		int result = jdbcTemplate.update(sql);
		boolean bool = false;
		if (result>0) {
			bool = true;
		}
		return bool;
	}

	public boolean modifySample(Sample modifySample) {
		boolean bool = false;
		String sql = "UPDATE "
					+ Sample.TABLE_NAME
					+ " SET "
					+ Sample.FIELD_ID + "=" + modifySample.getId() +","
					+ Sample.FIELD_NAME + "= '" + modifySample.getName() +"',"
					+ Sample.FIELD_STATUS + "=" + modifySample.getStatus() +","
					+ Sample.FIELD_CREATEDATE + "=" + modifySample.getCreateDate() +","
					+ Sample.FIELD_MONEY + "=" +modifySample.getMoney()
					+ " WHERE "+ Sample.FIELD_ID + "=" + modifySample.getId();
		int result = jdbcTemplate.update(sql);
		if (result > 0) {
			bool = true;
		}
		return bool;
	}

	
	public Sample getSampleById(long sampleId) {
		Sample sample = new Sample();
		String sql = "SELECT "
					+ Sample.FIELD_ID +","
					+ Sample.FIELD_NAME +","
					+ Sample.FIELD_STATUS +","
					+ Sample.FIELD_CREATEDATE +","
					+ Sample.FIELD_MONEY
					+ " FROM "+ Sample.TABLE_NAME
					+ " WHERE "+Sample.FIELD_ID +" = "+sampleId;
		try {
			sample = jdbcTemplate.queryForObject(sql, new SampleRowMapper());
		} catch (Exception e) {
		}
		return sample;
	}

	public List<Sample> getAllSampleList(int page, int size) {
		List<Sample> list = new ArrayList<Sample>();
		StringBuffer sqlBuffer = new StringBuffer("SELECT "
							+ Sample.FIELD_ID +","
							+ Sample.FIELD_NAME +","
							+ Sample.FIELD_STATUS +","
							+ Sample.FIELD_CREATEDATE +","
							+ Sample.FIELD_MONEY
							+ " FROM "+ Sample.TABLE_NAME);
		if(page>=0 && size >0){
			sqlBuffer.append(" LIMIT "+(page*size)+","+size+"");
		}
		list = jdbcTemplate.query(sqlBuffer.toString(), new SampleRowMapper());
		return list;
	}
	
	public long getSampleNextId() {
		long nextId = 1;
		String sql = "SELECT Max("
				+ Sample.FIELD_ID +")"
				+ " FROM "+ Sample.TABLE_NAME;
		long id = jdbcTemplate.queryForLong(sql);
		if(id > 0){
			nextId = nextId+id;
		}
		return nextId;
	}
}
