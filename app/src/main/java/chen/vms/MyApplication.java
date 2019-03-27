package chen.vms;

import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Introduce :
 * Created by CHEN_ on 2019/3/20.
 * version:1.0.0
 **/
public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
