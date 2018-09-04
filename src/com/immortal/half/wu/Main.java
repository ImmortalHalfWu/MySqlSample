package com.immortal.half.wu;


import com.immortal.half.wu.bean.*;
import com.immortal.half.wu.bean.enums.*;
import com.immortal.half.wu.bean.utils.BeanUtil;
import com.immortal.half.wu.dao.DaoManager;
import com.immortal.half.wu.dao.utils.SqlUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Main {


    static {
        try {
            DaoManager.init();
//            String driverClass = "com.mysql.cj.jdbc.Driver";
//            Class.forName(driverClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        List<UserInfoBean> userInfoBeans = DaoManager.instance().selectSQLForBean(new UserInfoBean(null, null, null, null, null, null, null));
//        for (UserInfoBean bean : userInfoBeans) {
//            System.out.println(bean.toString());
//        }

//        DaoManager.instance().deleteBeanForSQL(UserInfoBean.newInstance());
//        DaoManager.instance().deleteBeanForSQL(UserVipInfoBean.newInstance());
//        DaoManager.instance().deleteBeanForSQL(ScanInfoBean.newInstance());
//        DaoManager.instance().deleteBeanForSQL(HistoricalRecordsBean.newInstance());
//        DaoManager.instance().deleteBeanForSQL(DocumentaryInfoBean.newInstance());

        /* 增删跟投 */
//        DocumentaryInfoBean documentaryInfoBean = DocumentaryInfoBean.newInstance(
//                1, "www.baidu.com", "备注", ORDER_TYPE.ORDER_TYPE_ALL,
//                5, true, 20, DOCUMENTARY_TYPE.DOCUMENTARY_TYPE_STRICT
//        );
//
//        DaoManager.instance().insertBeanToSQL(documentaryInfoBean);
//        List<DocumentaryInfoBean> documentaryInfoBeans = DaoManager.instance().selectSQLForBean(DocumentaryInfoBean.newInstance());
//        System.out.println(documentaryInfoBeans.get(0));
//        DaoManager.instance().updataBeanForSQL(
//                DocumentaryInfoBean.newInstance(
//                        1, "www.baiduUpdata.com", "备注Updata", ORDER_TYPE.ORDER_TYPE_ALL,
//                        5, true, 20, DOCUMENTARY_TYPE.DOCUMENTARY_TYPE_STRICT),
//                documentaryInfoBean
//        );
//        documentaryInfoBeans = DaoManager.instance().selectSQLForBean(DocumentaryInfoBean.newInstance());
//        System.out.println(documentaryInfoBeans.get(0));
//        DaoManager.instance().deleteBeanForSQL(DocumentaryInfoBean.newInstance());





        /* 增加历史记录 */
//        DaoManager.instance().deleteBeanForSQL(HistoricalRecordsBean.newInstance());
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
//        DaoManager.instance().insertBeanToSQL(historicalRecordsBean);
//        List<HistoricalRecordsBean> historicalRecordsBeans = DaoManager.instance().selectSQLForBean(historicalRecordsBean);
//        log(historicalRecordsBean.toString());



        /*   增删扫描    */
//        DaoManager.instance().updataBeanForSQL(
//                ScanInfoBean.newInstance(
//                        13,"www.baiduupdata.com","备注updata", ORDER_TYPE.ORDER_TYPE_ALL,20, true
//                ),
//                ScanInfoBean.newInstanceByUrl("www.baidu.com")
//        );
//
//        DaoManager.instance().selectSQLForBean(ScanInfoBean.newInstance());
//
//        DaoManager.instance().deleteBeanForSQL(ScanInfoBean.newInstanceByUrl("www.baidu.com"));
//        DaoManager.instance().selectSQLForBean(ScanInfoBean.newInstance());



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
                false, "123456"
        );

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
