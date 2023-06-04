package vip.yicat.client.project.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AddTranslationProjectRequest {
    private String projectName;
    private String srcLan;
    private List<String> tgtLanList;
    /**
     * defaultValue ="Create by open api"
     */
    private String notes;
    private Date deadline;
    private String workflowTemplateId;
    /**
     * Whether to enable Simultaneous Collaboration
     */
    private Byte enabledSimultaneousCollaboration;
    /**
     * - 1: File based project
     * - 2: String based project
     * defaultValue = "1"
     */
    private Integer projectType;
}
