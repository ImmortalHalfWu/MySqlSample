package com.immortal.half.wu;


import com.immortal.half.wu.bean.*;
import com.immortal.half.wu.bean.enums.*;
import com.immortal.half.wu.configs.ApplicationConfig;
import com.immortal.half.wu.dao.DaoManager;
import com.immortal.half.wu.utils.*;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
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
//            String driverClass = "com.mysql.cj.jdbc.Driver";
//            Class.forName(driverClass);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {


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

    private static void log(String text) {
        System.out.println(text);
    }


}
