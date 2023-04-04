package com.yicat.client.project.model;

import lombok.Data;


@Data
public class PatchProjectAccessControlSettingsRequest {
    private Byte enableTranslatorExport;
    private Byte enableMemberSearchTermTm;
    private Integer langAssetsMatchDesensitizationLevel;
}
