package com.app.services;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.RobotDao;
import com.app.model.TestCase;

@Service
public class RobotServices {
	@Autowired
	private SqlSession sqlSession;
	
	public TestCase selectTestCaseById(Long id){
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		return (TestCase)dao.selectTestCaseById(id);
	}
}
