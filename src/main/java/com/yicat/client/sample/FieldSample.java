package com.yicat.client.sample;

import com.yicat.client.Client;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.field.model.ApiTranslationField;

import java.util.List;

/**
 * 领域分组
 */
public class FieldSample {
    static Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    static Client client = new Client(credentials);

    public static void main(String[] args) {
        //创建一个领域分组
        ApiTranslationField apiTranslationField = client.getFieldApi().createField("领域分组1");
        System.out.println("创建一个领域分组:" + apiTranslationField);
        //查询所有的领域分组
        List<ApiTranslationField> apiTranslationFields = client.getFieldApi().getField();
        System.out.println("查询所有的领域分组:" + apiTranslationFields);
        //修改领域分组
        ApiTranslationField updateField = client.getFieldApi().updateField("科技", apiTranslationField.getFieldId().toString());
        System.out.println("修改领域分组:" + updateField);
        //删除领域分组
        client.getFieldApi().deleteField(apiTranslationField.getFieldId().toString());
        System.out.println("删除领域分组:" + apiTranslationField.getFieldId());
    }
}
