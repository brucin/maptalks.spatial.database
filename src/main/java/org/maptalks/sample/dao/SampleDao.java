package org.maptalks.sample.dao;

import java.util.List;

import org.maptalks.sample.bean.Sample;

/**
 * 
 * 项目名：seegoo-jquery-spring-archetype
 * 类   名：SampleDao.java
 * 作   者：wangjun
 * 日   期：2013-6-30
 * 说   明：sample的数据库持久化接口
 */
public interface SampleDao {
	
	/**
	 * 持久化sample对象
	 * @param sample 待持久化的sample实例
	 * @return 持久化后的sample对象
	 */
	boolean addSample(Sample newSample);
	
	/**
	 * 根据sampleId移除sample记录
	 * @param sampleId sample主键
	 * @return true，移除成功；false,移除失败
	 */
	boolean removeSample(long sampleId);
	
	/**
	 * 修改持久化的sample对象
	 * @param modifySample 待修改的sample实例
	 * @return 修改后的sample对象
	 */
	boolean modifySample(Sample modifySample);
	
	/**
	 * 根据sampleId查找sample对象
	 * @param sampleId sample的id
	 * @return 默认sample对象或者查找到的sample对象
	 */
	Sample getSampleById(long sampleId);
	
	/**
	 * 获取所有的sample对象
	 * @page 当前页
	 * @size 每页显示条数
	 * @return sample列表
	 */
	List<Sample> getAllSampleList(int page, int size);
	
	/**
	 * 获取sample下一个id
	 * @return 下一个id
	 */
	long getSampleNextId();

}
