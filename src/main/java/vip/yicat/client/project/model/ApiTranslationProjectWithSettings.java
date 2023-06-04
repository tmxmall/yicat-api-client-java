package vip.yicat.client.project.model;

import vip.yicat.client.segment.model.ApiQARule;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApiTranslationProjectWithSettings extends ApiTranslationProject {
    private Integer fieldId;
    private Byte lockInnerRepeat;
    private Byte lockOuterRepeat;
    private Byte lock100MatchedRepeat;
    private Byte lock101MatchedRepeat;
    private Byte lock102MatchedRepeat;
    private Integer words;
    private Integer chars;
    private Integer ckChars;
    private List<ApiQARule> apiQARuleList;
    private List<ApiMountTranslationMemory> apiMountTranslationMemoryList;
    private List<ApiMountTermBase> apiMountTermBaseList;
    private String mtProvider;
    private Byte enabledTmPreTrans;
    private Byte enabledMtPreTrans;
    private Byte pretransConfirm;
    private Integer minMatchingRate;
    private String managerUserId;
    private String managerUserName;
    private String groupId;
    private String groupName;
    private Byte autoTagging;
    private Map<String, Map<String, Object>> fileParseSettings;
    private Integer newOff;
    private Integer tm5074Off;
    private Integer tm7584Off;
    private Integer tm8594Off;
    private Integer tm9599Off;
    private Integer tm100Off;
    private Integer tm101Off;
    private Integer tm102Off;
    private Integer innerRepeatOff;
    private Integer outerRepeatOff;
    private Integer mtOff;
    private Byte wordCountMode;
    private Byte enabledTqa;
    private String tqaPenalty;
    private Byte websiteLocalizationMode;
    private String segmentationRuleId;
    private Byte termInterference;
    private List<ApiProjectWorkflowStage> workflowStageList;
    private Byte enabledRepetitionSynchronize;
    private Byte enabledSimultaneousCollaboration;
    private Byte enabledPreTransUntranslatableElements;
    private Byte autoConfirmedUntranslatableElements;
    private Byte autoConfirmed100MatchedRepeat;
    private Byte visibilityLevel;
    private Byte enableTranslatorExport;
    private Byte enableMemberSearchTermTm;
    private List<ApiMountDictionary> apiMountDictionaryList;
    private Byte enableMtQe;
    private Integer langAssetsMatchDesensitizationLevel;
}
