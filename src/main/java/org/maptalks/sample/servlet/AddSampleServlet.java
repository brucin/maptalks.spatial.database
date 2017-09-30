package org.maptalks.sample.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.maptalks.sample.bean.Sample;
import org.maptalks.sample.service.SampleService;
import org.maptalks.servlet.BaseSpringServlet;
import cn.com.seegoo.web.util.ParamTool;

import com.alibaba.fastjson.JSONObject;

public class AddSampleServlet extends BaseSpringServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3739964467031883357L;
	
	@Autowired
	private SampleService sampleService;
	
	public void setSampleService(SampleService sampleService) {
		this.sampleService = sampleService;
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = ParamTool.getStr(req, "sampleName", "");
		int status = ParamTool.getInt(req, "sampleStatus", 0);
		BigDecimal money = ParamTool.getBigDecimal(req, "sampleMoney", "0");
		
		Sample newSample = new Sample();
		newSample.setName(name);
		newSample.setStatus(status);
		newSample.setMoney(money);
		
		JSONObject jsonObject = new JSONObject();
		boolean addStauts = false;
		Sample addSample = sampleService.addSample(newSample);
		if (addSample.getId() > 0) {
			addStauts = true;
		}
		jsonObject.put("success", addStauts);
		jsonObject.put("sample", addSample);
//		write(jsonObject.toJSONString(), resp);
	}

}
