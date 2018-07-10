package cn.droidlover.txzn.sys.present;

import com.blankj.utilcode.util.ToastUtils;

import cn.droidlover.txzn.mvp.XPresent;
import cn.droidlover.txzn.net.ApiSubscriber;
import cn.droidlover.txzn.net.NetError;
import cn.droidlover.txzn.net.XApi;
import cn.droidlover.txzn.router.Router;
import cn.droidlover.txzn.sys.model.DevelopCustomerModel;
import cn.droidlover.txzn.sys.net.Api;
import cn.droidlover.txzn.sys.ui.DevelopCustomerFormEditActivity;
import cn.droidlover.txzn.sys.widget.LoadingDialog;

/**
 * Created by haoxi on 2017/4/25.
 */

public class PDevelopCustomerForm extends XPresent<DevelopCustomerFormEditActivity> {

    /**
     * 查询单个
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
     * 保存对象
     *
     * @param data
     */
    public void save(DevelopCustomerModel.DevelopCustomer data) {
        LoadingDialog.showDialogForLoading(getV());//加载框
        Api.getDevelopCustomerService().save(data.getDataMap())
                .compose(XApi.<DevelopCustomerModel>getApiTransformer())
                .compose(XApi.<DevelopCustomerModel>getScheduler())
                .compose(getV().<DevelopCustomerModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DevelopCustomerModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.cancelDialogForLoading();
                        ToastUtils.showShort("保存失败");
                    }

                    @Override
                    public void onNext(DevelopCustomerModel developCustomerModel) {
                        if (developCustomerModel.isSuccess()) {
                            ToastUtils.showShort(developCustomerModel.getMessage());
                            Router.pop(getV());
                        } else {
                            ToastUtils.showShort(developCustomerModel.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        LoadingDialog.cancelDialogForLoading();
                    }
                });
    }
}
