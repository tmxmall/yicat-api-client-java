package vip.yicat.client.translationmemory.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApiYiCATTMEntryInfo {

    private String entryId;

    private String tmId;

    private List<ApiBaseTMItem> itemList;

    private Date createTime;

    private Date modifyTime;

}
