package vip.yicat.client.core.http.impl.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import vip.yicat.client.core.http.HttpClient;
import vip.yicat.client.core.http.HttpRequestConfig;
import vip.yicat.client.core.http.JsonTransformer;
import vip.yicat.client.core.http.exceptions.HttpBadRequestException;
import vip.yicat.client.core.http.exceptions.HttpException;
import vip.yicat.client.core.http.exceptions.YiCATAPIException;
import vip.yicat.client.core.model.ClientConfig;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.core.model.FileObject;
import vip.yicat.client.core.model.ResponseResult;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static vip.yicat.client.core.constants.YiCATConstants.FAIL;

public class ApacheHttpClient implements HttpClient {

    private final Credentials credentials;
    private final JsonTransformer jsonTransformer;
    private final Map<String, ?> defaultHeaders;
    private ClientConfig.Host proxy;
    private ClientConfig.UsernamePasswordCredentials proxyCreds;

    private final CloseableHttpClient httpClient;

    public ApacheHttpClient(Credentials credentials, JsonTransformer jsonTransformer, Map<String, ?> defaultHeaders) {
        this.credentials = credentials;
        this.jsonTransformer = jsonTransformer;
        this.defaultHeaders = defaultHeaders;
        this.httpClient = HttpClientBuilder.create().build();
    }

    public ApacheHttpClient(Credentials credentials, JsonTransformer jsonTransformer, Map<String, ?> defaultHeaders, ClientConfig.Host proxy, ClientConfig.UsernamePasswordCredentials proxyCreds) {
        this.credentials = credentials;
        this.jsonTransformer = jsonTransformer;
        this.defaultHeaders = defaultHeaders;
        this.proxy = proxy;
        this.proxyCreds = proxyCreds;
        this.httpClient = (proxy != null)
                ? HttpClientBuilder.create()
                .setProxy(new HttpHost(proxy.getHost(), proxy.getPort()))
                .setDefaultCredentialsProvider((proxyCreds != null)
                        ? new BasicCredentialsProvider() {{
                    setCredentials(new AuthScope(proxy.getHost(), proxy.getPort()), new UsernamePasswordCredentials(proxyCreds.getUsername(), proxyCreds.getPassword()));
                }}
                        : new BasicCredentialsProvider())
                .build()
                : HttpClientBuilder.create().build();
    }

    @Override
    public <T> T get(String url, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, null, config, clazz, HttpGet.METHOD_NAME);
    }

    @Override
    public <T, V> T get(String url, V data, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, data, config, clazz, HttpGet.METHOD_NAME);
    }

    @Override
    public <T> T delete(String url, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, null, config, clazz, HttpDelete.METHOD_NAME);
    }

    @Override
    public <T, V> T delete(String url, V data, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, data, config, clazz, HttpDelete.METHOD_NAME);
    }

    @Override
    public <T> T head(String url, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, null, config, clazz, HttpHead.METHOD_NAME);
    }

    @Override
    public <T, V> T post(String url, V data, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, data, config, clazz, HttpPost.METHOD_NAME);
    }

    @Override
    public <T, V> T put(String url, V data, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, data, config, clazz, HttpPut.METHOD_NAME);
    }

    @Override
    public <T, V> T patch(String url, V data, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException {
        return this.request(url, data, config, clazz, HttpPatch.METHOD_NAME);
    }

    @Override
    public InputStream executeDownload(String url, HttpRequestConfig config) {
        HttpUriRequest request = this.buildRequest(HttpGet.METHOD_NAME, url, null, config);
        try {
            return httpClient.execute(request).getEntity().getContent();
        } catch (IOException e) {
            throw HttpException.fromMessage(e.getMessage());
        }
    }

    private <T, V> T request(String url,
                             V data,
                             HttpRequestConfig config,
                             Class<T> clazz,
                             String method) throws HttpException, HttpBadRequestException {
        HttpUriRequest request = this.buildRequest(method, url, data, config);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if (Void.class.equals(clazz)) {
                return null;
            }
            ResponseResult responseResult = this.jsonTransformer.parse(this.toString(response.getEntity()), ResponseResult.class);
            if (FAIL.equals(responseResult.getStatus())) {
                String error = responseResult.getErrMsg();
                throw this.jsonTransformer.parse(error, YiCATAPIException.class);
            }
            if (String.class.equals(clazz) || Map.class.equals(clazz)) {
                return (T) responseResult.getResult();
            }
            if (ResponseResult.class.equals(clazz)) {
                return (T) responseResult;
            }
            return JSON.parseObject(JSONObject.toJSONString(responseResult.getResult()), clazz);
        } catch (IOException e) {
            throw HttpException.fromMessage(e.getMessage());
        }
    }

    private <V> HttpUriRequest buildRequest(String httpMethod, String url, V data, HttpRequestConfig config) {
        RequestBuilder requestBuilder = RequestBuilder.create(httpMethod);
        requestBuilder.setCharset(Consts.UTF_8);
        requestBuilder.setUri(URI.create(this.appendUrlParams(url, config.getUrlParams())));
        requestBuilder.addHeader("Authorization", "Bearer " + this.credentials.getToken());
        if (data != null) {
            HttpEntity entity;
            if (data instanceof FileObject) {
                FileObject fileObject = (FileObject) data;
                MultipartEntityBuilder builder = MultipartEntityBuilder.create().setCharset(Consts.UTF_8);
                try {
                    builder.addBinaryBody(
                            fileObject.getParmName(),
                            new FileInputStream(fileObject.getFile()),
                            ContentType.APPLICATION_OCTET_STREAM,
                            //使用`URLEncoder.encode()`方法将文件名编码为UTF-8格式，避免文件名乱码问题
                            URLEncoder.encode(fileObject.getFile().getName(), "UTF-8")
                    );
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                entity = builder.build();
            } else if (data instanceof String) {
                entity = new StringEntity((String) data, ContentType.APPLICATION_OCTET_STREAM);
            } else {
                entity = new StringEntity(this.jsonTransformer.convert(data), ContentType.APPLICATION_JSON);
            }
            requestBuilder.setEntity(entity);
        }
        Map<String, Object> headers = new HashMap<>();
        headers.putAll(config.getHeaders());
        headers.putAll(this.defaultHeaders);
        for (Map.Entry<String, ?> entry : headers.entrySet()) {
            requestBuilder = requestBuilder.addHeader(entry.getKey(), entry.getValue().toString());
        }
        return requestBuilder.build();
    }

    private String toString(HttpEntity entity) throws IOException {
        final InputStream stream = entity.getContent();
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(stream, StandardCharsets.UTF_8);
        int charsRead;
        while ((charsRead = in.read(buffer, 0, buffer.length)) > 0) {
            out.append(buffer, 0, charsRead);
        }
        return out.toString();
    }

}
