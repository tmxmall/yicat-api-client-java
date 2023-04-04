package com.yicat.client.language;

import com.alibaba.fastjson.JSONObject;
import com.yicat.client.Client;
import com.yicat.client.core.model.Credentials;
import lombok.var;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LanguageAPITest {

    @Test
    void getLanguages() {
        Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
        var client = new Client(credentials);
        JSONObject data = client.getLanguageAPI().getLanguages();
        System.out.println(data);
        assertNotNull(data);
    }
}