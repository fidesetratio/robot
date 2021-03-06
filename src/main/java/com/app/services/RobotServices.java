package com.app.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.RobotDao;
import com.app.model.BrowserDriver;
import com.app.model.CategoryParameter;
import com.app.model.Parameter;
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
	
	public void insertBrowserDriver(BrowserDriver browserDriver) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		dao.insertBrowserDriver(browserDriver);
	}
	public void insertCategoryParameter(CategoryParameter categoryParameter) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		dao.insertCategoryParameter(categoryParameter);
	}
	
	public void deleteBrowserId(BrowserDriver browserDriver) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		dao.deleteBrowserId(browserDriver);
	}
	
	public void deleteParametersByParameterId(Long parameter_id) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		dao.deleteParametersByParameterId(parameter_id);
	}
	public List<Script>  selectAllScripts() {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		
		List<Script> scripts = (List<Script>)dao.selectAllScripts();
		
		return scripts;
	}
	
	public List<CategoryParameter> selectAllCategoryParameters() {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		List<CategoryParameter> categoryParameter = (List<CategoryParameter>)dao.selectAllCategoryParameters();
		return categoryParameter; 
	}
	
	public CategoryParameter selectCategoryParameterById(Long cat_id) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		CategoryParameter categoryParameter = (CategoryParameter)dao.selectCategoryParameterById(cat_id);
		return categoryParameter; 
	}
	
	public void deleteCategoryParameter(CategoryParameter categoryParemeter) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		dao.deleteCategoryParameter(categoryParemeter);
	
	}
	
	public void deleteParametersByCatId(Long cat_id) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		dao.deleteParametersByCatId(cat_id);
	
	}
	
	public List<BrowserDriver>  selectBrowsers() {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		
		List<BrowserDriver> browsers = (List<BrowserDriver>)dao.selectBrowsers();
		
		return browsers;
	}
	
	public List<Parameter> selectParameterByCategoryId(Long cat_id){
	RobotDao dao=sqlSession.getMapper(RobotDao.class);
		
		List<Parameter> parameters = (List<Parameter>)dao.selectParameterByCategoryId(cat_id);
		
		return parameters;
	}
	
	public void  updateScript(Script script) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		
		dao.updateScript(script);
	}
	
	public void updateParameter(Parameter parameter) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		dao.updateParameter(parameter);
	}

	public void  updateBrowserDriver(BrowserDriver browserDriver) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		
		dao.updateBrowserDriver(browserDriver);
	}

	public void  updateCategoryParameter(CategoryParameter categoryParameter) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		dao.updateCategoryParameter(categoryParameter);
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
	
	public Parameter selectParameterById(Long parameter_id) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		return (Parameter) dao.selectParameterById(parameter_id);
	}
	public void insertParameter(Parameter parameter) {
		RobotDao dao=sqlSession.getMapper(RobotDao.class);
		 dao.insertParameter(parameter);
	}
	
}
