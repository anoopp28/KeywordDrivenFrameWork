package com.qa.keyword.test;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.qa.keyword.engine.KeywordEngine;

public class LoginTest {
	
	public KeywordEngine keywordengine;
	
	@Test
	public void loginTest1() throws IOException, InvalidFormatException {
		KeywordEngine keywordengine = new KeywordEngine();
	keywordengine.startExecution("Login");
	}
		

		@Test
		public void signupTest() throws IOException, InvalidFormatException {
			KeywordEngine keywordengine = new KeywordEngine();
			keywordengine.startExecution("signup");
	}

}
