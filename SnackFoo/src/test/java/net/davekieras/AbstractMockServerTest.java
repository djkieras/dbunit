package net.davekieras;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.davekieras.properties.SnackFooProperties;
import net.davekieras.util.HttpTestServer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mockServer/applicationContext.xml" })
public abstract class AbstractMockServerTest {

	private static Logger LOG = LoggerFactory.getLogger(AbstractMockServerTest.class);
	
    private HttpTestServer server;
    
	@Autowired
	private SnackFooProperties props;
    
    @Before
    /**
     * Starts up the test server
     *  
     * @throws Exception
     */
    public void startTestServer() throws Exception {
        server = new HttpTestServer();
        server.start();
        LOG.info("Server port is set to " + server.getPort());
        getApplicantServicesProperties().setSnackListUrl("http://localhost:" + server.getPort());
        getApplicantServicesProperties().setSnackSuggestionUrl("http://localhost:" + server.getPort());
    }
 
	public SnackFooProperties getApplicantServicesProperties() {
		return this.props;
	}
	
    @After
    public void stopTestServer() throws Exception {
        server.stop();
    }
 
    protected HttpTestServer getTestServer() {
    	return this.server;
    }
    
    protected void setResponse(String filePath) throws IOException {
    	getTestServer().setMockResponseData(getRecordedResponse(filePath));
    }
    
    protected String getRecordedResponse(String responseFile) throws IOException {
        InputStream is = null;
        String result = null;
        try {
        	is = Thread.currentThread().getContextClassLoader().getResourceAsStream(responseFile);
        	if (is == null) {
        		LOG.error("Resource " + responseFile + " could not be found.");
        	} else {
        		result = IOUtils.toString(is);
        	}
        } finally {
        	try {
        		is.close();
        	} catch (IOException e) {
        		LOG.error("Could not close input stream.");
        	}
        	is = null;
        }
        return result;
    }
    
    protected void assertCalendarEquals(String dateString, Calendar cal) throws ParseException {
    	Assert.assertEquals(toCalendar(dateString).getTimeInMillis(), cal.getTimeInMillis());
    }

	private Calendar toCalendar(String dateValue) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateValue);
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		return result;
	}
}
