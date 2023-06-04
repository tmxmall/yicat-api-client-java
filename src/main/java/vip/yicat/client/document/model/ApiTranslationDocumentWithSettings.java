package vip.yicat.client.document.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Upload document request
 */
@Data
public class ApiTranslationDocumentWithSettings {
    /**
     * Document id
     */
    private String documentId;
    /**
     * Original file id
     */
    private String originalFileId;
    /**
     * Document name
     */
    private String documentName;
    /**
     * File type
     */
    private String fileType;
    /**
     * File size
     */
    private Integer fileSize;
    /**
     * When uploading compressed file(zip, rar), file's parents' path
     */
    private String parentPath;
    private String projectId;
    private String projectName;
    private String groupId;
    private String groupName;
    private Integer groupType;
    /**
     * Document words
     */
    private Integer words;
    /**
     * Document chars
     */
    private Integer chars;
    /**
     * Document Asian characters and Korean words number
     */
    private Integer ckChars;
    /**
     * Document space number
     */
    private Integer spaces;
    /**
     * Document digital number
     */
    private Integer digitals;
    /**
     * Document symbols number
     */
    private Integer symbols;
    private Integer numbers;
    /**
     * Document segment number
     */
    private Integer segments;
    private String srcLan;
    private String tgtLan;
    private String notes;
    /**
     * Extend from project settings
     */
    private Byte lockInnerRepeat;
    /**
     * Extend from project settings
     */
    private Byte lockOuterRepeat;
    /**
     * Extend from project settings
     */
    private Byte lock100MatchedRepeat;
    /**
     * Extend from project settings
     */
    private Byte lock101MatchedRepeat;
    /**
     * Extend from project settings
     */
    private Byte lock102MatchedRepeat;
    /**
     * Extend from project settings
     */
    private List<String> qaRuleList;
    /**
     * MT provider
     */
    private String mtProvider;
    /**
     * Extend from project settings
     */
    private Byte enabledTmPreTrans;
    /**
     * Extend from project settings
     */
    private Byte enabledMtPreTrans;
    /**
     * Extend from project settings
     */
    private Byte pretransConfirm;
    /**
     * Extend from project settings
     */
    private Integer minMatchingRate;
    private String managerUserId;
    private String managerUserName;
    /**
     * Document translation progress
     */
    private Integer totalProgress;
    private Date deadline;
    /**
     * Document status
     */
    private Integer status;
    /**
     * Extend from project settings
     */
    private Byte autoTagging;
    /**
     * Extend from project settings
     */
    private Byte wordCountMode;
    /**
     * Extend from project settings
     */
    private Byte enabledTqa;
    /**
     * Extend from project settings
     */
    private String tqaPenalty;
    /**
     * Pretrans status 0: closed; 1: open 2: in progress
     */
    private Byte preTransStatus;
    /**
     * Document preview status 0: not ready; 1: ready
     */
    private Integer previewReadyStatus;
    /**
     * Extend from project settings
     */
    private String segmentationRuleId;
    /**
     * Extend from project settings
     */
    private Byte termInterference;
    /**
     * Document workflow stage
     */
    private List<ApiDocumentWorkflowStage> apiDocumentWorkflowStageList;
    /**
     * Extend from project settings
     */
    private Byte enabledRepetitionSynchronize;
    /**
     * Extend from project settings
     */
    private Byte enabledSimultaneousCollaboration;
    private Map<String, Map<String, Object>> fileParseSettings;
    /**
     * Extend from project settings
     */
    private Byte enabledPreTransUntranslatableElements;
    /**
     * Extend from project settings
     */
    private Byte autoConfirmedUntranslatableElements;
    /**
     * Extend from project settings
     */
    private Byte autoConfirmed100MatchedRepeat;
    /**
     * File Parse status 0: doing; 1: success; 2: fail
     */
    private Byte analyzeStatus;
    /**
     * File Parse result
     */
    private String analyzeResultInfo;
    private Date createTime;
    private Date modifyTime;
    private Integer mtWords;
    private Integer htWords;
    private Integer tm100MatchedWords;
    private Integer innerRepeatWords;
    private Integer outerRepeatWords;
    /**
     * Extend from project settings
     */
    private Byte enableMtQe;
}