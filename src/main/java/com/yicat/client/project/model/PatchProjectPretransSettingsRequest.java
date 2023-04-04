package com.yicat.client.project.model;

import lombok.Data;

@Data
public class PatchProjectPretransSettingsRequest {
    private Byte enabledTmPreTrans;
    private Byte enabledMtPreTrans;
    private Byte pretransConfirm;
    private Integer minMatchingRate;
    private Byte autoTagging;
    private Byte termInterference;
    private Byte autoConfirmedUntranslatableElements;
    private Byte autoConfirmed100MatchedRepeat;
}
