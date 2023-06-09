package vip.yicat.client.core;

import vip.yicat.client.core.http.HttpClient;
import vip.yicat.client.core.http.JsonTransformer;
import vip.yicat.client.core.http.impl.http.ApacheHttpClient;
import vip.yicat.client.core.http.impl.json.JacksonJsonTransformer;
import vip.yicat.client.core.model.ClientConfig;
import vip.yicat.client.core.model.Credentials;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class YiCATApi {

    protected final HttpClient httpClient;
    protected final ClientConfig clientConfig;
    protected final String url;

    public YiCATApi(Credentials credentials) {
        this(credentials, ClientConfig.builder().httpClient(new ApacheHttpClient(credentials, new JacksonJsonTransformer(), Collections.emptyMap())).build());
    }

    public YiCATApi(Credentials credentials, ClientConfig clientConfig) {
        Map<String, String> defaultHeaders = new HashMap<>();
        if (clientConfig.getUserAgent() != null) {
            defaultHeaders.put("User-Agent", clientConfig.getUserAgent());
        }
        JsonTransformer jsonTransformer = (clientConfig.getJsonTransformer() != null)
            ? clientConfig.getJsonTransformer() : new JacksonJsonTransformer();
        this.httpClient = (clientConfig.getHttpClient() != null)
            ? clientConfig.getHttpClient()
            : new ApacheHttpClient(credentials, jsonTransformer, defaultHeaders, clientConfig.getProxy(), clientConfig.getProxyCreds());
        this.clientConfig = clientConfig;
        if (credentials.getBaseUrl() != null) {
            if (credentials.getBaseUrl().endsWith("/")) {
                this.url = credentials.getBaseUrl() + "api/v1";
            } else {
                this.url = credentials.getBaseUrl() + "/api/v1";
            }
        } else {
            this.url = "https://www.yicat.vip/api/v1";
        }
    }
}
