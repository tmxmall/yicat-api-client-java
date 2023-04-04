package com.yicat.client.segment.model;

import lombok.Data;

@Data
public class ApiQALog  {
	private long executeTime = 0;
    private Integer ruleId;
    private String ruleDescription;
    private Integer ruleLevel;
    private String ruleLog;
    private String ruleLogEn;
}
