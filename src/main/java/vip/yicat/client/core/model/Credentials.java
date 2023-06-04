package vip.yicat.client.core.model;

public class Credentials {

    private final String token;
    private String baseUrl;

    public Credentials(String token) {
        this.token = token;
    }

    public Credentials(String token, String baseUrl) {
        this.token = token;
        this.baseUrl = baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

}
