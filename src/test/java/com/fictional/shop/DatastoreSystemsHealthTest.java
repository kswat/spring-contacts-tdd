package com.fictional.shop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DatastoreSystemsHealthTest {

	@Autowired
	DataSource dataSource;
	
	@Test
	public void dbPrimaryIsOk() {
		try {
			DatabaseMetaData metadata = dataSource.getConnection().getMetaData();
			String catalogName = dataSource.getConnection().getCatalog();
			
			assertNotNull(metadata);
			assertEquals(catalogName, "spring-tdd");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
