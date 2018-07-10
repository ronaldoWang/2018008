package cn.droidlover.txzn.sys.present;

import com.blankj.utilcode.util.ToastUtils;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;
import java.util.Map;

import cn.droidlover.txzn.mvp.XPresent;
import cn.droidlover.txzn.net.ApiSubscriber;
import cn.droidlover.txzn.net.NetError;
import cn.droidlover.txzn.net.XApi;
import cn.droidlover.txzn.sys.db.OrmLiteManager;
import cn.droidlover.txzn.sys.model.DevelopCustomerModel;
import cn.droidlover.txzn.sys.model.common.Constent;
import cn.droidlover.txzn.sys.net.Api;
import cn.droidlover.txzn.sys.ui.DevelopCustomerFragment;
import cn.droidlover.txzn.sys.widget.LoadingDialog;

/**
 * Created by haoxi on 2017/4/25.
 */

public class PDevelopCustomer extends XPresent<DevelopCustomerFragment> {
    public void loadData(final int page, final Map<String, Object> conditionMap) {
        Api.getDevelopCustomerService().query(page, conditionMap)
                .compose(XApi.<DevelopCustomerModel>getApiTransformer())
                .compose(XApi.<DevelopCustomerModel>getScheduler())
                .compose(getV().<DevelopCustomerModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DevelopCustomerModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.cancelDialogForLoading();
                        ToastUtils.showShort(error.getMessage());
                    }

                    @Override
                    public void onNext(DevelopCustomerModel developCustomerModel) {
                        if (developCustomerModel.isSuccess()) {
                            getV().showData(page, developCustomerModel.getData());
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

    public void delete(final String customerNo) {
        LoadingDialog.showDialogForLoading(getV().getActivity());
        Api.getDevelopCustomerService().delete(customerNo)
                .compose(XApi.<DevelopCustomerModel>getApiTransformer())
                .compose(XApi.<DevelopCustomerModel>getScheduler())
                .compose(getV().<DevelopCustomerModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DevelopCustomerModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.cancelDialogForLoading();
                        ToastUtils.showShort(error.getMessage());
                    }

                    @Override
                    public void onNext(DevelopCustomerModel developCustomerModel) {
                        LoadingDialog.cancelDialogForLoading();
                        if (developCustomerModel.isSuccess()) {
                            getV().loadData(1);
                        } else {
                            ToastUtils.showShort(developCustomerModel.getMessage());
                        }
                    }
                });
    }

    public void loadNativeData(final int page) {
        List<DevelopCustomerModel.DevelopCustomer> data = OrmLiteManager.getInstance(getV().getContext())
                .getLiteOrm(getV().getContext())
                .query(new QueryBuilder<DevelopCustomerModel.DevelopCustomer>(DevelopCustomerModel.DevelopCustomer.class)
                        .limit((page - 1) * Constent.PAGE_SIZE, Constent.PAGE_SIZE));
        getV().showData(page, data);
    }
}
