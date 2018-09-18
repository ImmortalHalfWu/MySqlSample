package com.immortal.half.wu.configs;

import com.google.gson.Gson;
import com.immortal.half.wu.dao.utils.DaoUtil;

import java.io.IOException;
import java.util.Map;

public class ApplicationConfig {

    private static final String CONFIG_PROPERTIES_NAME = "ApplicationConfig.properties";
    private static final String CONFIG_PROPERTIES_PATH = "configs/" + CONFIG_PROPERTIES_NAME;

    private static ApplicationConfig applicationConfigBean;

    public static void init() throws IOException {
        if (applicationConfigBean == null) {
            synchronized (ApplicationConfig.class) {
                String json = PropertieUtil.propertieToJson(CONFIG_PROPERTIES_PATH);
                applicationConfigBean = new Gson().fromJson(json, ApplicationConfig.class);
            }
        }

    }

    public static ApplicationConfig instance() {
        return applicationConfigBean;
    }

    public void uploadToFile() {

        if (applicationConfigBean == null) {
            return;
        }
        Map<String, Object> stringObjectMap = DaoUtil.object2Map(applicationConfigBean);
        PropertieUtil.mapToPropertie(stringObjectMap, CONFIG_PROPERTIES_PATH);
    }

    private long PayKDT_ID;
    private long PayTokenEndTime;
    private String PayToken;
    private String SMSUrlHost;
    private String SMSUrlPath;
    private String PayClientSecret;
    private boolean IsDebug;
    private String SMSAppCode;
    private String PayClientId;

    public long getPayKDT_ID() {
        return PayKDT_ID;
    }

    public void setPayKDT_ID(long payKDT_ID) {
        PayKDT_ID = payKDT_ID;
    }

    public long getPayTokenEndTime() {
        return PayTokenEndTime;
    }

    public void setPayTokenEndTime(long payTokenEndTime) {
        PayTokenEndTime = payTokenEndTime;
    }

    public String getPayToken() {
        return PayToken;
    }

    public void setPayToken(String payToken) {
        PayToken = payToken;
    }

    public String getSMSUrlHost() {
        return SMSUrlHost;
    }

    public void setSMSUrlHost(String SMSUrlHost) {
        this.SMSUrlHost = SMSUrlHost;
    }

    public String getSMSUrlPath() {
        return SMSUrlPath;
    }

    public void setSMSUrlPath(String SMSUrlPath) {
        this.SMSUrlPath = SMSUrlPath;
    }

    public String getPayClientSecret() {
        return PayClientSecret;
    }

    public void setPayClientSecret(String payClientSecret) {
        PayClientSecret = payClientSecret;
    }

    public boolean isDebug() {
        return IsDebug;
    }

    public void setDebug(boolean debug) {
        IsDebug = debug;
    }

    public String getSMSAppCode() {
        return SMSAppCode;
    }

    public void setSMSAppCode(String SMSAppCode) {
        this.SMSAppCode = SMSAppCode;
    }

    public String getPayClientId() {
        return PayClientId;
    }

    public void setPayClientId(String payClientId) {
        PayClientId = payClientId;
    }



}
