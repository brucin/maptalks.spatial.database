package org.maptalks.sample.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.maptalks.sample.bean.Sample;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/ApplicationContext.xml"})
public class SampleServiceTest {
	
	@Autowired
	private SampleService sampleService;

	public void setSampleService(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	@Test
	@Ignore
	public void testAddSample(){
		Sample newSample = new Sample();
		newSample.setId(3l);
		newSample.setName("sample name 测试名称");
		newSample.setStatus(0);
		newSample.setCreateDate(System.currentTimeMillis());
		newSample.setMoney(new BigDecimal("123.456"));
		Sample addSample = sampleService.addSample(newSample);
		assertEquals(newSample.getId(), addSample.getId());
	}
	
	@Test
	@Ignore
	public void testRemoveSample(){
		long removeId = 2l;
		boolean removeStatus = sampleService.removeSample(removeId);
		assertEquals(true, removeStatus);	
	}
	
	@Test
	@Ignore
	public void testModifySample(){
		long modifyId = 3l;
		String newNameStr = "service:修改后的名字";
		Sample beforeModifySample = sampleService.getSampleById(modifyId);
		beforeModifySample.setName(newNameStr);
		Sample afterModifySample = sampleService.modifySample(beforeModifySample);
		assertEquals(newNameStr, afterModifySample.getName());
	}
	
	@Test
	@Ignore
	public void testGetSampleById(){
		long getId = 2l;
		Sample getSample = sampleService.getSampleById(getId);
		assertEquals(getId, getSample.getId());
	}
	
	@Test
	@Ignore
	public void testGetAllSampleList(){
		List<Sample> list = sampleService.getAllSampleList(0, 0);
		assertEquals(1, list.size());
	}


}
