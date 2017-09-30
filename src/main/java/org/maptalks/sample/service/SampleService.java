package org.maptalks.sample.service;

import java.util.List;

import org.maptalks.sample.bean.Sample;

/**
 * 项目名：seegoo-jquery-spring-sample
 * 类   名：SampleService.java
 * 作   者：wangjun
 * 日   期：2013-7-1
 * 说   明：sample service接口
 */
public interface SampleService {

	/**
	 * 新增sample对象
	 * @param newSample sample实例
	 * @return 新增后的sample对象
	 */
	Sample addSample(Sample newSample);

	/**
	 * 根据指定的sample id删除持久化的记录
	 * @param removeId 需要删除的sample id
	 * @return true，删除成功
	 */
	boolean removeSample(long removeId);
	
	/**
	 * 修改sample对象
	 * @param modifySample sample实例
	 * @return 修改后的sample
	 */
	Sample modifySample(Sample modifySample);

	/**
	 * 根据sample id查询sample记录
	 * @param sampleId sample的id
	 * @return sample的记录
	 */
	Sample getSampleById(long sampleId);

	/**
	 * 查询所有sample记录
	 * @param page 当前页
	 * @param size 每页显示条数
	 * @return sample的列表
	 */
	List<Sample> getAllSampleList(int page, int size);

}
