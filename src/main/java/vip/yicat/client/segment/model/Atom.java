package vip.yicat.client.segment.model;

import java.io.Serializable;

public class Atom implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private String atomId;
    private String data;
    private String textStyle;
    private boolean isHidden = false;
    private String tagType;
    private String tagId;
    private boolean isCustom = false;
    private Integer reviseType = 0;
    private String styleContent;

    public Atom() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getAtomId() {
        return this.atomId;
    }

    public String getData() {
        return this.data;
    }

    public String getTextStyle() {
        return this.textStyle;
    }

    public boolean isHidden() {
        return this.isHidden;
    }

    public String getTagType() {
        return this.tagType;
    }

    public String getTagId() {
        return this.tagId;
    }

    public boolean isCustom() {
        return this.isCustom;
    }

    public Integer getReviseType() {
        return this.reviseType;
    }

    public String getStyleContent() {
        return this.styleContent;
    }

    public void setAtomId(final String atomId) {
        this.atomId = atomId;
    }

    public void setData(final String data) {
        this.data = data;
    }

    public void setTextStyle(final String textStyle) {
        this.textStyle = textStyle;
    }

    public void setHidden(final boolean isHidden) {
        this.isHidden = isHidden;
    }

    public void setTagType(final String tagType) {
        this.tagType = tagType;
    }

    public void setTagId(final String tagId) {
        this.tagId = tagId;
    }

    public void setCustom(final boolean isCustom) {
        this.isCustom = isCustom;
    }

    public void setReviseType(final Integer reviseType) {
        this.reviseType = reviseType;
    }

    public void setStyleContent(final String styleContent) {
        this.styleContent = styleContent;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Atom)) {
            return false;
        } else {
            Atom other = (Atom)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label103: {
                    Object this$atomId = this.getAtomId();
                    Object other$atomId = other.getAtomId();
                    if (this$atomId == null) {
                        if (other$atomId == null) {
                            break label103;
                        }
                    } else if (this$atomId.equals(other$atomId)) {
                        break label103;
                    }

                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                label89: {
                    Object this$textStyle = this.getTextStyle();
                    Object other$textStyle = other.getTextStyle();
                    if (this$textStyle == null) {
                        if (other$textStyle == null) {
                            break label89;
                        }
                    } else if (this$textStyle.equals(other$textStyle)) {
                        break label89;
                    }

                    return false;
                }

                if (this.isHidden() != other.isHidden()) {
                    return false;
                } else {
                    label81: {
                        Object this$tagType = this.getTagType();
                        Object other$tagType = other.getTagType();
                        if (this$tagType == null) {
                            if (other$tagType == null) {
                                break label81;
                            }
                        } else if (this$tagType.equals(other$tagType)) {
                            break label81;
                        }

                        return false;
                    }

                    Object this$tagId = this.getTagId();
                    Object other$tagId = other.getTagId();
                    if (this$tagId == null) {
                        if (other$tagId != null) {
                            return false;
                        }
                    } else if (!this$tagId.equals(other$tagId)) {
                        return false;
                    }

                    if (this.isCustom() != other.isCustom()) {
                        return false;
                    } else {
                        Object this$reviseType = this.getReviseType();
                        Object other$reviseType = other.getReviseType();
                        if (this$reviseType == null) {
                            if (other$reviseType != null) {
                                return false;
                            }
                        } else if (!this$reviseType.equals(other$reviseType)) {
                            return false;
                        }

                        Object this$styleContent = this.getStyleContent();
                        Object other$styleContent = other.getStyleContent();
                        if (this$styleContent == null) {
                            if (other$styleContent != null) {
                                return false;
                            }
                        } else if (!this$styleContent.equals(other$styleContent)) {
                            return false;
                        }

                        return true;
                    }
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Atom;
    }

    @Override
    public int hashCode() {
        Boolean PRIME = true;
        int result = 1;
        Object $atomId = this.getAtomId();
        result = result * 59 + ($atomId == null ? 43 : $atomId.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        Object $textStyle = this.getTextStyle();
        result = result * 59 + ($textStyle == null ? 43 : $textStyle.hashCode());
        result = result * 59 + (this.isHidden() ? 79 : 97);
        Object $tagType = this.getTagType();
        result = result * 59 + ($tagType == null ? 43 : $tagType.hashCode());
        Object $tagId = this.getTagId();
        result = result * 59 + ($tagId == null ? 43 : $tagId.hashCode());
        result = result * 59 + (this.isCustom() ? 79 : 97);
        Object $reviseType = this.getReviseType();
        result = result * 59 + ($reviseType == null ? 43 : $reviseType.hashCode());
        Object $styleContent = this.getStyleContent();
        result = result * 59 + ($styleContent == null ? 43 : $styleContent.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Atom(atomId=" + this.getAtomId() + ", data=" + this.getData() + ", textStyle=" + this.getTextStyle() + ", isHidden=" + this.isHidden() + ", tagType=" + this.getTagType() + ", tagId=" + this.getTagId() + ", isCustom=" + this.isCustom() + ", reviseType=" + this.getReviseType() + ", styleContent=" + this.getStyleContent() + ")";
    }
}
