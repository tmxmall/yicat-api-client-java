package com.yicat.client.core.model;

import lombok.Data;

@Data
public class PatchRequest {
    private Object value;
    private String path;
    private PatchOperation op;
}
