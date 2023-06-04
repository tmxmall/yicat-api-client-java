package vip.yicat.client.translationmemory.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
public class ApiMatchTMEntryInfo implements Serializable {

    private String entryId;

    private String tmId;

    private String tmName;

    private double bleu;

    private String markText;

    private String srcText;

    private String tgtText;

    private ApiBaseTMItem srcTMItem;

    private ApiBaseTMItem targetTMItem;

    private Date time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiMatchTMEntryInfo that = (ApiMatchTMEntryInfo) o;
        return Objects.equals(srcText, that.srcText) && Objects.equals(tgtText, that.tgtText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(srcText, tgtText);
    }
}
