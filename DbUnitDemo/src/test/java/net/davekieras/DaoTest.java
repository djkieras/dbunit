package net.davekieras;

import net.davekieras.dao.TestDao;
import net.davekieras.daoentity.TestEntity;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class DaoTest extends AbstractTest {

	@SuppressWarnings("unused")
	private static Logger LOG = LoggerFactory.getLogger(DaoTest.class);
			
	@Autowired
	private TestDao dao;

	@Test
	public void testFind() {
		TestEntity entity = dao.findById(1L);
		Assert.assertNotNull(entity);
		Assert.assertEquals(entity.getTransactionId(), "11111");
		Assert.assertEquals(1L, entity.getDetails().size());
	}

	/*
	@Test
	public void testInsert() {
	}
	
	@Test
	public void testUpdate() {
	}

*/
}
