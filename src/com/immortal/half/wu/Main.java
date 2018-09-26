package com.immortal.half.wu;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.immortal.half.wu.bean.*;
import com.immortal.half.wu.bean.enums.*;
import com.immortal.half.wu.configs.ApplicationConfig;
import com.immortal.half.wu.dao.DaoManager;
import com.immortal.half.wu.utils.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Main {


    static {
        try {
            ApplicationConfig.init();
            PayUtil.init(ApplicationConfig.instance());
            DaoManager.init();
            LogUtil.init();
//            WorkPoolUtil.init();
//            String driverClass = "com.mysql.cj.jdbc.Driver";
//            Class.forName(driverClass);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        /**
         * 可重复使用。09211029
         * {"qr_url":"https://trade.koudaitong.com/wxpay/confirmQr?qr_id=8352149&kdt_id=41461397","qr_code":"data:image/png;base64,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","qr_id":8352149}
         *
         *  一次性支付。09211029eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMzYxMzU3MTMzMSIsInBob25lIjoiMTM2MTM1NzEzMzEiLCJtSXNzdWVyIjoiaW1tb3J0YWxIYWxmV3UxNTM3MzQ3MTE4NjM5IiwiaXNzIjoiaW1tb3J0YWxIYWxmV3UxNTM3MzQ3MTE4NjM5IiwiZXhwIjoxODUyMjc0NjY2LCJ1c2VySWQiOjI0fQ.cWD1IknA6HX_u-3USw1xVtys4Nr3DqgN8Q17UdKSD1U
         *  {"qr_url":"https://trade.koudaitong.com/wxpay/confirmQr?qr_id=8352218&kdt_id=41461397","qr_code":"data:image/png;base64,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","qr_id":8352218}
         *  支付回调数据
         *  {"client_id":"afcd2eb5b88fbdbd90","id":"E20180921104849022600019","kdt_id":41461397,"kdt_name":"????????¤?????????","mode":1,"msg":"%7B%22order_promotion%22:%7B%22adjust_fee%22:%220.00%22%7D,%22qr_info%22:%7B%22qr_id%22:8352218,%22qr_pay_id%22:23106071,%22qr_name%22:%22%E4%B8%80%E6%AC%A1%E6%80%A7%E6%94%AF%E4%BB%98%E3%80%8209211029eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMzYxMzU3MTMzM%22%7D,%22refund_order%22:[],%22full_order_info%22:%7B%22address_info%22:%7B%22self_fetch_info%22:%22%22,%22delivery_address%22:%22%22,%22delivery_postal_code%22:%22%22,%22receiver_name%22:%22%22,%22delivery_province%22:%22%22,%22delivery_city%22:%22%22,%22delivery_district%22:%22%22,%22address_extra%22:%22%7B%7D%22,%22receiver_tel%22:%22%22%7D,%22remark_info%22:%7B%22buyer_message%22:%22%22%7D,%22pay_info%22:%7B%22outer_transactions%22:[%224200000188201809210763674972%22],%22post_fee%22:%220.00%22,%22total_fee%22:%220.01%22,%22payment%22:%220.01%22,%22transaction%22:[%22180921104852000048%22]%7D,%22buyer_info%22:%7B%22fans_type%22:9,%22buyer_id%22:290195682,%22fans_id%22:1950113670,%22fans_nickname%22:%22%22%7D,%22orders%22:[%7B%22outer_sku_id%22:%22%22,%22sku_unique_code%22:%22%22,%22goods_url%22:%22https://h5.youzan.com/v2/showcase/goods%3Falias=null%22,%22item_id%22:2147483647,%22outer_item_id%22:%22null%22,%22discount_price%22:%220.01%22,%22item_type%22:30,%22num%22:1,%22sku_id%22:0,%22sku_properties_name%22:%22%22,%22pic_path%22:%22https://img.yzcdn.cn/public_files/2016/12/29/33e6c838cefa614c5121c63c80f860e9.png%22,%22oid%22:%221472880524427013865%22,%22title%22:%22%E4%B8%80%E6%AC%A1%E6%80%A7%E6%94%AF%E4%BB%98%E3%80%8209211029eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMzYxMzU3MTMzM%22,%22buyer_messages%22:%22%22,%22is_present%22:false,%22pre_sale_type%22:%22null%22,%22points_price%22:%220%22,%22price%22:%220.01%22,%22total_fee%22:%220.01%22,%22alias%22:%22null%22,%22payment%22:%220.01%22,%22is_pre_sale%22:%22null%22%7D],%22source_info%22:%7B%22is_offline_order%22:false,%22book_key%22:%22null%22,%22source%22:%7B%22platform%22:%22wx%22,%22wx_entrance%22:%22direct_buy%22%7D%7D,%22order_info%22:%7B%22consign_time%22:%22%22,%22order_extra%22:%7B%22is_from_cart%22:%22false%22%7D,%22created%22:%222018-09-21%2010:48:49%22,%22status_str%22:%22%E5%B7%B2%E5%AE%8C%E6%88%90%22,%22expired_time%22:%222018-09-21%2011:18:49%22,%22success_time%22:%222018-09-21%2010:49:00%22,%22type%22:6,%22tid%22:%22E20180921104849022600019%22,%22confirm_time%22:%22%22,%22pay_time%22:%222018-09-21%2010:48:59%22,%22update_time%22:%222018-09-21%2010:49:00%22,%22pay_type_str%22:%22WEIXIN_DAIXIAO%22,%22is_retail_order%22:false,%22pay_type%22:10,%22team_type%22:1,%22refund_state%22:0,%22close_type%22:0,%22status%22:%22TRADE_SUCCESS%22,%22express_type%22:9,%22order_tags%22:%7B%22is_payed%22:true,%22is_secured_transactions%22:true%7D%7D%7D%7D","msg_id":"a0b49a93-d70a-4c55-b38a-ad84a7958388","sendCount":0,"sign":"93238e1bb6f893f77614b05b6edfef82","status":"PAID","test":false,"type":"trade_TradePaid","version":1537498140}'}
         *
         *  一次性支付。19211054   测试可用时间， 30min ？ 参考：
         *              "created":"2018-09-21 10:48:49",
         *             "status_str":"已完成",
         *             "expired_time":"2018-09-21 11:18:49",
         *             "success_time":"2018-09-21 10:49:00",
         *  {"qr_url":"https://trade.koudaitong.com/wxpay/confirmQr?qr_id=8352436&kdt_id=41461397","qr_code":"data:image/png;base64,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","qr_id":8352436}
         */

//        String s = PayUtil.getInstance().payTest();

        String url = "{\n" +
                "    \"client_id\":\"afcd2eb5b88fbdbd90\",\n" +
                "    \"id\":\"E20180925095935022600019\",\n" +
                "    \"kdt_id\":41461397,\n" +
                "    \"kdt_name\":\"????????¤?????????\",\n" +
                "    \"mode\":1,\n" +
                "    \"msg\":\"%7B%22order_promotion%22:%7B%22adjust_fee%22:%220.00%22%7D,%22qr_info%22:%7B%22qr_id%22:8352149,%22qr_pay_id%22:23213076,%22qr_name%22:%22%E5%8F%AF%E9%87%8D%E5%A4%8D%E4%BD%BF%E7%94%A8%E3%80%8209211029%22%7D,%22refund_order%22:[],%22full_order_info%22:%7B%22address_info%22:%7B%22self_fetch_info%22:%22%22,%22delivery_address%22:%22%22,%22delivery_postal_code%22:%22%22,%22receiver_name%22:%22%22,%22delivery_province%22:%22%22,%22delivery_city%22:%22%22,%22delivery_district%22:%22%22,%22address_extra%22:%22%7B%7D%22,%22receiver_tel%22:%22%22%7D,%22remark_info%22:%7B%22buyer_message%22:%22%22%7D,%22pay_info%22:%7B%22outer_transactions%22:[%224200000166201809251039556211%22],%22post_fee%22:%220.00%22,%22total_fee%22:%220.01%22,%22payment%22:%220.01%22,%22transaction%22:[%22180925095940000017%22]%7D,%22buyer_info%22:%7B%22fans_type%22:9,%22buyer_id%22:290195682,%22fans_id%22:1950113670,%22fans_nickname%22:%22%22%7D,%22orders%22:[%7B%22outer_sku_id%22:%22%22,%22sku_unique_code%22:%22%22,%22goods_url%22:%22https://h5.youzan.com/v2/showcase/goods%3Falias=null%22,%22item_id%22:2147483647,%22outer_item_id%22:%22null%22,%22discount_price%22:%220.01%22,%22item_type%22:30,%22num%22:1,%22sku_id%22:0,%22sku_properties_name%22:%22%22,%22pic_path%22:%22https://img.yzcdn.cn/public_files/2016/12/29/33e6c838cefa614c5121c63c80f860e9.png%22,%22oid%22:%221473616351109060473%22,%22title%22:%22%E5%8F%AF%E9%87%8D%E5%A4%8D%E4%BD%BF%E7%94%A8%E3%80%8209211029%22,%22buyer_messages%22:%22%22,%22is_present%22:false,%22pre_sale_type%22:%22null%22,%22points_price%22:%220%22,%22price%22:%220.01%22,%22total_fee%22:%220.01%22,%22alias%22:%22null%22,%22payment%22:%220.01%22,%22is_pre_sale%22:%22null%22%7D],%22source_info%22:%7B%22is_offline_order%22:false,%22book_key%22:%22null%22,%22source%22:%7B%22platform%22:%22wx%22,%22wx_entrance%22:%22direct_buy%22%7D%7D,%22order_info%22:%7B%22consign_time%22:%22%22,%22order_extra%22:%7B%22is_from_cart%22:%22false%22%7D,%22created%22:%222018-09-25%2009:59:35%22,%22status_str%22:%22%E5%B7%B2%E5%AE%8C%E6%88%90%22,%22expired_time%22:%222018-09-25%2010:29:35%22,%22success_time%22:%222018-09-25%2009:59:46%22,%22type%22:6,%22tid%22:%22E20180925095935022600019%22,%22confirm_time%22:%22%22,%22pay_time%22:%222018-09-25%2009:59:45%22,%22update_time%22:%222018-09-25%2009:59:46%22,%22pay_type_str%22:%22WEIXIN_DAIXIAO%22,%22is_retail_order%22:false,%22pay_type%22:10,%22team_type%22:1,%22refund_state%22:0,%22close_type%22:0,%22status%22:%22TRADE_SUCCESS%22,%22express_type%22:9,%22order_tags%22:%7B%22is_payed%22:true,%22is_secured_transactions%22:true%7D%7D%7D%7D\",\n" +
                "    \"msg_id\":\"72c50c16-9933-435e-8678-f40a296cbaa9\",\n" +
                "    \"sendCount\":0,\n" +
                "    \"sign\":\"b6731bb0628a972c3b83064a5e1d7c28\",\n" +
                "    \"status\":\"PAID\",\n" +
                "    \"test\":false,\n" +
                "    \"type\":\"trade_TradePaid\",\n" +
                "    \"version\":1537840786\n" +
                "}";
        String deurl = null;
        try {
            deurl = URLDecoder.decode(url,"UTF-8");

            System.out.println(deurl);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        testQRName();
        testQrBean();

//        testQrCodeSql();
//        testPayInfiSql();
//        String ss = "一次性支付。09211029eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMzYxMzU3MTMzM";
//        System.out.println(ss.length());

//        PayTokenEndTime=157777777777777
//        PayToken=9de830f1a71835c4950871a84e0c9d20
//        ApplicationConfig.instance().setDebug(false);
//            ApplicationConfig.instance().uploadToFile();


//        String url = "%7B%22order_promotion%22:%7B%22adjust_fee%22:%220.00%22%7D,%22qr_info%22:%7B%22qr_id%22:8286057,%22qr_pay_id%22:23005982,%22qr_name%22:%22%E6%B5%8B%E8%AF%95%E7%94%9F%E6%88%90%E6%94%B6%E6%AC%BE%E4%BA%8C%E7%BB%B4%E7%A0%81%22%7D,%22refund_order%22:[],%22full_order_info%22:%7B%22address_info%22:%7B%22self_fetch_info%22:%22%22,%22delivery_address%22:%22%22,%22delivery_postal_code%22:%22%22,%22receiver_name%22:%22%22,%22delivery_province%22:%22%22,%22delivery_city%22:%22%22,%22delivery_district%22:%22%22,%22address_extra%22:%22%7B%7D%22,%22receiver_tel%22:%22%22%7D,%22remark_info%22:%7B%22buyer_message%22:%22%22%7D,%22pay_info%22:%7B%22outer_transactions%22:[%224200000185201809187018379596%22],%22post_fee%22:%220.00%22,%22total_fee%22:%220.01%22,%22payment%22:%220.01%22,%22transaction%22:[%22180918101736000050%22]%7D,%22buyer_info%22:%7B%22fans_type%22:9,%22buyer_id%22:290195682,%22fans_id%22:1950113670,%22fans_nickname%22:%22%22%7D,%22orders%22:[%7B%22outer_sku_id%22:%22%22,%22sku_unique_code%22:%22%22,%22goods_url%22:%22https://h5.youzan.com/v2/showcase/goods%3Falias=null%22,%22item_id%22:2147483647,%22outer_item_id%22:%22null%22,%22discount_price%22:%220.01%22,%22item_type%22:30,%22num%22:1,%22sku_id%22:0,%22sku_properties_name%22:%22%22,%22pic_path%22:%22https://img.yzcdn.cn/public_files/2016/12/29/33e6c838cefa614c5121c63c80f860e9.png%22,%22oid%22:%221472319870133616259%22,%22title%22:%22%E6%B5%8B%E8%AF%95%E7%94%9F%E6%88%90%E6%94%B6%E6%AC%BE%E4%BA%8C%E7%BB%B4%E7%A0%81%22,%22buyer_messages%22:%22%22,%22is_present%22:false,%22pre_sale_type%22:%22null%22,%22points_price%22:%220%22,%22price%22:%220.01%22,%22total_fee%22:%220.01%22,%22alias%22:%22null%22,%22payment%22:%220.01%22,%22is_pre_sale%22:%22null%22%7D],%22source_info%22:%7B%22is_offline_order%22:false,%22book_key%22:%22null%22,%22source%22:%7B%22platform%22:%22wx%22,%22wx_entrance%22:%22direct_buy%22%7D%7D,%22order_info%22:%7B%22consign_time%22:%22%22,%22order_extra%22:%7B%22is_from_cart%22:%22false%22%7D,%22created%22:%222018-09-18%2010:17:34%22,%22status_str%22:%22%E5%B7%B2%E6%94%AF%E4%BB%98%22,%22expired_time%22:%222018-09-18%2010:47:34%22,%22success_time%22:%22%22,%22type%22:6,%22tid%22:%22E20180918101734022600020%22,%22confirm_time%22:%22%22,%22pay_time%22:%222018-09-18%2010:17:45%22,%22update_time%22:%222018-09-18%2010:17:46%22,%22pay_type_str%22:%22WEIXIN_DAIXIAO%22,%22is_retail_order%22:false,%22pay_type%22:10,%22team_type%22:1,%22refund_state%22:0,%22close_type%22:0,%22status%22:%22TRADE_PAID%22,%22express_type%22:9,%22order_tags%22:%7B%22is_payed%22:true,%22is_secured_transactions%22:true%7D%7D%7D%7D";
//
//        try {
//            String deurl = URLDecoder.decode(url,"UTF-8");
//            System.out.println(deurl);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

//        PayUtil.init();


//        SMSUtil.sendSMS("225418", "13613571331");

//        try {
//            s = MD5Util.EncoderByMd5("121411");
//            boolean checkpassword = MD5Util.checkpassword("121411", s);
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

//        PasswordEncryption.passWordTest();
//
//        Logger log = Logger.getLogger("Log");
//        log.info("inFO~~");
//        log.debug("deBug");
//        String property = System.getProperty("user.dir");
//        log.error("Erro" + property + File.separator + "configs"+File.separator+"log4j2.xml");



//        PasswordEncryption.passWordTest();
//        String s = "eyJhbGciOiJIUzI1NiJ9.eyJwaG9uZSI6IjEzNjEzNTcxMzMxIiwiZXhwIjozMDc1MDE1MDg5LCJ1c2VySWQiOjF9._htAi06VB9bvUPY6r8bAjHkVuvMAI9VSZ9vprtazZrI";
//        System.out.println(s.length());
//        JwtUtil.tokenTest();
//        registerUserInfo();

//        List<UserInfoBean> userInfoBeans = DaoManager.init().selectSQLForBean(
//                UserInfoBean.newInstance()
//        );
//        for (UserInfoBean bean : userInfoBeans) {
//            String token = bean.getToken();
//            if (token == null) {
//                System.out.println(token);
//            }
//        }

//        DaoManager.init().deleteBeanForSQL(UserInfoBean.newInstance());
//        DaoManager.init().deleteBeanForSQL(UserVipInfoBean.newInstance());
//        DaoManager.init().deleteBeanForSQL(ScanInfoBean.newInstance());
//        DaoManager.init().deleteBeanForSQL(HistoricalRecordsBean.newInstance());
//        DaoManager.init().deleteBeanForSQL(DocumentaryInfoBean.newInstance());

        /* 增删跟投 */
//        DocumentaryInfoBean documentaryInfoBean = DocumentaryInfoBean.newInstance(
//                1, "www.baidu.com", "备注", ORDER_TYPE.ORDER_TYPE_ALL,
//                5, true, 20, DOCUMENTARY_TYPE.DOCUMENTARY_TYPE_STRICT
//        );
//
//        DaoManager.init().insertBeanToSQL(documentaryInfoBean);
//        List<DocumentaryInfoBean> documentaryInfoBeans = DaoManager.init().selectSQLForBean(DocumentaryInfoBean.newInstance());
//        System.out.println(documentaryInfoBeans.get(0));
//        DaoManager.init().updataBeanForSQL(
//                DocumentaryInfoBean.newInstance(
//                        1, "www.baiduUpdata.com", "备注Updata", ORDER_TYPE.ORDER_TYPE_ALL,
//                        5, true, 20, DOCUMENTARY_TYPE.DOCUMENTARY_TYPE_STRICT),
//                documentaryInfoBean
//        );
//        documentaryInfoBeans = DaoManager.init().selectSQLForBean(DocumentaryInfoBean.newInstance());
//        System.out.println(documentaryInfoBeans.get(0));
//        DaoManager.init().deleteBeanForSQL(DocumentaryInfoBean.newInstance());





        /* 增加历史记录 */
//        DaoManager.init().deleteBeanForSQL(HistoricalRecordsBean.newInstance());
//
//        HistoricalRecordsBean historicalRecordsBean = HistoricalRecordsBean.newInstance(
//                PLATFORM_TYPE.PLATFORM_TYPE_JD,
//                "备注",
//                ORDER_TYPE.ORDER_TYPE_ALL,
//                "2018-09-04 17:01:00",
//                "321554",
//                "45.1",
//                "20",
//                true,
//                "",
//                SCAN_OR_ORDER_TYPE.SCAN_OR_ORDER_TYPE_SCAN
//        );
//        DaoManager.init().insertBeanToSQL(historicalRecordsBean);
//        List<HistoricalRecordsBean> historicalRecordsBeans = DaoManager.init().selectSQLForBean(historicalRecordsBean);
//        log(historicalRecordsBean.toString());



        /*   增删扫描    */
//        DaoManager.init().updataBeanForSQL(
//                ScanInfoBean.newInstance(
//                        13,"www.baiduupdata.com","备注updata", ORDER_TYPE.ORDER_TYPE_ALL,20, true
//                ),
//                ScanInfoBean.newInstanceByUrl("www.baidu.com")
//        );
//
//        DaoManager.init().selectSQLForBean(ScanInfoBean.newInstance());
//
//        DaoManager.init().deleteBeanForSQL(ScanInfoBean.newInstanceByUrl("www.baidu.com"));
//        DaoManager.init().selectSQLForBean(ScanInfoBean.newInstance());



        DaoManager.instance().release();

    }

    private static void testQrBean() {

        PayQRcodeBean senior1YearBean = PayQRcodeBean.createSenior1YearBean(123);
        System.out.println(senior1YearBean.toString());
        PayQRcodeBean senior3MonthBean = PayQRcodeBean.createSenior3MonthBean(123);
        System.out.println(senior3MonthBean.toString());
        PayQRcodeBean senior1MonthBean = PayQRcodeBean.createSenior1MonthBean(123);
        System.out.println(senior1MonthBean.toString());
        PayQRcodeBean super1YearBean = PayQRcodeBean.createSuper1YearBean(123);
        System.out.println(super1YearBean.toString());
        PayQRcodeBean super3MonthBean = PayQRcodeBean.createSuper3MonthBean(123);
        System.out.println(super3MonthBean.toString());
        PayQRcodeBean super1MonthBean = PayQRcodeBean.createSuper1MonthBean(123);
        System.out.println(super1MonthBean.toString());

    }

    private static void registerUserInfo() {
        Calendar calendar = Calendar.getInstance();
//        calendar.
        // 用户注册操作
        UserInfoBean addUserInfoBean = new UserInfoBean(
                null, null,
                0, "13613571331",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()),
                false, "123456",
                "eyJhbGciOiJIUzI1NiJ9.eyJwaG9uZSI6IjEzNjEzNTcxMzMxIiwiZXhwIjozMDc1MDE2MDY3LCJ1c2VySWQiOjF9.NpMVB4z6pS7wVtx19tkDLb3w-g7Pa5Dib-C30q9ZavI");

        DaoManager.instance().insertBeanToSQL(addUserInfoBean);
        List<UserInfoBean> userInfoBeans1 = DaoManager.instance().selectSQLForBean(addUserInfoBean);
        log(userInfoBeans1.toString());
        if (userInfoBeans1.size() > 0) {

            UserInfoBean userInfoBean = userInfoBeans1.get(0);

            // 插入vip数据
            Date time = calendar.getTime();
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
            UserVipInfoBean userVipInfoBean = UserVipInfoBean.newInstanceByVipType(
                    null, userInfoBean.getId(), String.valueOf(time.getTime()), String.valueOf(calendar.getTime().getTime()),
                    VIP_TYPE.VIP_TYPE_SUPER
            );

            if (DaoManager.instance().insertBeanToSQL(userVipInfoBean)) {
                List<UserVipInfoBean> userVipInfoBeans = DaoManager.instance().selectSQLForBean(userVipInfoBean);
                log(userVipInfoBeans.toString());

                if (userVipInfoBeans.size() > 0) {

                    UserVipInfoBean userVipInfoBean1 = userVipInfoBeans.get(0);
                    Integer id = userVipInfoBean1.getId();
                    UserInfoBean newUserInfoBean = UserInfoBean.newInstanceByVipId(id);

                    DaoManager.instance().updataBeanForSQL(newUserInfoBean, userInfoBean);
                    List<UserInfoBean> userInfoBeans = DaoManager.instance().selectSQLForBean(UserInfoBean.newInstance());
                    log(userInfoBeans.toString());

                }
            }
        }
    }

    private static void testQrCodeSql() {

            // 增加
//        int allMoney = 145 * 100;
//        PayQRcodeBean payQRcodeBean = new PayQRcodeBean(null, 123);
//        payQRcodeBean.setQrId(1);
//        payQRcodeBean.setQrImg("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOwAAADsCAIAAAD4sd1DAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAMKElEQVR4nO2dPY8jRReF2x6GDRgjEBEjMSJiSckmIPRvWIkYaSMkQv4IhMT8BlgSAqTNiFYCotUgTWatxIqPeXfHfgOjkfHa7rp1P485T7Q7011d3X3GPnXr1q3JarUaCEFmmt0BQrRQxAQeipjAQxETeChiAg9FTOChiAk8FDGBhyIm8FDEBB6KmMBDERN4KGICD0VM4KGICTwUMYGHIibwUMQEHoqYwPOa5uTJZGLVj03ulv1ttb/v593HbNJ4L5o+jC5nNOxzdx8OtN9y7xq6l3vyk5jAQxETeChiAo/KE4PiZOnC2tf0oULfzLEUcbcxlz5Zp4IvosHQvj5oVLLZZks70uOdCHvv+6CdIPBQxAQeF08sjbl2NJtSQk4ZN913WEs7o+dWwOm9j3K0A7tGvyjyu5pudPg/0bkH7lczwQEB7QSBhyIm8KDaCek3rHnj7Yd1t9PSvuaYUn5aQ2kRW8ViA97W6CUa70UUqw7wrxBCp50g8FDEBJ7SdmITJwNqlSMb6Y+tvuLT4+5WwIh4i7AEbWUCvuZao+1rlHdMoz3aCQIPRUzgKW0nDH2qx7dkVpy4O8LYmJsB5yhcRBz5FKSx5AoxY80CT8MxgHmbWeqnnSDwUMQEHks7EZnFt9PDST1fR/6uiI6UyFJr4xr7n569WXpgt4XUw1kd7+1Tra7rHTsvC+0EgYciJvAg2QmreGq1b1urflodX+35jKISccGoeLcPNj+44/hB2H+rdXVSqr132gkCD0VM4ClRdyLyq3Af1WLMLVgtwjO8nEntZylIA7usdWbepKSaBORJh0E7QeChiAk8SHbiDkM/Wuobs1Rn+kjJSw7NJw7O8TWpg1ahD1Y1j6WHFal/PArtBIGHIibwWO5jZ1X7zKOuQlYj6d/CHTnB1WaVRwkd2JkbzQN01DJLXxqo7EB3Hkhi7WQTaCcIPBQxgScid8JquljzTRScZtBO8BKjyOcQ5rOr5BNDxymHYslM+zqgzJdIv5d90E4QeChiAk+V3IljqjS6SWQuhzTv2eq66aSJ2MnvamoyaGqo7WwEWhl9uK5x3AftBIGHIibwRNgJjd/1rgHccgy6L7eibH5FiYGdxx5vLZfrrlbdce6B65p4cWUfuo8PqMUxCu0EgYciJvBY5hN7nz56fEC+gasXrDZlPbTVa+u2Z1b36+WJrWroanxwt5/z3sPCI6/aqUYyBLQTBB6KmMBTIsTmhNRDm8etvadeg3OyTfDogKWINVbPdR9j6enSuDUoJu+rArQTBB6KmMAT7Yk99tfwrlPhXashy6ea9D/dZA+5A7uw/eFaDgjIFe7OadbEm636LD2e+cSECKCICTyZdiJsH7WA2g6utRqyysIeOF1qA1ytc7m6E5rCK951MI6MrFof5s+WdoLAQxETeDLziVuaaollWvlI6XXRyapTYe4Gowd2ojrBLXeb5dsiEw+k9dRamgoeLLrmK9NOEHgoYgJPZu5E9+qgAwdUmMrPQuPjI+t7mOMeJ66ck9DRTjqafA/pJTxyV1h3gpAdUMQEHjNP7L1e7cDxHuveaoK4qO5wH0zcRVqcWHpjHvWGjwzz+9X46UhoJwg8FDGBp8oau2NltVj87+uvh2E4ffBg+v772d1pwqPOmisTp1WW0g0YpTV6Pfbg2GzT6rH89dlnf3/11TAMJ/fvv/HNNycffaRpbd1hUd+UtZCtxjCiPkihnXBktVi8+P779b9vf/nl5aNHuf05VihiR1bPn2/+d3l1ldWT48Yrn1g6j+9hudJzglfPnq2ur82bNc+fVuaiWL07mHxiD6SeLIzbJ0+WGx/G04uLxM60Y5474f0uaCccuX38ePO/k/PzrJ4cNxSxF6vF4vbXXzd/MnnzzazOHDfHYCdqsry62vok3ubmZhiG4d69mP5scUw52S71ia3qcHnXA3Yd7b189Gj57+jEy+++m7733vK335Y//7y8ulp/Tp/O5/c+/7xRygc6fCDP2CqXunu/PWXfRlFF9RsD492bx2huWHmutqb3YvHHJ5+8eCUwPJ3NhmH412hvNjv74QflJMgWHRNPrpNH0mOk0E648PLHH3d6ia3PZmKCpYi7830DpkYjfd7y6dObL79s1Ovk/Hzy9tvtjVdIKW58X2GxfFV0YrXBvl9p2leS0ofbn3768+HDV41EQXa+vgPv1Py6Vg3STmhZLRbLq6vbJ09W19evf/rpX198IVLw6vp69ezZAJLgVhOKWMXy6dM/Hz68ffx4+fz5yf37pw8eZPfovwiSiAvGL9fBYM1wbXJ+3j4dbfsE0nNLrCghYqu1XPvaaRx5pNSdOJ3PJ2dnolO6Q5abaO6x8UKjz9PqL4fTzslsTU2TDihiAo+XnfDIQ205t3tqVNmfbk7nc2n6hKb+Rouz0sR9RVjN3pXwxI1A1EoTMZ3NTi4v249frVZW934gnwEO2olkJrNZdhfgoYgzObm8RFnuUZkqe3ZYXS5gd7Bu1ils68DwyQcfnFxevvbxx5N33knpTLWxgYaIfGKP2gXS9g9fqP1a7WxK9nQ+X/9jMptNLy4mZ2dWufCagazJdVsGZ96fIEgDu4LcOdrpbLY2Bqfz+el8Pv3ww8lbb61VayhZspPMpPidxxxof/Rc74TuHdzcvPj229Xvv08vLqYXF9N33/XWa0Daagses4bdUowQsZTuZU6VSy1ZUWGq/AAmH0ZSKu5j192OMvZZVriJeP+RmDxzhtgIPBQxgafiPnbSdmJOrMCrnS97O40dM9GD+z52Q4Ovsmqnr1k4rEogiM49MN5oec6u3pp2gsBDERN4vPaxa/zVzgPMZ4bLGsc+NPnE0jal56ZYONRpZ9d4MxAar+nqUyMfLO0EgQf1kziMyJmq4lPKZTlmEXuv89NQuW9wmIlY44GkceJgwnKdNRiumTPJUJNeSwM9MYGHIibwRMSJpcnymtoIHmiuW9nXWtWT3kRqvfJzJ7yxyg/W5FoEx189/Kho0UDlv7p90E4QeChiAk9E3QkP76W5rtPlTOjOMylSRyLlGWbuY7fzXOUxYXT49cgaEeaLdpXXcn13tBMEHoqYwFMlxCatj5sVEjLJeQjos/l4QOm5NRXGRgldY7dJhewwKQFxVtFat30/N6zMJCXlw4V2gsBDERN4LOPEVmEU71zbFm+dHs5r6Q+it/agysBOJCbpk3LanyKlFm8AKXUtNNBOEHgoYgJPqJ0ImN+38nDSuPUmHmGmyFzqsDi31TjERcTBvhA6F3YovHYNBdoJAg9FTOBxsRMeMdfEfNlqsVJR+4bPrWy9tojcCY+mNOvGytYvU17XYx2ex85U7Sc2QjtB4KGICTwRdSc0p3vs65FVx7elTU1tZu++WR1s/q69JjvM95nryKPdeZhh4Q+rc0cbTLwvCGgnCDwUMYHHy05Y+R7v6EzLteIbscXKc5clLZ+4RYUeecNOucWjXfJOuveoT7xJS/87/kJYn5iQYaCIyREw8QjEHsBqw5Wssq3EFhM7EeGJrfxfpI+sXNO3+w84oO6EtA8m0E4QeChiAk+VJfsiDJMfquUqRILe/ztK5BN75CUbxpilg0KrOsSj9+I0c2FVdyKsLgftBIGHIibwROzZISUyL8I1r0A51WyVD23+mjrGFa4ld0sP7FpyiBOJrO9rFRveeQw6tBMEHoqYwFPaTrTgVO8s+NIp10qJE3tc1GUfOyneA0TDvAiRN9XsadKBVY5v34W6D9NDO0HgoYgJPBG12PbhXT4/q75EVk7Czrhs4phBf2Ij8AM7Q0ptQtPoZdFrM5tAO0HgoYgJPLQTAhIDw0czRewBkojN46DFczNESGO3Vn8kHjUupNBOEHgoYgIPkp2Q5qeK2inuQb1zi0dXQDmNB0z28kASsZSsmsGi/nRcyyO32IqWezTvD+0EgYciJvCg2gnp2jjN+jYrfyxqE3Qa2eO5jVJ6b+fIWsLefjerXtu+H0qTqzz2W7GCdoLAQxETeCztRIU8Wo9GPGpTeOcrW7VvEg5jPvE/WO3rhujXG7txR7X+eEM7QeChiAk8FDGBR7XxDCEV4CcxgYciJvBQxAQeipjAQxETeChiAg9FTOChiAk8FDGBhyIm8FDEBB6KmMBDERN4KGICD0VM4KGICTwUMYGHIibw/B+4CrfCZWbVnAAAAABJRU5ErkJggg==");
//        payQRcodeBean.setQrName("测试插入数据库");
//        payQRcodeBean.setVipType(11);
//        payQRcodeBean.setTimeNum(1);
//        payQRcodeBean.setTimeUnit("年");
//        payQRcodeBean.setTimeLong(365*12*60*60 + "");
//        payQRcodeBean.setAllMoney(allMoney);
//        payQRcodeBean.setMonthMoney(allMoney / 366 * 30);
//        payQRcodeBean.setCreateTime(System.currentTimeMillis() + "");
//
//        boolean b = DaoManager.instance().insertBeanToSQL(payQRcodeBean);
//        System.out.println(b);


        // 查找
//        PayQRcodeBean payQRcodeBean = new PayQRcodeBean(null, 123);
//        List<PayQRcodeBean> payQRcodeBeans = DaoManager.instance().selectSQLForBean(payQRcodeBean);
//        System.out.println(payQRcodeBeans);

        // 更改
//        PayQRcodeBean oldBean = new PayQRcodeBean(null, 123);
//        PayQRcodeBean payQRcodeBean = new PayQRcodeBean(null, null);
//        payQRcodeBean.setQrName("更改名称");
//        boolean b = DaoManager.instance().updataBeanForSQL(payQRcodeBean, oldBean);
//        System.out.println(b);

        // 删除
//        PayQRcodeBean payQRcodeBean = new PayQRcodeBean(null, 123);
//        List<PayQRcodeBean> payQRcodeBeans = DaoManager.instance().selectSQLForBean(payQRcodeBean);
//        if (payQRcodeBeans.size() > 0) {
//            boolean b = DaoManager.instance().deleteBeanForSQL(payQRcodeBeans.get(0));
//            System.out.println(b);
//        }
    }

    private static void testPayInfiSql() {

        PayInfoBean payInfoBean = new PayInfoBean(null, 123);

        // 增加
//        payInfoBean.setAllData("{\n" +
//                "    \"client_id\":\"afcd2eb5b88fbdbd90\",\n" +
//                "    \"id\":\"E20180918101734022600020\",\n" +
//                "    \"kdt_id\":41461397,\n" +
//                "    \"kdt_name\":\"????????¤??????????°????\",\n" +
//                "    \"mode\":1,\n" +
//                "    \"msg\":\"%7B%22order_promotion%22:%7B%22adjust_fee%22:%220.00%22%7D,%22qr_info%22:%7B%22qr_id%22:8286057,%22qr_pay_id%22:23005982,%22qr_name%22:%22%E6%B5%8B%E8%AF%95%E7%94%9F%E6%88%90%E6%94%B6%E6%AC%BE%E4%BA%8C%E7%BB%B4%E7%A0%81%22%7D,%22refund_order%22:[],%22full_order_info%22:%7B%22address_info%22:%7B%22self_fetch_info%22:%22%22,%22delivery_address%22:%22%22,%22delivery_postal_code%22:%22%22,%22receiver_name%22:%22%22,%22delivery_province%22:%22%22,%22delivery_city%22:%22%22,%22delivery_district%22:%22%22,%22address_extra%22:%22%7B%7D%22,%22receiver_tel%22:%22%22%7D,%22remark_info%22:%7B%22buyer_message%22:%22%22%7D,%22pay_info%22:%7B%22outer_transactions%22:[%224200000185201809187018379596%22],%22post_fee%22:%220.00%22,%22total_fee%22:%220.01%22,%22payment%22:%220.01%22,%22transaction%22:[%22180918101736000050%22]%7D,%22buyer_info%22:%7B%22fans_type%22:9,%22buyer_id%22:290195682,%22fans_id%22:1950113670,%22fans_nickname%22:%22%22%7D,%22orders%22:[%7B%22outer_sku_id%22:%22%22,%22sku_unique_code%22:%22%22,%22goods_url%22:%22https://h5.youzan.com/v2/showcase/goods%3Falias=null%22,%22item_id%22:2147483647,%22outer_item_id%22:%22null%22,%22discount_price%22:%220.01%22,%22item_type%22:30,%22num%22:1,%22sku_id%22:0,%22sku_properties_name%22:%22%22,%22pic_path%22:%22https://img.yzcdn.cn/public_files/2016/12/29/33e6c838cefa614c5121c63c80f860e9.png%22,%22oid%22:%221472319870133616259%22,%22title%22:%22%E6%B5%8B%E8%AF%95%E7%94%9F%E6%88%90%E6%94%B6%E6%AC%BE%E4%BA%8C%E7%BB%B4%E7%A0%81%22,%22buyer_messages%22:%22%22,%22is_present%22:false,%22pre_sale_type%22:%22null%22,%22points_price%22:%220%22,%22price%22:%220.01%22,%22total_fee%22:%220.01%22,%22alias%22:%22null%22,%22payment%22:%220.01%22,%22is_pre_sale%22:%22null%22%7D],%22source_info%22:%7B%22is_offline_order%22:false,%22book_key%22:%22null%22,%22source%22:%7B%22platform%22:%22wx%22,%22wx_entrance%22:%22direct_buy%22%7D%7D,%22order_info%22:%7B%22consign_time%22:%22%22,%22order_extra%22:%7B%22is_from_cart%22:%22false%22%7D,%22created%22:%222018-09-18%2010:17:34%22,%22status_str%22:%22%E5%B7%B2%E6%94%AF%E4%BB%98%22,%22expired_time%22:%222018-09-18%2010:47:34%22,%22success_time%22:%22%22,%22type%22:6,%22tid%22:%22E20180918101734022600020%22,%22confirm_time%22:%22%22,%22pay_time%22:%222018-09-18%2010:17:45%22,%22update_time%22:%222018-09-18%2010:17:46%22,%22pay_type_str%22:%22WEIXIN_DAIXIAO%22,%22is_retail_order%22:false,%22pay_type%22:10,%22team_type%22:1,%22refund_state%22:0,%22close_type%22:0,%22status%22:%22TRADE_PAID%22,%22express_type%22:9,%22order_tags%22:%7B%22is_payed%22:true,%22is_secured_transactions%22:true%7D%7D%7D%7D\",\n" +
//                "    \"msg_id\":\"3b0020c9-b9cc-4926-ad69-44b3b3b85dc6\",\n" +
//                "    \"sendCount\":0,\n" +
//                "    \"sign\":\"d22a01e17bf8007d787867fd2ac2dd94\",\n" +
//                "    \"status\":\"PAID\",\n" +
//                "    \"test\":false,\n" +
//                "    \"type\":\"trade_TradePaid\",\n" +
//                "    \"version\":1537237066\n" +
//                "}");
//        payInfoBean.setMoney(1);
//        payInfoBean.setQrId(1);
//        payInfoBean.setTimeFormat("2018-09-25 09:59:35");
//        payInfoBean.setTimeToLong(System.currentTimeMillis() + "");
//        boolean b = DaoManager.instance().insertBeanToSQL(payInfoBean);
//        System.out.println(b);

        // 查找
//        List<PayInfoBean> payInfoBeans = DaoManager.instance().selectSQLForBean(payInfoBean);
//        System.out.println(payInfoBeans);

        // 更改
//        PayInfoBean newPayInfoBean = new PayInfoBean(null, null);
//        newPayInfoBean.setQrId(2);
//
//        boolean b = DaoManager.instance().updataBeanForSQL(newPayInfoBean, payInfoBean);
//        System.out.println(b);

        // 删除
        boolean b = DaoManager.instance().deleteBeanForSQL(payInfoBean);
        System.out.println(b);

    }

    private static void log(String text) {
        System.out.println(text);
    }


    private static void testQRName() {
        String qrName = createQRName("112", "1200");
        System.out.println(qrName);
        boolean b = authQRName(qrName, "112", "1200");
        System.out.println(b);
    }

    public static String createQRName(String userId, String money) {

        String qrName = userId + money;

        PayQRcodeBean payQRcodeBean = new PayQRcodeBean(null, 112);
        payQRcodeBean.setVipType(23);
//        payQRcodeBean.setTimeNum(1);
//        payQRcodeBean.setTimeUnit("月");
        payQRcodeBean.setTimeLong(31L * 24 * 60 * 60 * 1000 + "");
        payQRcodeBean.setAllMoney(1200);
//        payQRcodeBean.setMonthMoney(1200);
//        payQRcodeBean.setCreateTime(System.currentTimeMillis() + "");

        String s = new Gson().toJson(payQRcodeBean);
        qrName = ZipUtils.zip(s);


//        try {
//            qrName = MD5Util.EncoderByMd5(qrName);
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//        qrName = base64Encoder.encode(s.getBytes());
        System.out.println(qrName + "__" + qrName.length());
        return qrName;
    }

    public static boolean authQRName(String qrNameForMD5, String userId, String money) {
        //            return MD5Util.checkpassword(userId + money, qrNameForMD5);
//            BASE64Decoder base64Decoder = new BASE64Decoder();
//            byte[] bytes = base64Decoder.decodeBuffer(qrNameForMD5);
//            System.out.println(new String(bytes));
        String gunzip = ZipUtils.unzip(qrNameForMD5);
        System.out.println(gunzip);
        return false;
    }
}
