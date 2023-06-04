package vip.yicat.client.translationmemory.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiBaseItem implements Serializable {

    private String lang;

    private String text;
}
