package project.module.dict.domain.po;

import java.util.Date;

public class CoreDictPo {
    private Integer id;

    private String dictValue;

    private String dictName;

    private String dictType;

    private String typeName;

    private Integer dictSort;

    private Integer dictParent;

    private Byte dictStatus;

    private String dictRemark;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    public Integer getDictParent() {
        return dictParent;
    }

    public void setDictParent(Integer dictParent) {
        this.dictParent = dictParent;
    }

    public Byte getDictStatus() {
        return dictStatus;
    }

    public void setDictStatus(Byte dictStatus) {
        this.dictStatus = dictStatus;
    }

    public String getDictRemark() {
        return dictRemark;
    }

    public void setDictRemark(String dictRemark) {
        this.dictRemark = dictRemark == null ? null : dictRemark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}