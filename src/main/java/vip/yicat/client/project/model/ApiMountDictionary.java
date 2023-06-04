package vip.yicat.client.project.model;
import lombok.Data;

import java.util.Date;

@Data
public class ApiMountDictionary {
    private String dictionaryId;
    private Byte dictionaryUpdateEnabled;
    private Date createTime;
    private Date modifyTime;
}
