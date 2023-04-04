package com.yicat.client.task.model;

import java.io.Serializable;

public class BaseStatistics implements Serializable {
    private float percent = 0.0F;
    private int segments = 0;
    private int chars = 0;
    private int words = 0;
    private int wordsWithSymbols;
    private int ckChars = 0;
    private int spaces = 0;
    private int symbols = 0;
    private int digitals = 0;
    private int numbers = 0;

    public BaseStatistics() {
    }

    public float getPercent() {
        return this.percent;
    }

    public int getSegments() {
        return this.segments;
    }

    public int getChars() {
        return this.chars;
    }

    public int getWords() {
        return this.words;
    }

    public int getWordsWithSymbols() {
        return this.wordsWithSymbols;
    }

    public int getCkChars() {
        return this.ckChars;
    }

    public int getSpaces() {
        return this.spaces;
    }

    public int getSymbols() {
        return this.symbols;
    }

    public int getDigitals() {
        return this.digitals;
    }

    public int getNumbers() {
        return this.numbers;
    }

    public void setPercent(final float percent) {
        this.percent = percent;
    }

    public void setSegments(final int segments) {
        this.segments = segments;
    }

    public void setChars(final int chars) {
        this.chars = chars;
    }

    public void setWords(final int words) {
        this.words = words;
    }

    public void setWordsWithSymbols(final int wordsWithSymbols) {
        this.wordsWithSymbols = wordsWithSymbols;
    }

    public void setCkChars(final int ckChars) {
        this.ckChars = ckChars;
    }

    public void setSpaces(final int spaces) {
        this.spaces = spaces;
    }

    public void setSymbols(final int symbols) {
        this.symbols = symbols;
    }

    public void setDigitals(final int digitals) {
        this.digitals = digitals;
    }

    public void setNumbers(final int numbers) {
        this.numbers = numbers;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseStatistics)) {
            return false;
        } else {
            BaseStatistics other = (BaseStatistics)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (Float.compare(this.getPercent(), other.getPercent()) != 0) {
                return false;
            } else if (this.getSegments() != other.getSegments()) {
                return false;
            } else if (this.getChars() != other.getChars()) {
                return false;
            } else if (this.getWords() != other.getWords()) {
                return false;
            } else if (this.getWordsWithSymbols() != other.getWordsWithSymbols()) {
                return false;
            } else if (this.getCkChars() != other.getCkChars()) {
                return false;
            } else if (this.getSpaces() != other.getSpaces()) {
                return false;
            } else if (this.getSymbols() != other.getSymbols()) {
                return false;
            } else if (this.getDigitals() != other.getDigitals()) {
                return false;
            } else {
                return this.getNumbers() == other.getNumbers();
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseStatistics;
    }

    @Override
    public int hashCode() {
        Boolean PRIME = true;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.getPercent());
        result = result * 59 + this.getSegments();
        result = result * 59 + this.getChars();
        result = result * 59 + this.getWords();
        result = result * 59 + this.getWordsWithSymbols();
        result = result * 59 + this.getCkChars();
        result = result * 59 + this.getSpaces();
        result = result * 59 + this.getSymbols();
        result = result * 59 + this.getDigitals();
        result = result * 59 + this.getNumbers();
        return result;
    }

    @Override
    public String toString() {
        return "BaseStatistics(percent=" + this.getPercent() + ", segments=" + this.getSegments() + ", chars=" + this.getChars() + ", words=" + this.getWords() + ", wordsWithSymbols=" + this.getWordsWithSymbols() + ", ckChars=" + this.getCkChars() + ", spaces=" + this.getSpaces() + ", symbols=" + this.getSymbols() + ", digitals=" + this.getDigitals() + ", numbers=" + this.getNumbers() + ")";
    }
}

