package cn.droidlover.txzn.sys.net;

import cn.droidlover.txzn.net.XApi;
import cn.droidlover.txzn.sys.net.order.OrderService;

/**
 * Created by wanglei on 2016/12/31.
 */

public class Api {
    public static String HTTP = "http://";
    public static String IP = "192.168.0.6:8080";//
    public static String PROJECT = "/2018008/";//
    public static String API_ROOT = HTTP + IP + "/";
    public static String API_USER = HTTP + IP + PROJECT;

    private static UserService userService;
    private static OrderService orderService;
    private static DevelopCustomerService developCustomerService;


    private static SysService sysService;


    public static void initURL() {
        API_ROOT = HTTP + IP + "/";
        Api.API_USER = Api.HTTP + Api.IP + Api.PROJECT;
        userService = null;
        developCustomerService = null;
        sysService = null;
    }

    public static SysService getSysService() {
        if (sysService == null) {
            synchronized (Api.class) {
                if (sysService == null) {
                    sysService = XApi.getInstance().getRetrofit(API_USER, true).create(SysService.class);
                }
            }
        }
        return sysService;
    }

    public static DevelopCustomerService getDevelopCustomerService() {
        if (developCustomerService == null) {
            synchronized (Api.class) {
                if (developCustomerService == null) {
                    developCustomerService = XApi.getInstance().getRetrofit(API_USER, true).create(DevelopCustomerService.class);
                }
            }
        }
        return developCustomerService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            synchronized (Api.class) {
                if (userService == null) {
                    userService = XApi.getInstance().getRetrofit(API_USER, true).create(UserService.class);
                }
            }
        }
        return userService;
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            synchronized (Api.class) {
                if (orderService == null) {
                    orderService = XApi.getInstance().getRetrofit(API_USER, true).create(OrderService.class);
                }
            }
        }
        return orderService;
    }
}
