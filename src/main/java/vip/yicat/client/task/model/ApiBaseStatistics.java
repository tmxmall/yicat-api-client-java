package vip.yicat.client.task.model;

import lombok.Data;

@Data
public class ApiBaseStatistics {
    private float percent;
    private int segments;
    private int chars;
    private int words;
    private int wordsWithSymbols;
    private int ckChars;
    private int spaces;
    private int symbols;
    private int digitals;
    private int numbers;
}

