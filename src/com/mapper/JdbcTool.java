package com.mapper.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.util.PropertiesHelper;

public class JdbcTool extends JdbcTemplate  {
	private static JdbcTool jdbcTool=null;
    public static JdbcTool getInstance(){
        if(jdbcTool==null){
            synchronized(JdbcTool.class){
                if(jdbcTool==null){
        			PropertiesHelper helper = new PropertiesHelper("\\WEB-INF\\classes\\gzjc_jdbc.properties");
        			helper.getProperties("biddingFile_path");
                	BasicDataSource dataSource = new BasicDataSource();
            		dataSource.setDriverClassName(helper.getProperties("driverClassName"));
            		dataSource.setUrl(helper.getProperties("jdbc_url"));
            		dataSource.setUsername(helper.getProperties("jdbc_username"));
            		dataSource.setPassword(helper.getProperties("jdbc_password"));
            		dataSource.setInitialSize(5); // 连接池启动时创建的初始化连接数量（默认值为0）
            		dataSource.setMaxActive(20); // 连接池中可同时连接的最大的连接数
            		dataSource.setMaxIdle(12); // 连接池中最大的空闲的连接数，超过的空闲连接将被释放，如果设置为负数表示不限
            		dataSource.setMinIdle(0); // 连接池中最小的空闲的连接数，低于这个数量会被创建新的连接
            		dataSource.setMaxWait(60000); // 最大等待时间，当没有可用连接时，连接池等待连接释放的最大时间，超过该时间限制会抛出异常，如果设置-1表示无限等待
            		dataSource.setRemoveAbandonedTimeout(180); // 超过时间限制，回收没有用(废弃)的连接
            		dataSource.setRemoveAbandoned(true); // 超过removeAbandonedTimeout时间后，是否进 行没用连接（废弃）的回收
            		dataSource.setTestOnBorrow(true);
            		dataSource.setTestOnReturn(true);
            		dataSource.setTestWhileIdle(true);
            		dataSource.setValidationQuery("select count(*) from cems_buyer");
            		dataSource.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30); // 检查无效连接的时间间隔 设为30分钟
            		jdbcTool = new JdbcTool(dataSource);
            		jdbcTool.setQueryTimeout(1000*60*60*12);
                }
            }
        }
        return jdbcTool;
    }
    private JdbcTool(DataSource dataSource){
    	super.setDataSource(dataSource);
    }
    private JdbcTool(){}
}
