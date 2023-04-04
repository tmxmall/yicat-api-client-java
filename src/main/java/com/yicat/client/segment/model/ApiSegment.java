package com.yicat.client.segment.model;


import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Segment information
 */
@Data
public class ApiSegment {
    /**
     * Segment ID
     */
    private String _id;
    private String documentId;
    /**
     * Translation unit id(Paragraph ID)
     */
    private String translationUnitId;
    /**
     * Pretranslation source
     * - 0: no;
     * - 1: public translation memory (Deprecated);
     * - 2: private translation memory;
     * - 3: translation memory store(Deprecated);
     * - 4: TM ROBOT(Deprecated);
     * - 5: Machine translation
     */
    private int preTransSource;
    /**
     * Translation memory match rate
     */
    private int matchingRate;
    /**
     * Whether auto translation result has been edit
     * - 1. yes
     * - 2. no
     */
    private boolean isAutoTransResultEdit;
    /**
     * Machine translation result
     */
    private String autoTransResult;
    /**
     * Repeat lock status
     * - 0. not locked
     * - 1. inner repeat locked
     * - 2. outer repeat locked
     */
    private int lockStatus;
    /**
     * Translation memroy lock status
     * - 0. not locked
     * - 1. 100% repeat locked
     * - 2. 101% repeat locked
     * - 3. 102% repeat locked
     */
    private int tmLockStatus;
    private String mtProvider;
    private List<ApiQALog> hitQAInfo;
    private List<ApiQALog> ignoreQAInfo;
    private String repeatSegmentId;
    private int words;
    private int chars;
    private int ckChars;
    private int spaces;
    private int digitals;
    private int numbers;
    private int symbols;
    private String lastModifyIp;
    /**
     * Segment sequence number, start from 1
     */
    private float seqNum;
    private String notes;
    private List<ApiAtom> srcSegmentAtoms;
    /**
     * Original source segment atoms
     */
    private List<ApiAtom> oriSrcSegmentAtoms;
    private List<ApiAtom> tgtSegmentAtoms;
    /**
     * Whether contains TQA record
     */
    private boolean isTQARecord;
    private String srcMD5;
    /**
     * Synchronize status
     */
    private boolean synchronize;
    private boolean hasRepeatSegment;
    private int forceLock;
    private String repeatDocumentId;
    private Date modifyTime;
    private int stage;
    private boolean hasConfirmed;
    private boolean hasDelivered;
    private int revertStage;
    private List<ApiSegmentStageRecord> segmentStageRecordList;
    private Integer mtqeLevel;
    private Integer mtqeTime;
    private Integer mtqeDistance;
}
