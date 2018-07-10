package cn.droidlover.txzn.test.rxbus;

import cn.droidlover.txzn.event.IBus;

/**
 * Created by wanglei on 2017/1/30.
 */

public class LoginEvent implements IBus.IEvent {

    @Override
    public String getTag() {
        return "0";
    }
}
