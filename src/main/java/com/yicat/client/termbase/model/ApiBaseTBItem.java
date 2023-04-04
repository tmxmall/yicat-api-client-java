package com.yicat.client.termbase.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiBaseTBItem extends ApiAbstractBaseItem implements Serializable {

    private byte status;

    private boolean preferred;

    private boolean caseSensitive;

    private boolean forbidden;

    private Integer termType;

    private Integer partOfSpeech;

    private String source;

    private String note;

    private String description;
}
