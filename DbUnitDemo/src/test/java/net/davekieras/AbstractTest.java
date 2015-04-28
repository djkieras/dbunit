package net.davekieras;


import java.io.InputStream;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.commons.lang3.time.DateUtils;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml"})
public abstract class AbstractTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private DataSource dataSource;

	private IDatabaseTester databaseTester;

	@Before
	public void setUp() throws Exception {
		databaseTester = new DataSourceDatabaseTester(dataSource) {
			@Override 
			public IDatabaseConnection getConnection() throws Exception { 
				IDatabaseConnection dbC = super.getConnection(); 
				dbC.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
				return dbC;
			} 
		};
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dbunit.xml");
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(inputStream);
		ReplacementDataSet rDataSet = new ReplacementDataSet(dataSet);
		rDataSet.addReplacementObject("[now]", new Date());
		rDataSet.addReplacementObject("[tomorrow]", DateUtils.addDays(new Date(), 1));
		rDataSet.addReplacementObject("[yesterday]", DateUtils.addDays(new Date(), -1));
		rDataSet.addReplacementObject("[10MinAgo]", DateUtils.addMinutes(new Date(), -10));
		rDataSet.addReplacementObject("[9MinAgo]", DateUtils.addMinutes(new Date(), -9));
		databaseTester.setDataSet(rDataSet);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setTearDownOperation(DatabaseOperation.NONE);
		databaseTester.onSetup();
	}

	@After
	public void tearDown() throws Exception {
		if (databaseTester != null) {
			databaseTester.onTearDown();
		}
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public IDatabaseTester getDatabaseTester() {
		return databaseTester;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
