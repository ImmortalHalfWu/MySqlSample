package com.immortal.half.wu.bean;

import com.immortal.half.wu.bean.enums.DOCUMENTARY_TYPE;
import com.immortal.half.wu.bean.enums.ORDER_TYPE;

public class DocumentaryInfoBean extends ScanInfoBean {

    private Integer positionRatio;
    private transient DOCUMENTARY_TYPE documentaryTypeEnum;
    private Integer documentaryType;

    public DocumentaryInfoBean(
            Integer id,
            Integer userId,
            String scanUrl,
            String tagName,
            ORDER_TYPE orderType,
            Integer frequency,
            Boolean canUser,
            Integer positionRatio,
            DOCUMENTARY_TYPE documentaryType) {
        super(id, userId, scanUrl, tagName, orderType, frequency, canUser);
        this.positionRatio = positionRatio;
        this.documentaryTypeEnum = documentaryType;
        this.documentaryType = documentaryType.getCode();
    }

    public Integer getPositionRatio() {
        return positionRatio;
    }

    public DOCUMENTARY_TYPE getDocumentaryTypeEnum() {
        return documentaryTypeEnum;
    }

    public Integer getDocumentaryType() {
        return documentaryType;
    }
}
