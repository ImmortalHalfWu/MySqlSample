package com.immortal.half.wu.utils;

import com.google.gson.JsonObject;
import com.immortal.half.wu.configs.ApplicationConfig;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.client.oauth.OAuth;
import com.youzan.open.sdk.client.oauth.OAuthContext;
import com.youzan.open.sdk.client.oauth.OAuthFactory;
import com.youzan.open.sdk.client.oauth.OAuthType;
import com.youzan.open.sdk.client.oauth.model.OAuthToken;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanPayQrcodeCreate;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanPayQrcodeCreateParams;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanPayQrcodeCreateResult;
import com.youzan.open.sdk.util.json.JsonUtils;

public class PayUtil {

    private static PayUtil payUtil;
//    private String CLIENT_ID = "afcd2eb5b88fbdbd90";
//    private String CLIENT_SECRET = "6f2d78752dd8aaded87ae9e3b027c058";
//    private long KDT_ID = 41461397;
//    // 0917-0924
//    private String payToken = "9de830f1a71835c4950871a84e0c9d20";
//    private long endTime;
    private YZClient yzClient;

    private ApplicationConfig applicationConfig;

    private PayUtil(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
        initToken();
        initClient();
    }

    public static void init(ApplicationConfig applicationConfig) {
        if (payUtil == null) {
            synchronized (PayUtil.class) {
                payUtil = new PayUtil(
                    applicationConfig
                );
            }
        }
    }

    public static PayUtil getInstance() {
        return payUtil;
    }

    private void initToken() {

        // 若找到token， 并有效期内，则直接返回
        if (applicationConfig.getPayToken() != null
                &&  applicationConfig.getPayTokenEndTime() > DataUtil.getDelayTimeForDay(1)) {
            return;
        }

        // 刷新token
        OAuth authorizationCode = OAuthFactory.create(
                OAuthType.SELF,
                new OAuthContext(
                        applicationConfig.getPayClientId(),
                        applicationConfig.getPayClientSecret(),
                        applicationConfig.getPayKDT_ID()
                )
        );

        OAuthToken token = authorizationCode.getToken();

        applicationConfig.setPayToken(token.getAccessToken());
        applicationConfig.setPayTokenEndTime(token.getExpiresIn() * 1000 + DataUtil.getNowTimeToLong());
        applicationConfig.uploadToFile();

    }

    private void initClient() {
        yzClient = new DefaultYZClient(new Token(applicationConfig.getPayToken()));
    }

    public String payTest() {

        YouzanPayQrcodeCreate youzanPayQrcodeCreate = new YouzanPayQrcodeCreate();
        YouzanPayQrcodeCreateParams youzanPayQrcodeCreateParams = new YouzanPayQrcodeCreateParams();

        youzanPayQrcodeCreateParams.setQrPrice("1");
        //二维码类型. QR_TYPE_FIXED_BY_PERSON ：无金额二维码，扫码后用户需自己输入金额； QR_TYPE_NOLIMIT ： 确定金额二维码，可以重复支付; QR_TYPE_DYNAMIC：确定金额二维码，只能被支付一次

//        youzanPayQrcodeCreateParams.setQrName("可重复使用。09211029");
//        youzanPayQrcodeCreateParams.setQrType("QR_TYPE_NOLIMIT");

        youzanPayQrcodeCreateParams.setQrType("QR_TYPE_DYNAMIC");
        // 备注长度不能大于60，  手机+会员类型+金额+base64
        youzanPayQrcodeCreateParams.setQrName("一次性支付。09211029eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMzYxMzU3MTMzMSIsInBob25lIjoiMTM2MTM1NzEzMzEiLCJtSXNzdWVyIjoiaW1tb3J0YWxIYWxmV3UxNTM3MzQ3MTE4NjM5IiwiaXNzIjoiaW1tb3J0YWxIYWxmV3UxNTM3MzQ3MTE4NjM5IiwiZXhwIjoxODUyMjc0NjY2LCJ1c2VySWQiOjI0fQ.cWD1IknA6HX_u-3USw1xVtys4Nr3DqgN8Q17UdKSD1U");


        youzanPayQrcodeCreate.setAPIParams(youzanPayQrcodeCreateParams);

        YouzanPayQrcodeCreateResult result = yzClient.invoke(youzanPayQrcodeCreate);
        String s = JsonUtils.toJson(result);
        System.out.println(s);

        return s;
    }

}
