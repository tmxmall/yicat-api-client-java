package vip.yicat.client.document.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ExportDocumentRequest implements Serializable {
    private List<String> documentIdList;
    private List<Integer> stageList;
    private List<Integer> downloadTypeList;
}
