package com.springboot.project.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpRequestHelper {

public enum Platform {
		
		PC("", "pc"), SMART("s_", "smart");

		private String jspPrefix;
		private String folderPath;
		
		private Platform(String jspPrefix, String folderPath) {
			this.jspPrefix = jspPrefix;
			this.folderPath = folderPath;
		}

		public String getJspPrefix() {
			return jspPrefix;
		}

		public void setJspPrefix(String jspPrefix) {
			this.jspPrefix = jspPrefix;
		}

		public String getFolderPath() {
			return folderPath;
		}

		public void setFolderPath(String folderPath) {
			this.folderPath = folderPath;
		}
				
	}
	
	public static Platform getPlatformByUri(String uri) {
		if ( uri.contains("/s_")) {
			return Platform.SMART;
		} else {
			return Platform.PC;
		}
	}
	
	public static String getUri() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String uri = request.getRequestURI();
		
		return uri.toString();
	}
	
	public static String getJspPath() {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		Platform platform = getPlatformByUri(request.getRequestURI());
		String uri = request.getRequestURI();
		String jsp = uri.substring(0, uri.lastIndexOf("."));
		
		StringBuffer fullPath = new StringBuffer();
		String folder = platform.getFolderPath(); fullPath.append(folder);
		fullPath.append(jsp);				
		
		return fullPath.toString();		
	}
}
