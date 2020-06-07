package com.app.dao;

import com.app.model.TestCase;

public interface RobotDao {
	public TestCase selectTestCaseById(Long job_id);
}
