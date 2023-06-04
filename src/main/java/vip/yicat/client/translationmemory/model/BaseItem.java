package vip.yicat.client.translationmemory.model;


import java.io.Serializable;

public class BaseItem implements Serializable {
    private String lang;
    private String text;

    public BaseItem() {
    }

    public String getLang() {
        return this.lang;
    }

    public String getText() {
        return this.text;
    }

    public void setLang(final String lang) {
        this.lang = lang;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseItem)) {
            return false;
        } else {
            BaseItem other = (BaseItem)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$lang = this.getLang();
                Object other$lang = other.getLang();
                if (this$lang == null) {
                    if (other$lang != null) {
                        return false;
                    }
                } else if (!this$lang.equals(other$lang)) {
                    return false;
                }

                Object this$text = this.getText();
                Object other$text = other.getText();
                if (this$text == null) {
                    if (other$text != null) {
                        return false;
                    }
                } else if (!this$text.equals(other$text)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseItem;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $lang = this.getLang();
        result = result * 59 + ($lang == null ? 43 : $lang.hashCode());
        Object $text = this.getText();
        result = result * 59 + ($text == null ? 43 : $text.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "BaseItem(lang=" + this.getLang() + ", text=" + this.getText() + ")";
    }
}
