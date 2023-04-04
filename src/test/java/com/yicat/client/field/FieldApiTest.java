package com.yicat.client.field;

import com.yicat.client.Client;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.field.model.ApiTranslationField;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FieldApiTest {

    Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    Client client = new Client(credentials);
    String field = client.getFieldApi().getField().get(0).getFieldId().toString();
    int i = 1;

    @Test
    @Order(1)
    void getField() {
        List<ApiTranslationField> response = client.getFieldApi().getField();
        System.out.println(response);
        assertNotNull(response);
    }

    @Test
    @Order(2)
    void createField() {
        ApiTranslationField response = client.getFieldApi().createField("领域分组" + (++i));
        System.out.println(response);
        assertNotNull(response);
    }

    @Test
    @Order(3)
    void updateField() {
        ApiTranslationField response = client.getFieldApi().updateField("Y_领域分组" + (++i), field);
        System.out.println(response);
        assertNotNull(response);
    }

    @Test
    @Order(4)
    void deleteField() {
        client.getFieldApi().deleteField(field);
    }
}