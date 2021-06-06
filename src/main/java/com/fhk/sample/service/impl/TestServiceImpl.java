package com.fhk.sample.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhk.sample.domain.dao.TestDao;
import com.fhk.sample.domain.entity.TestBean;
import com.fhk.sample.service.TestService;

@Service
class TestServiceImpl implements TestService
{
	@Inject
	private TestDao testDao;

	@Override
	@Transactional(readOnly=true)
	public List<TestBean> findTestAll() 
	{
		return testDao.findAll();
	}

	@Override
	@Transactional
	public void addTest(final TestBean test) 
	{
		testDao.save(test);
	}

	@Override
	@Transactional
	public void updateTest(final TestBean test) 
	{
		testDao.save(test);
	}

	@Override
	@Transactional
	public void deleteTestByTestId(final Long testId) 
	{
		testDao.delete(testId);
	}
	
}
