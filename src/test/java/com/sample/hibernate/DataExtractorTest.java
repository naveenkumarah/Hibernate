package com.sample.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataExtractorTest {

	public static DataSource getMySQLDataSource() {
      
        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL("jdbc:mysql://localhost:3306/employee");
        mysqlDS.setUser("root");
        mysqlDS.setPassword("naveen");
       
        return mysqlDS;
    }
	public static void main(String[] args) throws Exception {
		DBUnitDataExtractor dataExtractor=new DBUnitDataExtractor();
		dataExtractor.setDataSource(getMySQLDataSource());
		List<String> tableList=new ArrayList<String>();
		tableList.add("Employee");
		dataExtractor.setTableList(tableList);
		dataExtractor.extract();
		

	}

}
