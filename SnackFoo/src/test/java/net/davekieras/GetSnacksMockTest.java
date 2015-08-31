package net.davekieras;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import net.davekieras.model.Snack;
import net.davekieras.properties.SnackFooProperties;

public class GetSnacksMockTest extends AbstractMockServerTest {

	private static Logger LOG = LoggerFactory.getLogger(GetSnacksMockTest.class);

	@Autowired
	private SnackFooProperties props;

	@Before
	/**
	 * Sets the default response before every test. Tests can specify their own
	 * response.
	 * 
	 * @throws Throwable
	 */
	public void setupServer() throws Throwable {
		setResponse("mockServer/sampleResponse.json");
	}

	@Test
	public void testGetResponse() throws IOException {
		String url = props.getSnackListUrl();
		HttpHost targetHost = new HttpHost("localhost", 50036, "http");
		HttpClientContext localContext = HttpClientContext.create();
		HttpGet get = new HttpGet(url);
		try (CloseableHttpClient httpclient = HttpClients.custom().build();) {
			try (CloseableHttpResponse response = httpclient.execute(targetHost, get, localContext)) {
				Assert.assertNotNull(response);
			}
		}
	}

	@Test
	public void testGetResponseObject() throws Throwable {
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJaxbJsonProvider());

		DateFormat df = new SimpleDateFormat("M/d/yyyy");
		df.setTimeZone(TimeZone.getTimeZone(SnackFooProperties.TIME_ZONE_ID));
		
		WebClient client = null;
		try {
			client = WebClient.create("http://localhost:50036", providers);
			Snack[] response = client.path("/").accept(MediaType.APPLICATION_JSON, "text/json")
					.header("content-type", MediaType.APPLICATION_JSON).get(Snack[].class);
			Assert.assertNotNull(response);
			Assert.assertEquals(3, response.length);
			Assert.assertEquals(Long.valueOf(6L), response[0].getId());
			Assert.assertEquals("Gorp", response[0].getName());
			Assert.assertEquals(true, response[0].isOptional());
			Assert.assertEquals(2L, response[0].getPurchaseLocations().size());
			Assert.assertEquals("John's Grocery", response[0].getPurchaseLocations().get(0));
			Assert.assertEquals("Corner C-Store", response[0].getPurchaseLocations().get(1));
			Assert.assertEquals(0L, response[0].getPurchaseCount());
			Assert.assertNull(response[0].getLastPurchaseDate());
			
			Assert.assertEquals(Long.valueOf(17L), response[1].getId());
			Assert.assertEquals("Bacon Jerky", response[1].getName());
			Assert.assertEquals(false, response[1].isOptional());
			Assert.assertEquals(1L, response[1].getPurchaseLocations().size());
			Assert.assertEquals("John's Grocery", response[1].getPurchaseLocations().get(0));
			Assert.assertEquals(542L, response[1].getPurchaseCount());
			Assert.assertNotNull(response[1].getLastPurchaseDate());
			Assert.assertEquals(df.parse("8/18/2015"), response[1].getLastPurchaseDate());
			
			Assert.assertEquals(Long.valueOf(18L), response[2].getId());
			Assert.assertEquals("Beef Jerky", response[2].getName());
			Assert.assertEquals(false, response[2].isOptional());
			Assert.assertNull(response[2].getPurchaseLocations());
			Assert.assertEquals(5L, response[2].getPurchaseCount());
			Assert.assertNotNull(response[2].getLastPurchaseDate());
			Assert.assertEquals(df.parse("8/19/2015"), response[2].getLastPurchaseDate());
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}

}
