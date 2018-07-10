package cn.droidlover.txzn.sys.ui;

import android.os.Bundle;

import cn.droidlover.txzn.mvp.XFragment;
import cn.droidlover.txzn.sys.R;

/**
 * Created by ronaldo on 2017/6/6.
 */

public class SearchFragment extends XFragment {

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
    public Object newP() {
        return null;
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }
}
