package able.com.debug;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by guanlijie on 2018/2/9.
 */

public class DebugApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
