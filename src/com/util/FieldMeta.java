package com.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义注解用于生成相应的文档
 * @comment:
 * @author: luoxinwei(luoxinwei@gstarcad.com) 
 * @DATE:2015-11-4 @TIME: 下午8:29:38
 */
@Retention(RetentionPolicy.RUNTIME)// 注解会在class字节码文件中存在，在运行时可以通过反射获取到  
@Target({ElementType.FIELD,ElementType.METHOD})//定义注解的作用目标**作用范围字段、枚举的常量/方法  
@Documented//说明该注解将被包含在javadoc中  
public @interface FieldMeta {
	
	/**
	 * 是否为主键
	 * 
	 * @return
	 */
	boolean isPrimaryKey() default false;
	/** 
     * 字段描述 
     * @return 
     */  
	 String description() default "";
	 
	   /** 
	     * 字段大小
	    * @return 
	    */  
	  int size() default 0;
	  /** 
	     * 小数位数
	    * @return 
	    */  
	  int digit() default 0;

	/**
	 * 是否为空
	 * 
	 * @return
	 */  
    boolean isNull() default true; 
}
