package vip.yicat.client.termbase.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApiMatchTBEntryInfo implements Serializable {

    private String entryId;

    private String tbId;

    private String tbName;

    private String markText;

    private String srcText;

    private String tgtText;

    private ApiBaseTBItem srcTBItem;

    private ApiBaseTBItem targetTBItem;

    private Date time;
}
