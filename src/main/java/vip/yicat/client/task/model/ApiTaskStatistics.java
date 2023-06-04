package vip.yicat.client.task.model;

import lombok.Data;

import java.util.Date;

@Data
public class ApiTaskStatistics {
    private String groupId;
    private String taskId;
    private Date countTime;
    private BaseStatistics newStatistics;
    private BaseStatistics tm5074Statistics;
    private BaseStatistics tm7584Statistics;
    private BaseStatistics tm8594Statistics;
    private BaseStatistics tm9599Statistics;
    private BaseStatistics tm100Statistics;
    private BaseStatistics tm101Statistics;
    private BaseStatistics tm102Statistics;
    private BaseStatistics innerRepeatStatistics;
    private BaseStatistics outerRepeatStatistics;
    private BaseStatistics mtStatistics;
    private BaseStatistics totalStatistics;
    private BaseStatistics transNewStatistics;
    private BaseStatistics transTm5074Statistics;
    private BaseStatistics transTm7584Statistics;
    private BaseStatistics transTm8594Statistics;
    private BaseStatistics transTm9599Statistics;
    private BaseStatistics transTm100Statistics;
    private BaseStatistics transTm101Statistics;
    private BaseStatistics transTm102Statistics;
    private BaseStatistics transInnerRepeatStatistics;
    private BaseStatistics transOuterRepeatStatistics;
    private BaseStatistics transMtStatistics;
    private BaseStatistics transTotalStatistics;
}
