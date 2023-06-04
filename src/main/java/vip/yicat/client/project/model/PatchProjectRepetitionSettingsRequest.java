package vip.yicat.client.project.model;

import lombok.Data;

@Data
public class PatchProjectRepetitionSettingsRequest {
    private Byte lockInnerRepeat;
    private Byte lockOuterRepeat;
    private Byte lock100MatchedRepeat;
    private Byte lock101MatchedRepeat;
    private Byte lock102MatchedRepeat;
    private Byte enabledRepetitionSynchronize;
}
