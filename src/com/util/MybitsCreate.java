package com.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;

public class MybitsCreate {
 
	public static void main(String[] args) {
   	  
	}
	public static String   getObjectAttrs(Class<?> clz) {
		    StringBuilder sb = new StringBuilder();
		    StringBuilder sb_result = new StringBuilder();
    	    StringBuilder sb_insert = new StringBuilder("(");
    	    StringBuilder sb_up = new StringBuilder();
    	    StringBuilder sb_sql = new StringBuilder("<sql id=\""+clz.getSimpleName().toLowerCase()+"Columns\">");
	      if (clz != null) {//if (object!=null )  ----begin  
	          // 拿到该类  
	          // 获取实体类的所有属性，返回Field数组  
	          Field[] fields = clz.getDeclaredFields(); 
	          for (int i = 0; i < fields.length; i++) {
	        	 Type type = fields[i].getGenericType();
	        	//根据自定义注解得到相应的字段说明
	           FieldMeta meta = fields[i].getAnnotation(FieldMeta.class);
	  		   if (meta != null) {  
				if (i!=0) {
					sb_insert.append(",");
    				sb_up.append(",");
    				sb_sql.append(",");
				}
				sb_result.append("<!--").append(meta.description()).append("-->\n");
				if (meta.isPrimaryKey()) {
					sb_result.append("<id column=\"").append(fields[i].getName().trim()).append("\"");
	    			
				}else {
					sb_result.append("<result column=\"").append(fields[i].getName().trim()).append("\"");
				}
    			sb_result.append(" property=\"").append(fields[i].getName().trim()).append("\"/>\n");
    			
    			sb_insert.append("#{").append(fields[i].getName().trim());
    			sb_up.append(fields[i].getName().trim()).append("=").append("#{").append(fields[i].getName().trim());
    			/*if (String.class.toString().equals(type.toString())) {
    				sb_insert.append(",jdbcType=VARCHAR");
    				sb_up.append(",jdbcType=VARCHAR");
				}
				if (Date.class.toString().equals(type.toString())) {
					sb_insert.append(",jdbcType=TIMESTAMP");
					sb_up.append(",jdbcType=TIMESTAMP");
				}
				if (Integer.class.toString().equals(type.toString())) {
					sb_insert.append(",jdbcType=INTEGER");
					sb_up.append(",jdbcType=INTEGER");
				}
				if (Float.class.toString().equals(type.toString())) {
					sb_insert.append(",jdbcType=FLOAT");
					sb_up.append(",jdbcType=FLOAT");
				}
				if (Double.class.toString().equals(type.toString())) {
					sb_insert.append(",jdbcType=DOUBLE");
					sb_up.append(",jdbcType=DOUBLE");
				}*/
    			sb_insert.append("}");
    			sb_up.append("}");
    			sb_sql.append(fields[i].getName().trim());
			}
	      }
	          sb_insert.append(")");
	          sb_sql.append("</sql>");
	          sb.append(sb_result.toString().trim()+"\n\n");
	    	  sb.append(sb_sql.toString().trim()+"\n\n");
	    	  sb.append(sb_insert.toString().trim()+"\n\n");
	    	  sb.append(sb_up.toString().trim()+"\n\n");
	    	  
	      } 
	      return sb.toString();
	  }   
	  public static void WriteStringToFile2(String filePath,String str) {
	        try {
	            FileWriter fw = new FileWriter(filePath, false);
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(str);
	            bw.close();
	            fw.close();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
}
