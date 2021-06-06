package com.fhk.sample.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhk.sample.domain.entity.TestBean;
import com.fhk.sample.service.TestService;

@RestController
@RequestMapping(value = "/rest/test")
public class TestController 
{
	@Inject
	private TestService testService;
	
	@RequestMapping(value = "find-test-all", method = RequestMethod.POST)
	public List<TestBean> findTestAll() 
	{
		return testService.findTestAll();
	}
	
	@RequestMapping(value = "add-test", method = RequestMethod.POST)
	public Map<String,String> addTest()
	{
		final TestBean t = new TestBean();
		t.setTest(new Date().toString());
		testService.addTest(t);
		return new HashMap<String,String>();
	}
	@RequestMapping(value = "delete-test-by-test-id", method = RequestMethod.POST)
	public Map<String,String> deleteTestByTestId(@RequestParam(value = "testId", required = true) final Long testId )
	{
		testService.deleteTestByTestId(testId);
		return new HashMap<String,String>();
	}
}
