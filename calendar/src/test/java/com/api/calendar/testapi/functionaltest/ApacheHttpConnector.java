package com.api.calendar.testapi.functionaltest;

import static com.api.calendar.testapi.util.ObjectMappers.MAPPER_SIMPLE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Deque;
import java.util.LinkedList;
import javax.net.ssl.SSLContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

@Slf4j
public class ApacheHttpConnector {

    private final HttpClient httpClient;
    private final ConnectionPool connectionPool;

    public ApacheHttpConnector() {
        this.httpClient = getClient();
        connectionPool = new ConnectionPool();
    }

    private CloseableHttpClient getClient() {
        CloseableHttpClient getHttpClient;
        try {
            final SSLContext sslContext;

            sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (x509CertChain, authType) -> true)
                .build();

            getHttpClient = HttpClientBuilder.create()
                .setSSLContext(sslContext)
                .setConnectionManager(
                    new PoolingHttpClientConnectionManager(
                        RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.INSTANCE)
                            .register("https", new SSLConnectionSocketFactory(sslContext,
                                NoopHostnameVerifier.INSTANCE))
                            .build()
                    ))
                .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new RuntimeException("getClient ", e);
        }
        return getHttpClient;
    }

    public HttpResponse httpGet(String endpoint) throws IOException {
        log.info("performing http get request to {}", endpoint);
        HttpGet httpGet = new HttpGet(endpoint);
        HttpResponse httpResponse = httpClient.execute(setDefaultHeaders(httpGet));
        connectionPool.add(httpGet);

        log.info("http get request status code {}", httpResponse.getStatusLine().getStatusCode());
        return httpResponse;
    }


    public HttpResponse httpPost(String endpoint) throws IOException {
        return httpPost(endpoint, MAPPER_SIMPLE);
    }

    public <E> HttpResponse httpPost(String endpoint, E e) throws IOException {
        return httpPost(endpoint, MAPPER_SIMPLE, e);
    }

    public HttpResponse httpPost(String endpoint, ObjectMapper objectMapper) throws IOException {
        return httpPost(endpoint, objectMapper, null);
    }

    public <E> HttpResponse httpPost(String endpoint, ObjectMapper objectMapper, E e) throws IOException {
        log.info("performing http post request to {}", endpoint);
        HttpPost httpPost = new HttpPost(endpoint);
        if (e != null) {
            HttpEntity httpEntity = e instanceof HttpEntity ? (HttpEntity) e
                : new StringEntity(objectMapper.writeValueAsString(e), ContentType.APPLICATION_JSON);
            httpPost.setEntity(httpEntity);
        }
        HttpResponse httpResponse = httpClient.execute(setDefaultHeaders(httpPost));
        connectionPool.add(httpPost);

        log.info("http post request status code {}", httpResponse.getStatusLine().getStatusCode());
        return httpResponse;
    }

    public HttpResponse httpXmlPost(String endpoint, String payload) throws IOException {
        log.info("performing http xml post request to {}", endpoint);
        HttpPost httpPost = new HttpPost(endpoint);
        httpPost.setEntity(new ByteArrayEntity(payload.getBytes(UTF_8), ContentType.create("application/xml")));
        HttpResponse httpResponse = httpClient.execute(setDefaultHeaders(httpPost));
        connectionPool.add(httpPost);
        log.info("http xml post request status code {}", httpResponse.getStatusLine().getStatusCode());
        return httpResponse;
    }

    public <E> HttpResponse httpPut(String endpoint, E e) throws IOException {
        return httpPut(endpoint, MAPPER_SIMPLE, e);
    }

    public <E> HttpResponse httpPut(String endpoint, ObjectMapper objectMapper, E e) throws IOException {
        log.info("performing http put request to {}", endpoint);

        HttpEntity httpEntity = e instanceof HttpEntity ? (HttpEntity) e
            : new StringEntity(objectMapper.writeValueAsString(e), ContentType.APPLICATION_JSON);

        HttpPut httpPut = new HttpPut(endpoint);
        httpPut.setEntity(httpEntity);
        HttpResponse httpResponse = httpClient.execute(setDefaultHeaders(httpPut));
        connectionPool.add(httpPut);

        log.info("http put request status code {}", httpResponse.getStatusLine().getStatusCode());
        return httpResponse;
    }

    public HttpResponse httpXmlPut(String endpoint, String payload) throws IOException {
        log.info("performing http xml put request to {}", endpoint);
        HttpPut httpPut = new HttpPut(endpoint);
        httpPut.setEntity(new ByteArrayEntity(payload.getBytes(UTF_8), ContentType.create("application/xml")));
        HttpResponse httpResponse = httpClient.execute(setDefaultHeaders(httpPut));
        connectionPool.add(httpPut);
        log.info("http xml put request status code {}", httpResponse.getStatusLine().getStatusCode());
        return httpResponse;
    }

    public HttpResponse httpDelete(String endpoint) throws IOException {
        log.info("performing http delete request to {}", endpoint);
        HttpResponse httpResponse = httpClient.execute(setDefaultHeaders(new HttpDelete(endpoint)));

        log.info("http delete request status code {}", httpResponse.getStatusLine().getStatusCode());
        return httpResponse;
    }


    private <E extends HttpRequestBase> E setDefaultHeaders(E requestBase) {
        requestBase.setHeader("Accept", ContentType.APPLICATION_JSON.toString());
        return requestBase;
    }


    private static class ConnectionPool {
        private final Deque<HttpRequestBase> responsePool = new LinkedList<>();

        void add(HttpRequestBase connection) {
            while (responsePool.size() >= 3) {
                HttpRequestBase oldest = responsePool.removeFirst();
                oldest.releaseConnection();
            }
            responsePool.addLast(connection);
        }
    }

    public static void validateStatus(int expectedStatus, HttpResponse httpResponse) {
        int actualStatus = httpResponse.getStatusLine().getStatusCode();
        assertEquals(getResponseContentCaseMismatchStatus(httpResponse, expectedStatus, actualStatus), expectedStatus, actualStatus);
    }

    private static String getResponseContentCaseMismatchStatus(HttpResponse httpResponse, int expectedStatus, int actualStatus) {
        if (expectedStatus == actualStatus) {
            return null;
        }

        String responseString = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            httpResponse.getEntity().writeTo(out);
            responseString = out.toString();
        } catch (IOException e) {
            log.error("reading stream getResponseContentCaseMismatchStatus -> {}", e.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                log.error("closing stream getResponseContentCaseMismatchStatus -> {}", e.getMessage());
            }
        }

        return responseString;
    }


    public static <T> T readResponse(HttpResponse httpResponse, Class<T> clazz) throws IOException {
        MAPPER_SIMPLE.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return MAPPER_SIMPLE.readValue(EntityUtils.toString(httpResponse.getEntity()), clazz);
    }


}
