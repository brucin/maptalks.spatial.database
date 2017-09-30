package org.maptalks.sample.servlet;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.maptalks.sample.service.SampleService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/ApplicationContext.xml"})
public class AddSampleServletTest {
	
	
	private static final String SAMPLE_NAME = "sampleName";
	private static final String SAMPLE_STATUS = "sampleStatus";
	private static final String SAMPLE_MONEY = "sampleMoney";
	private AddSampleServlet addSampleServlet;
	private HttpServletRequest mockRequest;
	private HttpServletResponse mockResponse;
	private ByteArrayOutputStream out;
	private PrintWriter writer;
	
	@Autowired
	private SampleService sampleService;
	
	public void setSampleService(SampleService sampleService) {
		this.sampleService = sampleService;
	}
	
	@Before
	public void setup() {
		//我们需要实现的servlet
		addSampleServlet = new AddSampleServlet();
		out = new ByteArrayOutputStream();
		writer = new PrintWriter(out);
		//用easymock创建模拟对象
		mockRequest = EasyMock.createMock("mockRequest", HttpServletRequest.class);
		mockResponse = EasyMock.createMock("mockResponse",HttpServletResponse.class);	
	}
	
	@Test
	@Ignore
	public void testAddSample() throws IOException, ServletException{
		String name = new String("Servlet:sample 名称");
		String status = new String("1");
		String money = new String("333.666");
		perpareMockParames(name, status, money);
		
		addSampleServlet.setSampleService(sampleService);
		addSampleServlet.doPost(mockRequest, mockResponse);
		String actual = out.toString();
		JSONObject jsonObj =  JSON.parseObject(actual);
        boolean addStauts = jsonObj.getBoolean("success");
        assertEquals(true, addStauts);
	}
	
	//为模拟对象准备参数数据
	private void perpareMockParames(String name, String status, String money)
			throws IOException {
		//构造username参数与值
		EasyMock.expect(
						mockRequest.getParameter(SAMPLE_NAME))//我们需要提供给下面测试的类调用的方法
						.andReturn(name)//我们提供给上面调用方法的返回值
						.once();//给调用一次，还有everyone;times(n);
		//构造password参数与值
		EasyMock.expect(mockRequest.getParameter(SAMPLE_STATUS)).andReturn(status).once();
		EasyMock.expect(mockRequest.getParameter(SAMPLE_MONEY)).andReturn(money).once();
		
		//构造一个输出对象，为了查看测试类方法输出的内容
		EasyMock.expect(mockResponse.getWriter()).andReturn(writer).once();
		
		//模拟对象待机，准备运行
		EasyMock.replay(mockRequest);  
		EasyMock.replay(mockResponse);
	}
	
	
	@After  
    public void tearDown() throws IOException{  
		EasyMock.verify(mockRequest); 
		EasyMock.verify(mockResponse);  
		out.close();
    } 

}
