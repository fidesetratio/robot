package com.app.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.RobotDao;
import com.app.model.BrowserDriver;
import com.app.model.ResultTestCase;
import com.app.model.Script;
import com.app.model.TestCase;

@Service
public class RobotServices {
	@Autowired
	private SqlSession sqlSession;
	
	public TestCase selectTestCaseById(Long id){
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		return (TestCase)dao.selectTestCaseById(id);
	}
	
	public Script selectScriptById(Long id){
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		return (Script)dao.selectScriptById(id);
	}
	
	public void insertResultTestCaseReff(Long testCaseId, String reff_id) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		Map map = new HashMap();
		map.put("test_case_id", testCaseId);
		map.put("reff_id", reff_id);
		dao.insertResultTestCaseReff(map);
	}
	public void insertScript(Script script) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		
		dao.insertScript(script);
	}
	public List<Script>  selectAllScripts() {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		
		List<Script> scripts = (List<Script>)dao.selectAllScripts();
		
		return scripts;
	}
	
	public List<BrowserDriver>  selectBrowsers() {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		
		List<BrowserDriver> browsers = (List<BrowserDriver>)dao.selectBrowsers();
		
		return browsers;
	}
	
	public void  updateScript(Script script) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		
		dao.updateScript(script);
	}
	
	public BrowserDriver  selectBrowserDriverById(Long browserId) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		
	return	(BrowserDriver)dao.selectBrowserDriverById(browserId);
	}
	public void insertResultTestCase(ResultTestCase resultTestCase) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		
		dao.insertResultTestCase(resultTestCase);
	}
	
	public List<ResultTestCase> selectResultByReffAndPrefix(String reff_id, String prefix) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		Map map = new HashMap();
		map.put("reff_id", reff_id);
		map.put("prefix", prefix);
		return (List<ResultTestCase>)dao.selectResultByReffAndPrefix(map);
	}
	
}
