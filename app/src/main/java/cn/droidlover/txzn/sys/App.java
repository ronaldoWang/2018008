package cn.droidlover.txzn.sys;

import android.support.multidex.MultiDex;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.blankj.utilcode.util.Utils;

import cn.droidlover.txzn.net.NetError;
import cn.droidlover.txzn.net.NetProvider;
import cn.droidlover.txzn.net.RequestHandler;
import cn.droidlover.txzn.net.XApi;
import cn.droidlover.txzn.sys.base.BaseApplication;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by wanglei on 2016/12/31.
 */

public class App extends BaseApplication {

    private static App sInstance;

    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Utils.init(this);
        MultiDex.install(this);
        TypefaceProvider.registerDefaultIconSets();
        XApi.registerProvider(new NetProvider() {

            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[0];
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {

            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public RequestHandler configHandler() {
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                return 0;
            }

            @Override
            public long configReadTimeoutMills() {
                return 0;
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }
        });
    }
}
