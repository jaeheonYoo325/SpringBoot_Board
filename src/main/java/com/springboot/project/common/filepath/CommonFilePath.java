package com.springboot.project.common.filepath;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommonFilePath {

	@Bean("uploadFilePath")
	public String uploadFilePath() {
		return "E:\\SpringBoot_Board\\BoardProjectFiles\\uploadFiles";
	}
	
	@Bean("imageFilePath")
	public String imageFilePath() {
		return "E:\\SpringBoot_Board\\BoardProjectFiles\\imageFile";
	}
}
