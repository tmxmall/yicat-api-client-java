package vip.yicat.client.translationmemory.model;

import java.io.Serializable;

public class LangInfo implements Serializable {
    private String lang;
    private int count;

    public LangInfo(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return this.lang;
    }

    public int getCount() {
        return this.count;
    }

    public void setLang(final String lang) {
        this.lang = lang;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof LangInfo)) {
            return false;
        } else {
            LangInfo other = (LangInfo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$lang = this.getLang();
                Object other$lang = other.getLang();
                if (this$lang == null) {
                    if (other$lang == null) {
                        return this.getCount() == other.getCount();
                    }
                } else if (this$lang.equals(other$lang)) {
                    return this.getCount() == other.getCount();
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LangInfo;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $lang = this.getLang();
        result = result * 59 + ($lang == null ? 43 : $lang.hashCode());
        result = result * 59 + this.getCount();
        return result;
    }

    @Override
    public String toString() {
        return "LangInfo(lang=" + this.getLang() + ", count=" + this.getCount() + ")";
    }

    public LangInfo() {
    }

    public LangInfo(final String lang, final int count) {
        this.lang = lang;
        this.count = count;
    }
}
