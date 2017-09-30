package org.maptalks.sample.dao;

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
import org.maptalks.sample.dao.impl.SampleDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/ApplicationContext.xml"})
public class SampleDaoTest {
	
	@Autowired
	private SampleDao sampleDao;

	public void setSampleDao(SampleDao sampleDao) {
		this.sampleDao = sampleDao;
	}
	
	@Test
	@Ignore
	public void testAddSample(){
		Sample newSample = new Sample();
		newSample.setId(2l);
		newSample.setName("sample name 测试名称");
		newSample.setStatus(0);
		newSample.setCreateDate(System.currentTimeMillis());
		newSample.setMoney(new BigDecimal("123.456"));
		boolean addStatus = sampleDao.addSample(newSample);
		assertEquals(true, addStatus);
	}
	
	@Test
	@Ignore
	public void testRemoveSample(){
		long removeId = 1l;
		boolean removeStatus = sampleDao.removeSample(removeId);
		assertEquals(true, removeStatus);
	}
	
	@Test
	@Ignore
	public void testModifySample(){
		long modifyId = 2l;
		String newNameStr = "修改后的名字";
		Sample beforeModifySample = sampleDao.getSampleById(modifyId);
		beforeModifySample.setName(newNameStr);
		boolean modifyStatus = sampleDao.modifySample(beforeModifySample);
		assertEquals(true, modifyStatus);
		Sample afterModifySample = sampleDao.getSampleById(modifyId);
		assertEquals(newNameStr, afterModifySample.getName());
	}
	
	@Test
	@Ignore
	public void testGetSampleById(){
		long getId = 2l;
		Sample getSample = sampleDao.getSampleById(getId);
		assertEquals(getId, getSample.getId());
	}
	
	@Test
	@Ignore
	public void testGetAllSampleList(){
		List<Sample> list = sampleDao.getAllSampleList(0, 0);
		assertEquals(1, list.size());
	}
	

}
