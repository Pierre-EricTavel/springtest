/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springtest;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;
import net.itta.springtest.FirstController;
import net.itta.springtest.LeServiceExpress;
import net.itta.springtest.config.WebAppContext;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Before;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import sun.misc.IOUtils;

@ActiveProfiles({"integration"})
public class HttpRequestTest {

    @Before
    public void setup() {

    }

    @Test
    public void httpTestsNominauxdeFirstController() throws MalformedURLException, IOException {
        URL url = new URL("http://localhost:8084/ittaspringTest/premiere.html");
        HttpURLConnection conx = (HttpURLConnection) url.openConnection();
        conx.setRequestMethod("GET");
        conx.setDoOutput(true);
        String s = readstring(conx.getInputStream());

        Assert.assertThat(s, Matchers.containsString("<h1>Premiere</h1>"));
        conx.disconnect();
    }

    String readstring(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String r = null;
        StringBuilder sb = new StringBuilder();
        while ((r = br.readLine()) != null) {
            sb.append(r);
        }
        return sb.toString();
    }

    @Test
    public void whenSendPostRequestUsingApacheHttpClient_thenCorrect()
            throws ClientProtocolException, IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8084/ittaspringTest/premiere.html?id=10");
            CloseableHttpResponse response = client.execute(httpGet);
            assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
            String s = readstring(response.getEntity().getContent());
            Assert.assertThat(s, Matchers.containsString("<h1>Premiere</h1>"));
        }
    }

    @Test
    public void whenSendGetRequestUsingRestTemplate_thenCorrect() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8084/ittaspringTest/premiere.html?id=10", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), Matchers.containsString("<h1>Premiere</h1>"));
    }

    @Test
    public void whenSendGetRequestUsingRestTemplate100_thenErreur() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8084/ittaspringTest/premiere.html?id=100", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), Matchers.containsString("<h1>Erreur d'exception</h1>"));
    }

    @Test
    public void whenSendGetRequestUsingRestTemplate200_thenErreur() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {  return false; }
            public void handleError(ClientHttpResponse response) throws IOException {    }
        });

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8084/ittaspringTest/premiere.html?id=200", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), Matchers.containsString("<h1>Erreur 404</h1>"));
    }

@Test
        public void whenSendGetRequestUsingRestTemplate600_thenErreur()  {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity   <String> response  = 
                restTemplate.getForEntity("http://localhost:8084/ittaspringTest/premiere.html?id=600", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), Matchers.containsString("<h1>Erreur d'exception</h1>"));
    }
    
    
}
