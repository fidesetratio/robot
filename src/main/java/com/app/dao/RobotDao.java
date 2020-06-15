package com.app.dao;

import java.util.List;
import java.util.Map;

import com.app.model.BrowserDriver;
import com.app.model.ResultTestCase;
import com.app.model.Script;
import com.app.model.TestCase;

public interface RobotDao {
	public TestCase selectTestCaseById(Long job_id);
	public Script selectScriptById(Long id); 
	public void insertResultTestCaseReff(Map params);
	public void insertScript(Script script);
	public void updateScript(Script script);
	public List<Script> selectAllScripts();
	public BrowserDriver selectBrowserDriverById(Long browser_id);
	public List<BrowserDriver> selectBrowsers();
	public void insertResultTestCase(ResultTestCase resultTestCase);
	public List<ResultTestCase> selectResultByReffAndPrefix(Map params);
}
