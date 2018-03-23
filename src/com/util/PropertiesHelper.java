package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;



/**
 * 配置文件读取和写入工具类
 * @author Jacky.Jiang
 *
 */
public class PropertiesHelper {
	
	private String project_root = "";
	private File file = null;
	
	public PropertiesHelper(String filePath) {
		//构造时获取到项目的物理根目录
		project_root = this.getClass().getResource("/").toString().replace("file:/", "");
		project_root = project_root.substring(0,project_root.indexOf("/WEB-INF"));
		
		if(filePath != null && filePath.length() > 0){
			try {
				file = new File(project_root+filePath);
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
	}
	
	public String getProperties(String key){
		InputStream fis = null;
		try {
			Properties prop = new Properties();
			fis = new FileInputStream(getAbsolutePath());
			
			prop.load(fis);
			
			return prop.getProperty(key);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{if(fis != null){fis.close();}}catch(Exception e){}
		}
		
		return "";
	}
	
	public void setProperties(String key,String value)throws Exception{
		Properties prop = new Properties();
		
		
		FileOutputStream outputFile = null;
		InputStream fis = null;
		try {
			//输入流和输出流要分开处理， 放一起会造成写入时覆盖以前的属性
			fis = new FileInputStream(getAbsolutePath());
			//先载入已经有的属性文件
			prop.load(fis);
			
			//追加新的属性
			prop.setProperty(key, value);
			
			//写入属性
			outputFile = new FileOutputStream(getAbsolutePath()); 
			prop.store(outputFile, "");
			
			outputFile.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			try{if(fis != null){fis.close();}}catch(Exception e){}
			try{if(outputFile != null){outputFile.close();}}catch(Exception e){}
		}
	}
	
	
	public String getAbsolutePath(){
		try {
			return file.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}

