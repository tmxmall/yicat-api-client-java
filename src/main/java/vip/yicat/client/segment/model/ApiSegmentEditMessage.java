package vip.yicat.client.segment.model;

import lombok.Data;

import java.util.List;

/**
 * Segment edit submit information
 */
@Data
public class ApiSegmentEditMessage {
    /**
     * Operation type
     * - insert: Insert segment
     * - delete: Delete segment
     * - update: Update segment
     * - validateQA: Execute QA
     */
    private String opType;
    /**
     * Operation segment content
     */
    private ApiSegment opSegment;
    private String srcLan;
    private String tgtLan;
    private List<ApiQARule> apiQARuleList;
    private Integer stage;
    /**
     * Whether source text changed
     */
    private boolean srcChanged;
    /**
     * Edit save type
     * - 0: Segment modify by translator
     * - 1: Segment content come from translation memory
     * - 3: Segment content come from machine translation
     * - 4: Segment content come from stage revert operation
     * - 5: Segment content come from stage forward operation
     * - 6: Segment content come from stage deliver operation
     */
    private Integer editSaveType;
}
