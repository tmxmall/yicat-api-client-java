package vip.yicat.client.termbase.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ApiYiCATTBEntryInfo implements Serializable {

    private String entryId;

    private String tbId;

    private List<ApiBaseTBItem> itemList;

    private Date createTime;

    private Date modifyTime;

}
