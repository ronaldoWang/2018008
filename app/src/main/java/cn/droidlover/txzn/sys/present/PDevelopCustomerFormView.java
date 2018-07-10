package cn.droidlover.txzn.sys.present;

import com.blankj.utilcode.util.ToastUtils;

import cn.droidlover.txzn.mvp.XPresent;
import cn.droidlover.txzn.net.ApiSubscriber;
import cn.droidlover.txzn.net.NetError;
import cn.droidlover.txzn.net.XApi;
import cn.droidlover.txzn.sys.db.OrmLiteManager;
import cn.droidlover.txzn.sys.model.DevelopCustomerModel;
import cn.droidlover.txzn.sys.net.Api;
import cn.droidlover.txzn.sys.ui.DevelopCustomerFormViewActivity;

/**
 * Created by haoxi on 2017/5/11.
 */

public class PDevelopCustomerFormView extends XPresent<DevelopCustomerFormViewActivity> {
    /**
     * 在线查询单个
     *
     * @param id
     */
    public void queryOne(final String id) {
        Api.getDevelopCustomerService().queryOne(id)
                .compose(XApi.<DevelopCustomerModel>getApiTransformer())
                .compose(XApi.<DevelopCustomerModel>getScheduler())
                .compose(getV().<DevelopCustomerModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DevelopCustomerModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showShort(error.getMessage());
                    }

                    @Override
                    public void onNext(DevelopCustomerModel developCustomerModel) {
                        if (developCustomerModel.isSuccess()) {
                            if (!developCustomerModel.getData().isEmpty()) {
                                getV().showData(developCustomerModel.getData().get(0));
                            }
                        } else {
                            ToastUtils.showShort(developCustomerModel.getMessage());
                        }
                    }
                });
    }

    /**
     * 离线查询单个
     *
     * @param id
     */
    public void queryNativeOne(final String id) {
        DevelopCustomerModel.DevelopCustomer data = OrmLiteManager.queryById(getV(), DevelopCustomerModel.DevelopCustomer.class, id);
        if (null == data) {
            ToastUtils.showShort("未查询到数据");
        } else {
            getV().showData(data);
        }
    }
}
