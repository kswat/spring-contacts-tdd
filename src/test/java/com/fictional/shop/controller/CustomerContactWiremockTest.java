package com.fictional.shop.controller;



import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.tomakehurst.wiremock.WireMockServer;

@SpringBootTest
//@TestPropertySource(locations = "classpath:test.properties")
class CustomerContactWiremockTest {

    private WireMockServer wireMockServer;

    @BeforeEach
    void beforeEach() {
        // Start the WireMock Server
        wireMockServer = new WireMockServer();//9999 , if different
        wireMockServer.start();

        // Configure our requests
        wireMockServer.stubFor(get(urlEqualTo("/contact/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/contact-response.json")));
        wireMockServer.stubFor(get(urlEqualTo("/contact/2"))
                .willReturn(aResponse().withStatus(404)));
        wireMockServer.stubFor(post("/contact/1/occassions")
                // Actual Header sent by the RestTemplate is: application/json;charset=UTF-8
                .withHeader("Content-Type", containing("application/json"))
                .withRequestBody(containing("\"id\":1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/contact-response-after-post.json")));
    }

    @AfterEach
    void afterEach() {
        wireMockServer.stop();
    }

    @Test
    void testGetInventoryRecordSuccess() throws ClientProtocolException, IOException, JSONException {

    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	 
    	HttpGet request = new HttpGet("http://localhost:8080/contact/1");
    	HttpResponse httpResponse = httpClient.execute(request);

    	JSONObject resultContact = convertHttpResponseToJson(httpResponse);
    	// Validate the contents of the response
        Assertions.assertEquals("Jenny", resultContact.getString("first_name"),
              "The name should be Jenny for contact 1");
    }

    
    private JSONObject convertHttpResponseToJson(HttpResponse httpResponse) throws IOException, JSONException {
	    InputStream inputStream = httpResponse.getEntity().getContent();
	    return convertInputStreamToJson(inputStream);
	}
    
    private JSONObject convertInputStreamToJson(InputStream inputStream) throws JSONException {
    	Scanner scanner = new Scanner(inputStream, "UTF-8");
	    String string = scanner.useDelimiter("\\Z").next();
	    scanner.close();	    
	    return new JSONObject(string);
    }

}
