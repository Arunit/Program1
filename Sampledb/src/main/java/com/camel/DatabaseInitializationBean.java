package com.camel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


import org.apache.derby.jdbc.EmbeddedDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseInitializationBean  {
	
	private static final Logger LOG = LoggerFactory.getLogger(DatabaseInitializationBean.class);

	String url;
	Connection connection;
	public DatabaseInitializationBean() {
    }		   
	
	public void create() throws Exception {
		 LOG.info("Creating database tables ...");
		 if (connection == null) {
			 EmbeddedDriver driver = new EmbeddedDriver();
			 connection = driver.connect(url + ";create=true", null);
		 }
		 String sql = "create table ORDERS (\n"
				            + " id int primary key,\n"
				               + " name char (20),\n"
				                + "  age int(3),\n"
				                + "  address varchar(30),\n"
				               
				                 + ")";
	
		 try {
	            execute("drop table orders");
	        } catch (Throwable e) {
	            // ignore
	        }

	        execute(sql);

	        LOG.info("Database tables created");
	    }

	    public void drop() throws Exception {
	        LOG.info("Dropping database tables ...");

	        try {
	            execute("drop table orders");
	        } catch (Throwable e) {
	            // ignore
	        }
	        connection.close();

	        LOG.info("Database tables dropped");
	    }

	    private void execute(String sql) throws SQLException {
	        Statement stm = connection.createStatement();
	        stm.execute(sql);
	       
	        connection.commit();
	        stm.close();
	    }

	    public String getUrl() {
	        return url;
	    }

	    public void setUrl(String url) {
	        this.url = url;
	    }
	}