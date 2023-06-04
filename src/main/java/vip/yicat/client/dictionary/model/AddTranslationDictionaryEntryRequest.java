package vip.yicat.client.dictionary.model;

import lombok.Data;

@Data
public class AddTranslationDictionaryEntryRequest {
    private String entryName;
    private String reference;
    private String note;
}
