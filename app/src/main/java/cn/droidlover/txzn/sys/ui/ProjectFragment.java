package cn.droidlover.txzn.sys.ui;

import android.os.Bundle;

import cn.droidlover.txzn.mvp.XFragment;
import cn.droidlover.txzn.sys.R;
import cn.droidlover.txzn.sys.present.PUser;

/**
 * Created by ronaldo on 2017/6/6.
 */

public class ProjectFragment extends XFragment<PUser> {

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public PUser newP() {
        return null;
    }

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }
}
