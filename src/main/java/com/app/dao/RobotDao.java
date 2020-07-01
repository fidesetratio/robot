package com.app.dao;

import java.util.List;
import java.util.Map;

import com.app.model.BrowserDriver;
import com.app.model.CategoryParameter;
import com.app.model.Parameter;
import com.app.model.ResultTestCase;
import com.app.model.Script;
import com.app.model.TestCase;

public interface RobotDao {
	public TestCase selectTestCaseById(Long job_id);
	public Script selectScriptById(Long id); 
	public void insertResultTestCaseReff(Map params);
	public void insertScript(Script script);
	public void updateScript(Script script);
	public void updateBrowserDriver(BrowserDriver browserDriver);
	public void updateCategoryParameter(CategoryParameter categoryParameter);
	public void updateParameter(Parameter parameter);
	
	public void insertBrowserDriver(BrowserDriver browserDriver);
	public void deleteBrowserId(BrowserDriver browserDriver);
	public void deleteCategoryParameter(CategoryParameter categoryParemeter);
	public void deleteParametersByCatId(Long cat_id);
	public void deleteParametersByParameterId(Long parameter_id);
	public List<Script> selectAllScripts();
	public List<CategoryParameter> selectAllCategoryParameters();
	public List<Parameter> selectParameterByCategoryId(Long cat_id);
	public Parameter selectParameterById(Long parameter_id);
	public CategoryParameter selectCategoryParameterById(Long cat_id);
	public BrowserDriver selectBrowserDriverById(Long browser_id);
	public List<BrowserDriver> selectBrowsers();
	public void insertResultTestCase(ResultTestCase resultTestCase);
	public void insertCategoryParameter(CategoryParameter categoryParameter);
	public void insertParameter(Parameter parameter);
	
	
	public List<ResultTestCase> selectResultByReffAndPrefix(Map params);
}
