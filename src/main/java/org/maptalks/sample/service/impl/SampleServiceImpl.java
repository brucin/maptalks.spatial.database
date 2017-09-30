package org.maptalks.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.maptalks.sample.bean.Sample;
import org.maptalks.sample.dao.SampleDao;
import org.maptalks.sample.service.SampleService;

/**
 * 项目名：seegoo-jquery-spring-sample
 * 类   名：SampleServiceImpl.java
 * 作   者：wangjun
 * 日   期：2013-7-1
 * 说   明：sample service接口实现类
 */
public class SampleServiceImpl implements SampleService {
	
	@Autowired
	private SampleDao sampleDao;

	public Sample addSample(Sample newSample) {
		Sample addSample = new Sample();
		newSample.setId(sampleDao.getSampleNextId());
		newSample.setCreateDate(System.currentTimeMillis());
		boolean addStatus = sampleDao.addSample(newSample);
		if(addStatus){
			addSample = sampleDao.getSampleById(newSample.getId());
		}
		return addSample;
	}

	public boolean removeSample(long removeId) {
		return sampleDao.removeSample(removeId);
	}

	public Sample modifySample(Sample modifySample) {
		Sample returnSample = new Sample();
		boolean modifyStatus = sampleDao.modifySample(modifySample);
		if(modifyStatus){
			returnSample = sampleDao.getSampleById(modifySample.getId());
		}
		return returnSample;
	}

	public Sample getSampleById(long sampleId) {
		return sampleDao.getSampleById(sampleId);
	}

	public List<Sample> getAllSampleList(int page, int size) {
		return sampleDao.getAllSampleList(page, size);
	}

}
