package cn.droidlover.txzn.sys.present;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;

import cn.droidlover.txzn.mvp.XPresent;
import cn.droidlover.txzn.net.ApiSubscriber;
import cn.droidlover.txzn.net.NetError;
import cn.droidlover.txzn.net.XApi;
import cn.droidlover.txzn.sys.db.OrmLiteManager;
import cn.droidlover.txzn.sys.model.UserModel;
import cn.droidlover.txzn.sys.net.Api;
import cn.droidlover.txzn.sys.ui.LoginActivity;
import cn.droidlover.txzn.sys.widget.LoadingDialog;

/**
 * Created by ronaldo on 2017/4/21.
 */

public class PUser extends XPresent<LoginActivity> {

    /**
     * 在线登录
     *
     * @param userName
     * @param userPwd
     */
    public void login(final String userName, final String userPwd) {
        Api.getUserService().login(userName, userPwd)
                .compose(XApi.<UserModel>getApiTransformer())
                .compose(XApi.<UserModel>getScheduler())
                .compose(getV().<UserModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<UserModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.cancelDialogForLoading();
                        ToastUtils.showLong("登录失败");
                    }

                    @Override
                    public void onNext(UserModel userModel) {
                        LoadingDialog.cancelDialogForLoading();
                        if (userModel.isSuccess()) {
                            getV().doLogin(userModel.getData());
                        } else {
                            ToastUtils.showLong(userModel.getMessage());
                        }
                    }
                });
    }

    /**
     * 离线登录
     *
     * @param userName
     * @param userPwd
     */
    public void unLineLogin(String userName, String userPwd) {
        List<UserModel.User> userList = OrmLiteManager.getInstance(getV()).getLiteOrm(getV()).query(new QueryBuilder<UserModel.User>(UserModel.User.class).where("login_name=? and pwd=?", userName, EncryptUtils.encryptMD5ToString(userPwd)));
        LoadingDialog.cancelDialogForLoading();
        if (userList.isEmpty()) {
            ToastUtils.showLong("用户名或密码不正确");
        } else {
            UserModel.User user = userList.get(0);
            getV().doLogin(user);
        }
    }
}
